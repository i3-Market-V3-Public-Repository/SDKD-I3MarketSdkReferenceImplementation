package com.i3market.sdk.ri.common_services.notification.services;
import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.QueuesApi;
import com.i3m.model.backplane.NotificationManagerOasService;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implemented by: J. Eleazar Escudero
 * @email: eleazar@hopu.org
 */
public class GetService {
    private static final Logger _log = LoggerFactory.getLogger(GetService.class);

    //public GetService() {
    //}
    public ApiResponse<List<NotificationManagerOasService>> getServices (String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        _log.debug("Getting all registered services");
        QueuesApi queuesApi = new QueuesApi();
        return queuesApi.getApiV1ServicesWithHttpInfo();
    }
    public ApiResponse<NotificationManagerOasService> getServiceById (String access_token, String id_token, String serviceId) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        _log.debug("Getting the service: {}", serviceId);
        QueuesApi queuesApi = new QueuesApi();
        return queuesApi.getApiV1ServicesByServiceIdWithHttpInfo(serviceId);

    }
}