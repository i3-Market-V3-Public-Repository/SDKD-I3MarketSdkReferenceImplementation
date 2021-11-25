package com.i3market.sdk.ri.common_services.notification;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.NotificationsApi;
import com.i3m.model.backplane.Notification;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

public class RetrieveNotifications {

    public ApiResponse<List<Notification>> getAllNotifications() throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationWithHttpInfo();

    }

    public ApiResponse<List<Notification>> getAllUnreadNotifications() throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUnreadWithHttpInfo();

    }

    public ApiResponse<List<Notification>> getUserNotifications(String user_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUserByUserIdWithHttpInfo(user_id);

    }

    public ApiResponse<List<Notification>> getUserUnreadNotifications(String user_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUserByUserIdUnreadWithHttpInfo(user_id);

    }

    public ApiResponse<Notification> getNotificationsByNotificationId(String notification_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationByNotificationIdWithHttpInfo(notification_id);

    }

}

