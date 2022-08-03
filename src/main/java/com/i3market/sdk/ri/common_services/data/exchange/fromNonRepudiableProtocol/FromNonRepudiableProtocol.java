/*
  Copyright 2020-2022 i3-MARKET Consortium:

  ATHENS UNIVERSITY OF ECONOMICS AND BUSINESS - RESEARCH CENTER
  ATOS SPAIN SA
  EUROPEAN DIGITAL SME ALLIANCE
  GFT ITALIA SRL
  GUARDTIME OU
  HOP UBIQUITOUS SL
  IBM RESEARCH GMBH
  IDEMIA FRANCE
  SIEMENS AKTIENGESELLSCHAFT
  SIEMENS SRL
  TELESTO TECHNOLOGIES PLIROFORIKIS KAI EPIKOINONION EPE
  UNIVERSITAT POLITECNICA DE CATALUNYA
  UNPARALLEL INNOVATION LDA

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package com.i3market.sdk.ri.common_services.data.exchange.fromNonRepudiableProtocol;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.i3market.sdk.ri.common_services.data.exchange.interfaces.PoO;
import com.i3market.sdk.ri.common_services.data.exchange.interfaces.PoR;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
//import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;

public class FromNonRepudiableProtocol {
    //From non-repudiable library-------------------- To be removed after library is updated ---------------
	/*
	changes:
		ECkey used over OKP
		ECDSA signer and verifier instead of Ed25519
		JWSHeader.Builder(JWSAlgorithm.ES256) instead of EdDSA
        return jweObject.getPayload(); instead of return jweObject.getPayload().toString();
        validate PoP ---> doesnt need to check if PoP is signed because it isnt :)
        in validatePoP get jwk as string -> problem when parsing from string to jwk, fields dont keep order and hashes aren't matching
	*/
	////////////////////////////////////////////////////////////////////////////////////////////////////

    static Integer IAT_DELAY = 5000;

	public boolean validatePoO(ECKey publicKey, String poO, String cipherblock) throws ParseException, JOSEException, java.text.ParseException {

        PoO poOpayload = decodePoo(publicKey,poO);
        String hashedCipherBlock = Hashing.sha256().
                hashString((CharSequence) cipherblock, StandardCharsets.UTF_8).
                toString();

        Date currentDate = new Date();
        if (!poOpayload.getExchange().getCipherblock_dgst().equals(hashedCipherBlock)) {
            throw new IllegalArgumentException("the cipherblock_dgst parameter in the proof of origin does not correspond to hash of the cipherblock received by the provider");
        } else if (currentDate.getTime() - poOpayload.getIat() > IAT_DELAY) {
            throw new IllegalArgumentException("timestamp error");
        } else {
            return true;
        }

    }

	public PoO decodePoo(ECKey publicKey, String poO) throws JOSEException, ParseException, java.text.ParseException {

        //JWSVerifier verifier = new ECDSAVerifier(publicKey);
        JWSObject jwsObject = JWSObject.parse(poO);
        if (jwsObject.verify(new ECDSAVerifier(publicKey))!=true) {
            throw new IllegalArgumentException("PoO cannot be verified");
        }

        Gson gson = new Gson();
        return gson.fromJson(jwsObject.getPayload().toString(), PoO.class);
    }

	public String createPoR (ECKey privateKey, String poO, String providerId, String consumerId,
                            Integer exchangeId) {

    	Gson gson = new Gson();
    	String hashPooDgst = Hashing.sha256().
                	hashString((CharSequence) poO, StandardCharsets.UTF_8).
                	toString();

    	Date currentDate = new Date();
    	PoR poR = new PoR();
    	poR.setIat(currentDate.getTime());
    	poR.setSub(consumerId);
    	poR.setIss(providerId);
    	PoR.Exchange exchange = new PoR.Exchange();
    	exchange.setPoo_dgst(hashPooDgst);
    	exchange.setHash_alg("sha256");
    	exchange.setId(exchangeId);
    	poR.setExchange(exchange);

    	JWSSigner signer = null;
    	try {
        	signer = new ECDSASigner(privateKey);
    	} catch (JOSEException e) {
        	e.printStackTrace();
    	}
    	// Creates the JWS object with payload
    	JWSObject jwsObject = new JWSObject(
            	new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(privateKey.getKeyID()).build(),
            	new Payload(gson.toJson(poR)));
    	// Compute the EdDSA signature
    	try {
        	jwsObject.sign(signer);
    	} catch (JOSEException e) {
        	e.printStackTrace();
    	}
    	// Serialize the JWS to compact form
    	return jwsObject.serialize();

		}

		public Payload decryptCipherblock(String chiperblock, JWK jwk) throws ParseException, JOSEException {

			JWEObject jweObject = JWEObject.parse(chiperblock);
			jweObject.decrypt(new DirectDecrypter(jwk.toOctetSequenceKey()));

			return jweObject.getPayload();
		}

        //this isnt signed by the auditable accounting so it needs just the provider key
        public boolean validatePoP(ECKey publicKeyProvider, String poP, String jwk, String poO) throws ParseException, JOSEException {

            PoO poOPayload = decodePoo(publicKeyProvider,poO);
            String hashedJwk = Hashing.sha256().
                    hashString(jwk, StandardCharsets.UTF_8).
                    toString();
            System.out.println("The hashed jwk is --->" + hashedJwk);
            if (poOPayload.getExchange().getKey_commitment().equals(hashedJwk)) {
                return true;
            } else {
                throw new IllegalArgumentException("hashed key not correspond to poO key_commitment parameter");
            }

        }

}
