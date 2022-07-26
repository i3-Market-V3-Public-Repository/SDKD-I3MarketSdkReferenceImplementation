package com.i3market.sdk.ri.common_services.notification.services;
import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.QueuesApi;
import com.i3m.model.backplane.NotificationManagerOasService;
import com.i3m.model.backplane.NotificationManagerOasServiceInput;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Implemented by: J. Eleazar Escudero
 * @email: eleazar@hopu.org
 */
public class RegisterService {
    private static final Logger _log = LoggerFactory.getLogger(RegisterService.class);
    //public RegisterService() {
    //}

    public ApiResponse<NotificationManagerOasService> registerService (String access_token, String id_token, NotificationManagerOasServiceInput notificationManagerOasServiceInput) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        _log.debug("Registering a new service: {}", notificationManagerOasServiceInput);
        QueuesApi queuesApi = new QueuesApi();
        return queuesApi.postApiV1ServicesWithHttpInfo(notificationManagerOasServiceInput);

    }
}