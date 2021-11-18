package com.i3market.sdk.ri.common_services.tokenizer;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3m.model.backplane.*;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Token {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    public InlineResponse2008 deployTransaction (InlineObject2 inlineObejct2) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", inlineObejct2);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.deployTransactionToBesu(inlineObejct2);

    }

    public InlineResponse2005 createMarketplace (InlineObject inlineObejct) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", inlineObejct);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.addMarketPlace(inlineObejct);

    }

    public InlineResponse20010 exchangeIn (InlineObject4 inlineObejct4) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", inlineObejct4);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.exchangeTokensForMoney(inlineObejct4);

    }

    public InlineResponse2007 clearing (InlineObject1 inlineObejct1) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("clearing operation {} ", inlineObejct1);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.clearBalance(inlineObejct1);

    }

    public InlineResponse20010 exchangeOut (InlineObject4 inlineObejct4) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_out operation {} ", inlineObejct4);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.exchangeTokensForMoney(inlineObejct4);

    }

    public ApiResponse payment (InlineObject5 inlineObejct5) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("payment operation {} ", inlineObejct5);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        tokenizerApi.dataProviderPayment(inlineObejct5);

        return new ApiResponse(HttpStatus.SC_OK, null);

    }

    public ApiResponse setPaid (InlineObject6 inlineObejct6) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("set paid transaction operation {} ", inlineObejct6);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        tokenizerApi.markTokenAsPaid(inlineObejct6);

        return new ApiResponse(HttpStatus.SC_OK, null);

    }

    public InlineResponse2003 getBalanceByAddress(String address) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get balance of marketplace by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getBalance(address);
    }

    public InlineResponse2004 getMarketplaceByAddress(String address) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get marketplace index by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getMarketplaceIndex(address);
    }

    public InlineResponse2006 getTokenTransfersByTransferId(String transferId) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get token transfer object by transfer identifier");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getTokenTransfer(transferId);
    }

    public InlineResponse20013 getTransactionsByTransactionHash(String transactionHash) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get transaction by transaction hash");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getReceipt(transactionHash);
    }
}
