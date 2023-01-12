package com.i3market.sdk.ri.common_services.tokenizer;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.Pair;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.api.backplane.TokenizerControllerApi;
import com.i3m.model.backplane.TokenizationBalanceResponse;
import com.i3m.model.backplane.TokenizationCommunityWalletBody;
import com.i3m.model.backplane.TokenizationDeploySignedTransactionBody;
import com.i3m.model.backplane.TokenizationDeploySignedTransactionResponse;
import com.i3m.model.backplane.TokenizationExchangeOutBody;
import com.i3m.model.backplane.TokenizationExchangeinBody;
import com.i3m.model.backplane.TokenizationFeePayToken;
import com.i3m.model.backplane.TokenizationMarketplaceBody;
import com.i3m.model.backplane.TokenizationMarketplaceIndexResponse;
import com.i3m.model.backplane.TokenizationMarketplaceResponse;
import com.i3m.model.backplane.TokenizationOperationsResponse;
import com.i3m.model.backplane.TokenizationPayToken;
import com.i3m.model.backplane.TokenizationTokenTransfersResponse;
import com.i3m.model.backplane.TokenizationTransactionObjectResponse;
import com.i3m.model.backplane.TokenizationTransactionObjectToken;
import com.i3m.model.backplane.TokenizationTreasuryTransactionResponse;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;

/**
 * Implemented by: J. Eleazar Escudero
 * @email: eleazar@hopu.org
 */
public class TokenizerController {
    private static final Logger _log = LoggerFactory.getLogger(TokenizerController.class);
    public ApiResponse<TokenizationTransactionObjectResponse> clearingOperation(String access_token, String id_token) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsClearingWithHttpInfo();
    }
    public ApiResponse<TokenizationTransactionObjectResponse> exchangeIn(String access_token, String id_token, TokenizationExchangeinBody  tokenizationExchangeinBody) throws ApiException {
        ApiResponse<String> result;
    	handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsExchangeInWithHttpInfo(tokenizationExchangeinBody);    
    }
    public ApiResponse<TokenizationTransactionObjectResponse> exchangeOut(String access_token, String id_token, TokenizationExchangeOutBody tokenizationExchangeOutBody) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsExchangeOutWithHttpInfo(tokenizationExchangeOutBody);
    }
    public ApiResponse<TokenizationTransactionObjectResponse> feePayment(String access_token, String id_token, TokenizationFeePayToken tokenizationFeePayToken) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsFeePaymentWithHttpInfo(tokenizationFeePayToken);
    }
    public ApiResponse<TokenizationTransactionObjectResponse> setPaid(String access_token, String id_token, TokenizationPayToken tokenizationPayToken) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1OperationsSetPaidWithHttpInfo(tokenizationPayToken);
    }
    public ApiResponse<TokenizationOperationsResponse> getOperations(String access_token, String id_token, String transferId, String type, String status, String user, String fromdate, String todate, BigDecimal page, BigDecimal pageSize) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1OperationsWithHttpInfo(transferId, type, status, user, fromdate, todate, page, pageSize);
    }
    public ApiResponse<TokenizationBalanceResponse> getBalanceByAddress(String access_token, String id_token, String address) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryBalancesByAddressWithHttpInfo(address);
    }
    public ApiResponse<TokenizationTransactionObjectToken> modifyCommunityWallet(String access_token, String id_token, TokenizationCommunityWalletBody tokenizationCommunityWalletBody) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryCommunityWalletWithHttpInfo(tokenizationCommunityWalletBody);
    }
    public ApiResponse<TokenizationMarketplaceIndexResponse> getMarketplaceIndex(String access_token, String id_token, String address) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryMarketplacesByAddressWithHttpInfo(address);
    }
    public ApiResponse<TokenizationMarketplaceResponse> registerMarketplace(String access_token, String id_token, TokenizationMarketplaceBody tokenizationMarketplaceBody) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryMarketplacesWithHttpInfo(tokenizationMarketplaceBody);
    }
    public ApiResponse<TokenizationTokenTransfersResponse> getTokenTransferByTransferId(String access_token, String id_token, String transferId) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryTokenTransfersByTransferIdWithHttpInfo(transferId);
    }
    public ApiResponse<TokenizationDeploySignedTransactionResponse> deploySignedTransaction(String access_token, String id_token, TokenizationDeploySignedTransactionBody tokenizationDeploySignedTransactionBody) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.postApiV1TreasuryTransactionsDeploySignedTransactionWithHttpInfo(tokenizationDeploySignedTransactionBody);
    }
    public ApiResponse<TokenizationTreasuryTransactionResponse> getReceiptByTransactionHash(String access_token, String id_token, String transactionHash) throws ApiException {
        handleAuthentication(access_token, id_token);
        TokenizerControllerApi controller = new TokenizerControllerApi();
        return controller.getApiV1TreasuryTransactionsByTransactionHashWithHttpInfo(transactionHash);
    }

    private void handleAuthentication (String access_token, String id_token) {
        String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        // Get default client from Configuration
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        
        //Set data format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        defaultClient.setDateFormat(sdf);

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
