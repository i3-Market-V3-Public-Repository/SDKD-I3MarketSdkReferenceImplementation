package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.OfferingsList;
import com.i3m.model.backplane.ProvidersList;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

public class RetrieveListOfProvider {

    public ApiResponse<List<ProvidersList>> getProviderListByCategory(String category, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return  registrationOfferingApi.getProvidersListByCategoryGETWithHttpInfo(category, page, size, sort);

    }

    public ApiResponse<List<ProvidersList>> getAllProviders(Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return  registrationOfferingApi.getProvidersListUsingGETWithHttpInfo(page, size, sort);

    }
}
