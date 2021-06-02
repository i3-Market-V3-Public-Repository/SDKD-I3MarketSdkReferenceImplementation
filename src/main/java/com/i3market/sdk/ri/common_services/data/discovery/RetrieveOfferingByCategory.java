package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.RegistrationOfferingDTO;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.Collections;
import java.util.List;

/**
 * @author qaiser
 * @email: qaiser.mehmood@insight-centre.org
 * @project i3-sdk-ri
 */
public class RetrieveOfferingByCategory {

    public ApiResponse<List<RegistrationOfferingDTO>> getOfferingByCategory(String category, int page, int size, String sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return (ApiResponse<List<RegistrationOfferingDTO>>) registrationOfferingApi.getAllRegisteredOfferingsByCategoryUsingGET(category, page, size, Collections.singletonList(sort));

    }
}
