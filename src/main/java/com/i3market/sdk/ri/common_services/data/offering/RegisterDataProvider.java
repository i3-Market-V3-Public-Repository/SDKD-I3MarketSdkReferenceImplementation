package com.i3market.sdk.ri.common_services.data.offering;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.DataProvider;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

/**
 * @author qaiser
 * @email: qaiser.mehmood@insight-centre.org
 * @project i3m-sdk-reference-implementation
 */
public class RegisterDataProvider {

    private static final Logger _log = LoggerFactory.getLogger(RegisterDataProvider.class);
    public RegisterDataProvider() {
    }

    public ApiResponse saveDataProviderInfo (DataProvider dataProvider) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("storing provider information {} ", dataProvider);
        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        registrationOfferingApi.saveDataProviderUsingPOSTWithHttpInfo(dataProvider);

        return new ApiResponse(HttpStatus.SC_OK, null);


    }
}
