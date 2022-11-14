package com.i3market.sdk.ri.common_services.tokenizer;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3m.model.backplane.*;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Implemented by: J. Eleazar Escudero
 * @email: eleazar@hopu.org
 */
public class TokenizerController {
    private static final Logger _log = LoggerFactory.getLogger(TokenizerController.class);
    public ApiResponse<InlineResponse2007> clearingOperation(String access_token, String id_token) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsClearingWithHttpInfo();
    }
    public ApiResponse<InlineResponse2007> exchangeIn(String access_token, String id_token, InlineObject inlineObject) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsExchangeInWithHttpInfo(inlineObject);
    }
    public ApiResponse<InlineResponse2007> exchangeOut(String access_token, String id_token, InlineObject1 inlineObject) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsExchangeOutWithHttpInfo(inlineObject);
    }
    public ApiResponse<InlineResponse2007> feePayment(String access_token, String id_token, TokenizationFeePayToken tokenizationFeePay) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsFeePaymentWithHttpInfo(tokenizationFeePay);
    }
    public ApiResponse<InlineResponse2007> setPaid(String access_token, String id_token, TokenizationPayToken tokenizationPay) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsSetPaidWithHttpInfo(tokenizationPay);
    }
    public ApiResponse<TokenizationGenericListToken> getOperations(String access_token, String id_token, String transferId, String type, String status, String user, Date fromdate, Date todate, BigDecimal page, BigDecimal pageSize) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1OperationsWithHttpInfo(transferId, type, status, user, fromdate, todate, page, pageSize);
    }
    public ApiResponse<InlineResponse2008> getBalanceByAddress(String access_token, String id_token, String address) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryBalancesByAddressWithHttpInfo(address);
    }
    public ApiResponse<InlineResponse2009> modifyCommunityWallet(String access_token, String id_token, InlineObject2 inlineObject) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryCommunityWalletWithHttpInfo(inlineObject);
    }
    public ApiResponse<InlineResponse20010> getMarketplaceIndex(String access_token, String id_token, String address) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryMarketplacesByAddressWithHttpInfo(address);
    }
    public ApiResponse<InlineResponse2009> registerMarketplace(String access_token, String id_token, InlineObject3 inlineObject) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryMarketplacesWithHttpInfo(inlineObject);
    }
    public ApiResponse<InlineResponse20011> getTokenTransferByTransferId(String access_token, String id_token, String transferId) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryTokenTransfersByTransferIdWithHttpInfo(transferId);
    }
    public ApiResponse<InlineResponse20012> deploySignedTransaction(String access_token, String id_token, InlineObject4 inlineObject) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryTransactionsDeploySignedTransactionWithHttpInfo(inlineObject);
    }
    public ApiResponse<InlineResponse20013> getReceiptByTransactionHash(String access_token, String id_token, String transactionHash) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryTransactionsByTransactionHashWithHttpInfo(transactionHash);
    }

    private void handleAuthentication (String access_token, String id_token) {
        String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        // Get default client from Configuration
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        // Set basePath to http request
        defaultClient.setBasePath(basePath);

        // Avoiding default server conf based on localhost url
        defaultClient.setServerIndex(null);

        //Add token as headers
        defaultClient.addDefaultHeader("access_token", access_token);
        defaultClient.addDefaultHeader("id_token", id_token);

        // Setup authentications (JWT).
        // String jwt = "Bearer " + access_token;

        Map<String, Authentication> authentications = defaultClient.getAuthentications();
        HttpBearerAuth jwt = new HttpBearerAuth(null);
        jwt.setBearerToken(id_token);

        HttpBearerAuth jwtAccess = new HttpBearerAuth(null);
        jwtAccess.setBearerToken(access_token);

        // System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
        authentications.put("jwt", jwt);
        authentications.put("jwtAccess", jwtAccess);
    }
}
