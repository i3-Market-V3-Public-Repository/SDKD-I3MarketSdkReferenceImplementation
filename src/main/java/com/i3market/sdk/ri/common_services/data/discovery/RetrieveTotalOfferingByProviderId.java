package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class RetrieveTotalOfferingByProviderId {

    private static final Logger _log = LoggerFactory.getLogger(RetrieveOfferingTemplate.class);

    public ApiResponse<List<BigDecimal>> getTotalOfferingByProviderId(String id) throws ApiException {
        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("Get total offering by providerID");
        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return registrationOfferingApi.getTotalOfferingByProviderIdGETWithHttpInfo(id);
    }
}

