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
package com.i3market.sdk.ri.common_services.data.exchange;

import java.util.Map;

import javax.ws.rs.core.HttpHeaders;

import java.util.Base64;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.i3market.sdk.ri.common_services.data.exchange.fromNonRepudiableProtocol.FromNonRepudiableProtocol;
import com.i3market.sdk.ri.common_services.data.exchange.sqlite.Sqlite;
//import com.i3market.sdk.ri.common_services.data.exchange.fromNonRepudiableProtocol.FromNonRepudiableProtocol;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.api.data_access.DataApi;
import com.i3m.model.data_access.DataAccessResponse;
import com.i3m.model.data_access.InlineObject;
import com.i3m.model.data_access.InlineObject1;
import com.i3m.model.data_access.InlineObject2;
import com.i3m.model.data_access.Invoice;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.jwk.ECKey;
//import com.nimbusds.jose.jwk.JWK;


//import protocol.CreateProofs;
//import protocol.ValidateProofs;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;



import org.json.JSONObject;
import org.json.JSONTokener;


public class AccountDataBlock {

	//private static final Logger _log = LoggerFactory.getLogger(AccountDataBlock.class);
	private static com.nimbusds.jose.jwk.JWK parsedjwk;
	private static Payload decrypted_cipherblock;
	private static String sendJwk;

	static String privateKeyStrConsumer = "{\"kty\":\"EC\",\"crv\": \"P-256\",\"x\": \"NjyNt9n_QEsjwPDQJp45vNn7dn3JeMc8Qj1ffDWfxLA\",\"y\": \"hzj_0heDulDkvAR_u1SqkKVi98_K1MX0GztJggAQ8PQ\",\"d\": \"-VAvKlK5_0-B4c82klsXri28pGbYx2iwKPryoKEE1f4\"}";
	static String publicKeyStrConsumer = "{\"kty\":\"EC\",\"crv\": \"P-256\",\"x\": \"NjyNt9n_QEsjwPDQJp45vNn7dn3JeMc8Qj1ffDWfxLA\",\"y\": \"hzj_0heDulDkvAR_u1SqkKVi98_K1MX0GztJggAQ8PQ\"}";
	static String publicKeyStrProvider = "{\"kty\":\"EC\",\"crv\": \"P-256\",\"x\": \"NjyNt9n_QEsjwPDQJp45vNn7dn3JeMc8Qj1ffDWfxLA\",\"y\": \"hzj_0heDulDkvAR_u1SqkKVi98_K1MX0GztJggAQ8PQ\"}";

	static ECKey publicKeyProvider;
	static {
		try {
			System.out.println("The json that was read is: " + privateKeyStrConsumer);
			publicKeyProvider = ECKey.parse(publicKeyStrProvider);
			System.out.println("The json that was read is: " + publicKeyProvider);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}
	}
	static ECKey privateKeyConsumer;
	static {
		try {
			privateKeyConsumer = ECKey.parse(privateKeyStrConsumer);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}
	}
	static ECKey publicKeyConsumer;
	static {
		try {
			publicKeyConsumer = ECKey.parse(publicKeyStrConsumer);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}
	}

	public String accountDataBlock(String access_token, String id_token, String bearerToken, InlineObject blockIdAck, String data)
			throws ApiException {

		// local
		Sqlite connection = new Sqlite();
		FromNonRepudiableProtocol nrp = new FromNonRepudiableProtocol();

		Connection conn =  connection.connect();
		connection.createTable(conn);

		String dataAccessPath = SdkRiConstants.DATA_ACCESS_ENDPOINT;
		
		ApiClient apiClient = Configuration.getDefaultApiClient();

		apiClient.setBasePath(dataAccessPath);

		apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);
        
		String jwt = "Bearer " + bearerToken;

		Map<String, Authentication> authentications = apiClient.getAuthentications();
		HttpBearerAuth bearerAuth = new HttpBearerAuth(null);
		bearerAuth.setBearerToken(jwt);
		System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("bearerAuth", bearerAuth);

