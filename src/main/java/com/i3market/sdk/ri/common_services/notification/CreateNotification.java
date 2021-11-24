package com.i3market.sdk.ri.common_services.notification;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.NotificationsApi;
import com.i3m.model.backplane.ServiceNotification;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateNotification {

    private static final Logger _log = LoggerFactory.getLogger(com.i3market.sdk.ri.common_services.notification.CreateNotification.class);

    //public CreateNotification() {
    //}

    public ApiResponse<Object> createServiceNotification (ServiceNotification body) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("creating a service notification {} ", body);
        NotificationsApi notificationsApi = new NotificationsApi();
        return notificationsApi.postApiV1NotificationServiceWithHttpInfo(body);

    }


}

