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
package com.i3market.sdk.ri.common_services.verifiableCredentials;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.IssuerApi;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3m.api.backplane.CredentialApi;
import com.i3m.model.backplane.*;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

public class
VerifiableCredentials {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    public Object getIssueVerifiableCredential(String access_token, String id_token, String credential, String callbackUrl) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get issue verifiable credential by credential string");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.getRelease2VcCredentialIssueByCredentialCallbackUrlByCallbackUrl(credential, callbackUrl);
    }

    public Object getIssueVerifiableCredentialByDid(String access_token, String id_token, String credential, String did) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get issue verifiable credential by credential string");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.getRelease2VcCredentialIssueByDidByCredential(did, credential);
    }

    public Object postRevokeCredentialByJWT(String access_token, String id_token, InlineObject credential) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("post revoke verifiable credential by credential JWT");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.postRelease2VcCredentialRevoke(credential);

    }

    public Object postVerifyCredentialByJWT(String access_token, String id_token, InlineObject1 credential) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);
        
        _log.debug("post revoke verifiable credential by credential JWT");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.postRelease2VcCredentialVerify(credential);
    }

    public List<String> getCredentialList(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get issued verifiable credential list");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.getRelease2VcCredential();
    }

    public Object getSubscribeIssuer(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get subscribe the issuer");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerSubscribe();
    }

    public Object getUnsubscribeIssuer(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get unsubscribe the issuer");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerUnsubscribe();
    }

    public Object getVerifyIssuerSubscription(String access_token, String id_token) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get verify the issuer subscription");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerVerify();
    }
}