package com.i3market.sdk.ri.common_services.notification.services;
import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.QueuesApi;
import com.i3m.model.backplane.NotificationManagerOasQueue;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Implemented by: J. Eleazar Escudero
 * @email: eleazar@hopu.org
 */
public class GetServiceQueue {
    private static final Logger _log = LoggerFactory.getLogger(RegisterServiceQueue.class);

    //public GetServiceQueue() {
    //}

    public ApiResponse<NotificationManagerOasQueue> getServiceQueueByQueueId (String access_token, String id_token, String serviceId, String queueId) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        _log.debug("Getting queue service_id:{} queue_id:{}", serviceId, queueId);
        QueuesApi queuesApi = new QueuesApi();
        return queuesApi.getApiV1ServicesByServiceIdQueuesByQueueIdWithHttpInfo(serviceId, queueId);

    }
}