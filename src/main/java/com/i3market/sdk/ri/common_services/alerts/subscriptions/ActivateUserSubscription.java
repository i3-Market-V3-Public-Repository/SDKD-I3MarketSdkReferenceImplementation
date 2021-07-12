package com.i3market.sdk.ri.common_services.alerts.subscriptions;

        import com.i3m.api.ApiClient;
        import com.i3m.api.ApiException;
        import com.i3m.api.ApiResponse;
        import com.i3m.api.Configuration;
        import com.i3m.api.backplane.SubscriptionsApi;
        import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class ActivateUserSubscription {

    private static final Logger _log = LoggerFactory.getLogger(com.i3market.sdk.ri.common_services.data.offering.CreateOffering.class);

    public ActivateUserSubscription() {
    }

    public ApiResponse<Void> activateUserSubscription (String user_id, String subscription_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("Disabling User {} subscription {} ", user_id, subscription_id);
        SubscriptionsApi subscriptionsApi = new SubscriptionsApi();

        return subscriptionsApi.activateSubscriptionWithHttpInfo(user_id, subscription_id);

        //return new ApiResponse(HttpStatus.SC_OK, null);


    }


}