		DataApi dataApi = new DataApi();
		String response = null;
		response = getBlock(blockIdAck, data, dataApi, nrp, connection, conn);
		return response;
	}

	public Invoice createInvoice(String access_token, String id_token, String bearerToken, String fromDate, String toDate) throws ApiException {

		String dataAccessPath = SdkRiConstants.DATA_ACCESS_ENDPOINT;
		
		ApiClient apiClient = Configuration.getDefaultApiClient();

		apiClient.setBasePath(dataAccessPath);

		apiClient.setServerIndex(null);
		
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

		String jwt = "Bearer " + bearerToken;

		Map<String, Authentication> authentications = apiClient.getAuthentications();
		HttpBearerAuth bearerAuth = new HttpBearerAuth(null);
		bearerAuth.setBearerToken(jwt);
		System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("bearerAuth", bearerAuth);

		DataApi dataApi = new DataApi();
		InlineObject2 obj = new InlineObject2();
		obj.setFromDate(fromDate);
		obj.setToDate(toDate);
		Invoice responseFromApi = dataApi.createInvoicePost(obj);

		return responseFromApi;
	}

	public String print(String cipherblock) {
		return cipherblock;
	}

	public void setJwk(String jwk) {
		this.sendJwk = jwk;
	}
	public String getJwk() {
		return sendJwk;
	}

	public byte[] decryptCipherblock(String cipherblock, JSONObject jwk) {
		FromNonRepudiableProtocol nrp = new FromNonRepudiableProtocol();
		try {
			parsedjwk = com.nimbusds.jose.jwk.JWK.parse(jwk.toString());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			decrypted_cipherblock = nrp.decryptCipherblock(cipherblock, parsedjwk);
			System.out.println("Decrypted cypher block ---> " + decrypted_cipherblock);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decrypted_cipherblock.toBytes();
	}
	// Function to decode jwt
	public static JSONObject decodingJwt(String token) {
		if (token != null) {
			String base64String = token.split("\\.", 3)[1];
			byte[] decodedBytes = Base64.getDecoder().decode(base64String);
			String decodedValue = new String(decodedBytes);
			JSONObject decodedValueToJson = new JSONObject(decodedValue);
			return decodedValueToJson;
		}
		return null;
	}

	// Function to read json files and return JSONobject
	public static String readJsonFile(String filepath) {
		InputStream is = AccountDataBlock.class.getResourceAsStream(filepath);
		if (is == null) {
			throw new NullPointerException("Cannot find resource file " + filepath);
		}
		JSONTokener tokener = new JSONTokener(is);
		JSONObject object = new JSONObject(tokener);

		return object.toString();
	}

	// Test to fetch entire file
	public String getFile (String access_token, String id_token, String bearerToken, InlineObject blockIdAck, String data) throws ApiException {

		String check = "not null";
		String response = null;

		File targetFile = new File(data);
		if(!targetFile.exists()){
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 } else {
			 check = "null";
			 response = "File already exists";
		 }
		Sqlite connection = new Sqlite();
		FromNonRepudiableProtocol nrp = new FromNonRepudiableProtocol();

		Connection conn =  connection.connect();
		connection.createTable(conn);

		String dataAccessPath = SdkRiConstants.DATA_ACCESS_ENDPOINT;
		
		ApiClient apiClient = Configuration.getDefaultApiClient();

		apiClient.setBasePath(dataAccessPath);

		apiClient.setServerIndex(null);
		
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

		String jwt = "Bearer " + bearerToken;

		Map<String, Authentication> authentications = apiClient.getAuthentications();
		HttpBearerAuth bearerAuth = new HttpBearerAuth(null);
		bearerAuth.setBearerToken(jwt);
		System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("bearerAuth", bearerAuth);

		DataApi dataApi = new DataApi();

		while (!check.equals("null")) {
			response = getBlock(blockIdAck, data, dataApi, nrp, connection, conn);
			if (response.equals("Invalid PoO")){
				response = "Invalid PoO";
				check = "null";
			} else if (response.equals("Invalid PoP")) {
				response = "Invalid PoP";
				check = "null";
			} else {
				JSONObject jsonResponse = new JSONObject(response);
				blockIdAck.setBlockId(jsonResponse.get("nextBlockId").toString());
				blockIdAck.setBlockAck(jsonResponse.get("blockId").toString());
				check = jsonResponse.get("nextBlockId").toString();
				if(!jsonResponse.get("cipherblock").toString().equals("null")) {
					JSONObject jwk = new JSONObject(this.getJwk());
					byte[] buffer = decryptCipherblock(jsonResponse.get("cipherblock").toString(), jwk);
					try {
						FileOutputStream output = new FileOutputStream(data, true);
						output.write(buffer);
						output.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				if (jsonResponse.get("nextBlockId").toString().equals("null")){
					response = "File imported!";
				}
				}
			}
			return response;
		}


	public String getBlock (InlineObject blockIdAck, String data, DataApi dataApi, FromNonRepudiableProtocol nrp, Sqlite connection, Connection conn) throws ApiException {

		String response = new String();
		ApiResponse<DataAccessResponse> responseFromApi = dataApi.dataPostWithHttpInfo(blockIdAck, data);

		String poO = responseFromApi.getData().getPoO();
		String cipherblock = responseFromApi.getData().getCipherblock();
		System.out.println("The poO is: " + poO);

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("blockId", responseFromApi.getData().getBlockId());
		jsonResponse.put("nextBlockId", responseFromApi.getData().getNextBlockId() == null ? JSONObject.NULL.toString() : responseFromApi.getData().getNextBlockId());
		jsonResponse.put("cipherblock", responseFromApi.getData().getCipherblock());
		jsonResponse.put("poO", responseFromApi.getData().getPoO());
		response = jsonResponse.toString();

		if (!jsonResponse.getString("poO").equals("null")) {
			try {
				Boolean validatePoO = nrp.validatePoO(publicKeyProvider, poO, cipherblock);
				System.out.println("PoO is valid ---> :" + validatePoO);
				if (validatePoO) {
					JSONObject decodedPoO = decodingJwt(poO);
					System.out.println("The decoded poO is: " + decodedPoO);
					String providerId = decodedPoO.getString("iss");
					String consumerId = decodedPoO.getString("sub");
					Integer exchangeId = decodedPoO.getJSONObject("exchange").getInt("id");
					InlineObject1 porObject = new InlineObject1();
					String poR = nrp.createPoR(privateKeyConsumer, poO, providerId, consumerId, exchangeId);
					System.out.println("The poR is: " + poR);
					porObject.setPoR(poR);
					ApiResponse<com.i3m.model.data_access.JWK> jwkFromApi = dataApi.validatePoRPostWithHttpInfo(porObject);
					System.out.println("The jwk is ---> " + jwkFromApi.getData().getJwk());
					System.out.println("The poP is ---> " + jwkFromApi.getData().getPoP());

					JSONObject jwk = new JSONObject();
					jwk.put("alg", jwkFromApi.getData().getJwk().getAlg());
					jwk.put("kid", jwkFromApi.getData().getJwk().getKid());
					jwk.put("k", jwkFromApi.getData().getJwk().getK());
					jwk.put("kty", jwkFromApi.getData().getJwk().getKty());
					this.setJwk(jwk.toString());

					JSONObject poP = new JSONObject();
					poP.put("readyForRegistration", jwkFromApi.getData().getPoP().getReadyForRegistration());
					poP.put("dataHash", jwkFromApi.getData().getPoP().getDataHash());
					poP.put("id", jwkFromApi.getData().getPoP().getId());

					try {
						//check of PoP is valid
						String stringJwk = "{\"kty\":\"" + jwkFromApi.getData().getJwk().getKty().toString() + "\",\"k\":\"" + jwkFromApi.getData().getJwk().getK().toString() + "\",\"kid\":\"" + jwkFromApi.getData().getJwk().getKid().toString() + "\",\"alg\":\"" + jwkFromApi.getData().getJwk().getAlg().toString() + "\"}";
						System.out.println("The string version ---> " + stringJwk);
						nrp.validatePoP(publicKeyProvider, poP.toString(), stringJwk, poO);

						//proofs to be inserted into consumer database
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date dt = new Date();
    					System.out.println(formatter.format(dt));

						String date = formatter.format(dt).toString();
						String blockId = decodedPoO.getJSONObject("exchange").get("block_id").toString();
						connection.insert(date, providerId, blockId, poO, poR, poP.toString(), conn);
					} catch (IllegalArgumentException e){
						response = "Invalid PoP";
					};
				}
			} catch (IllegalArgumentException e) {
				response = "Invalid PoO";
			} catch (JOSEException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

		}
		return response;
	}

	public String deleteFile (String data) {
		String response = null;
		File fileToDelete = new File(data);
		if (fileToDelete.delete()) {
			response = "File deleted";
		} else {
			response = "Failed to delete file";
		}
		return response;
	}

	public FileInputStream downloadFile (String data) {
		File file = new File(data);
		FileInputStream inputStream = null;
    	try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			inputStream = null;
			e.printStackTrace();
		}
		 return inputStream;
	}
}
