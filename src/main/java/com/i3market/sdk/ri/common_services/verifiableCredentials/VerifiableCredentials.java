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

public class VerifiableCredentials {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    public ApiResponse<void> getIssueVerifiableCredential(String credential) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get issue verifiable credential by credential string");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.getRelease2VcCredentialIssueByCredential(credential);
    }

    public ApiResponse<void> postRevokeCredentialByJWT(InlineObject credential) {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("post revoke verifiable credential by credential JWT");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.postRelease2VcCredentialRevoke(credential);

    }

    public ApiResponse<void> postVerifyCredentialByJWT(InlineObject credential) {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("post revoke verifiable credential by credential JWT");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.postRelease2VcCredentialVerify(credential);
    }

    public ApiResponse<void> getCredentialList() {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get issued verifiable credential list");
        CredentialApi credentialApi = new CredentialApi();

        return credentialApi.getRelease2VcCredential();
    }

    public ApiResponse<void> getSubscribeIssuer() {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get subscribe the issuer");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerSubscribe();
    }

    public ApiResponse<void> getUnsubscribeIssuer() {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get unsubscribe the issuer");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerUnsubscribe();
    }

    public ApiResponse<void> getVerifyIssuerSubscription() {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("get verify the issuer subscription");
        IssuerApi issuerApi = new IssuerApi();

        return issuerApi.getRelease2VcIssuerVerify();
    }
}
