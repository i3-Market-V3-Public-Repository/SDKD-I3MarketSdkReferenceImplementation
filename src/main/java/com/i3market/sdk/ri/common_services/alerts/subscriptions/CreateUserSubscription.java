package com.i3market.sdk.ri.common_services.alerts.subscriptions;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.notifications.SubscriptionApi;
//import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserSubscription {
    private static final Logger _log = LoggerFactory.getLogger(CreateUserSubscription.class);

    public CreateUserSubscription() {
    }

    public ApiResponse createUserSubscription (String user_id, String category) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("creating a user subscription for {} {}", user_id, category);
        SubscriptionApi subscriptionApi = new SubscriptionApi();

        response = subscriptionApi.postSubscriptionsWithHttpInfo(category, user_id);

        return response;
    }
}
