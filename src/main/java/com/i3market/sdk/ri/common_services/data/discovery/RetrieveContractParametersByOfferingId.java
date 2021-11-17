package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.ContractParameters;
import com.i3m.model.backplane.ContractsParametersForOfferings;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

/**
 * Implemented by: Chi-Hung Le
 * @email: chi-hung.le@insight-centre.org
 */


public class RetrieveContractParametersByOfferingId {
    public ApiResponse<List<ContractsParametersForOfferings>> getOfferingContractsByOfferingId(String offeringId, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return  registrationOfferingApi.getOfferingContractsByOfferingIdUsingGETWithHttpInfo(offeringId, page, size, sort);

    }
}