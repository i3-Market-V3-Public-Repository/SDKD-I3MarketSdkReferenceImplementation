package com.i3market.sdk.ri.common_services.data.offering;

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
