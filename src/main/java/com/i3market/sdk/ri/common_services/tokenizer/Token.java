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
package com.i3market.sdk.ri.common_services.tokenizer;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import com.i3m.model.backplane.*;

import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Token {

    private static final Logger _log = LoggerFactory.getLogger(Token.class);

    public  InlineResponse2009 deployTransaction (String access_token, String id_token, InlineObject4 deployTransactionToBesu) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);


        _log.debug("exchange_in operation {} ", deployTransactionToBesu);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1TreasuryTransactionsDeploySignedTransaction(deployTransactionToBesu);

    }

    public InlineResponse2006 createMarketplace (String access_token, String id_token,  InlineObject3 registerMarketplace) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("exchange_in operation {} ", registerMarketplace);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1TreasuryMarketplaces(registerMarketplace);

    }

    public InlineResponse2004 exchangeIn (String access_token, String id_token, InlineObject exchangeMoneyForTokens) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("exchange_in operation {} ", exchangeMoneyForTokens);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1OperationsExchangeIn(exchangeMoneyForTokens);

    }

    public  InlineResponse2003 clearing (String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);


        _log.debug("clearing operation {} ");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1OperationsClearing();

    }

    public InlineResponse2004 exchangeOut (String access_token, String id_token, InlineObject1 exchangeTokensForMoney) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("exchange_out operation {} ", exchangeTokensForMoney);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1OperationsExchangeOut(exchangeTokensForMoney);

    }

    public   InlineResponse2004 payment (String access_token, String id_token, TokenizationFeePay dataProviderPayment) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("payment operation {} ", dataProviderPayment);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1OperationsFeePayment(dataProviderPayment);

    }

    public  InlineResponse2003 setPaid (String access_token, String id_token, TokenizationPay markTokenAsPaid) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("set paid transaction operation {} ", markTokenAsPaid);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.postApiV1OperationsSetPaid(markTokenAsPaid);


    }

    public InlineResponse2005 getBalanceByAddress(String access_token, String id_token, String address) throws ApiException {
        
    	String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
    	
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get balance of marketplace by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getApiV1TreasuryBalancesByAddress(address);
    }

    public InlineResponse2007 getMarketplaceByAddress(String access_token, String id_token, String address) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get marketplace index by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getApiV1TreasuryMarketplacesByAddress(address);
    }

    public InlineResponse2008 getTokenTransfersByTransferId(String access_token, String id_token, String transferId) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get token transfer object by transfer identifier");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getApiV1TreasuryTokenTransfersByTransferId(transferId);
    }

    public InlineResponse20010 getTransactionsByTransactionHash(String access_token, String id_token, String transactionHash) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("get transaction by transaction hash");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getApiV1TreasuryTransactionsByTransactionHash(transactionHash);
    }
}
