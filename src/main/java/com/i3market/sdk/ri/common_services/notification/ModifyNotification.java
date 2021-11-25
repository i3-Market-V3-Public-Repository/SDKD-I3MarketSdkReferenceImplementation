package com.i3market.sdk.ri.common_services.notification;
import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.NotificationsApi;
import com.i3m.model.backplane.Notification;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModifyNotification {
    private static final Logger _log = LoggerFactory.getLogger(com.i3market.sdk.ri.common_services.notification.ModifyNotification.class);

    public ApiResponse<Notification> markAsReadNotification (String notification_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("Marking notification {} as read", notification_id);
        NotificationsApi notificationsApi = new NotificationsApi();
        return notificationsApi.patchApiV1NotificationByNotificationIdReadWithHttpInfo(notification_id);

    }
    public ApiResponse<Notification> markAsUnreadNotification (String notification_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("Marking notification {} as unread", notification_id);
        NotificationsApi notificationsApi = new NotificationsApi();
        return notificationsApi.patchApiV1NotificationByNotificationIdUnreadWithHttpInfo(notification_id);

    }
}
