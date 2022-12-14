package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.SemanticEngineOfferingsList;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

public class RetrieveFederatedOfferingList {
    public ApiResponse<List<SemanticEngineOfferingsList>> getDataOfferingList(String access_token, String id_token, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

       return registrationOfferingApi.getFederatedOfferingsListUsingGETWithHttpInfo(page,size,sort);


    }
}
