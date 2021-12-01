package com.i3market.sdk.ri.common_services.tokenizer;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3m.model.backplane.*;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Token {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    public DeployedSignedTransaction deployTransaction (DeployTransactionToBesu deployTransactionToBesu) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", deployTransactionToBesu);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.deployTransactionToBesu(deployTransactionToBesu);

    }

    public RegisterMarketplace1 createMarketplace (RegisterMarketplace registerMarketplace) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", registerMarketplace);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.addMarketPlace(registerMarketplace);

    }

    public ExchangeIn exchangeIn (ExchangeMoneyForTokens exchangeMoneyForTokens) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_in operation {} ", exchangeMoneyForTokens);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.exchangeMoneyForTokens(exchangeMoneyForTokens);

    }

    public ClearingBalance clearing (ClearBalance clearBalance) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("clearing operation {} ", clearBalance);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.clearBalance(clearBalance);

    }

    public ExchangeOut exchangeOut (ExchangeTokensForMoney exchangeTokensForMoney) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("exchange_out operation {} ", exchangeTokensForMoney);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.exchangeTokensForMoney(exchangeTokensForMoney);

    }

    public Payment payment (DataProviderPayment dataProviderPayment) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("payment operation {} ", dataProviderPayment);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.dataProviderPayment(dataProviderPayment);

    }

    public SetPaid setPaid (MarkTokenAsPaid markTokenAsPaid) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("set paid transaction operation {} ", markTokenAsPaid);
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.markTokenAsPaid(markTokenAsPaid);


    }

    public Balances getBalanceByAddress(String address) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get balance of marketplace by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getBalance(address);
    }

    public MarketplaceIndex getMarketplaceByAddress(String address) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get marketplace index by address");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getMarketplaceIndex(address);
    }

    public InlineResponse2003 getTokenTransfersByTransferId(String transferId) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get token transfer object by transfer identifier");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getTokenTransfer(transferId);
    }

    public InlineResponse2004 getTransactionsByTransactionHash(String transactionHash) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get transaction by transaction hash");
        TokenizerControllerApi tokenizerApi = new TokenizerControllerApi();

        return tokenizerApi.getReceipt(transactionHash);
    }
}
