package com.i3market.sdk.ri.common_services.alerts.subscriptions;

        import com.i3m.api.ApiClient;
        import com.i3m.api.ApiException;
        import com.i3m.api.ApiResponse;
        import com.i3m.api.Configuration;
        import com.i3m.api.backplane.SubscriptionsApi;
        import com.i3m.model.backplane.Subscription;
        import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class DeleteUserSubscription {

    private static final Logger _log = LoggerFactory.getLogger(com.i3market.sdk.ri.common_services.data.offering.CreateOffering.class);

    public DeleteUserSubscription() {
    }

    public ApiResponse<Subscription> deleteUserSubscription (String user_id, String subscription_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("Deleting User {} subscription {} ", user_id, subscription_id);
        SubscriptionsApi subscriptionsApi = new SubscriptionsApi();

        return subscriptionsApi.deleteApiV1UsersByUserIdSubscriptionsBySubscriptionIdWithHttpInfo(user_id, subscription_id);

        //return new ApiResponse(HttpStatus.SC_OK, null);


    }


}

