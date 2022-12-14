package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.SemanticEngineDataOfferingDto;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RetrieveFederatedOfferingById {
    private static final Logger _log = LoggerFactory.getLogger(RetrieveOfferingById.class);


    public ApiResponse<SemanticEngineDataOfferingDto> getDataOfferingById(String access_token, String id_token, String id, int page, int size, List<String> sort) throws ApiException {


        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        _log.debug("parameters to get offering by id are: provider_id {} page {} size {} sort {} ", id, page, size, sort);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

       return registrationOfferingApi.getRegisteredFederatedOfferingUsingGETWithHttpInfo(id ,page,size,sort);


    }
}
