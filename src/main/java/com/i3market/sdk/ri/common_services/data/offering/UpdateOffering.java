package com.i3market.sdk.ri.common_services.data.offering;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.DataOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;

/**
 * @author qaiser
 * @email: qaiser.mehmood@insight-centre.org
 * @project i3-sdk-ri
 */
public class UpdateOffering {

    public ApiResponse updateOffering(DataOffering dataOffering) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        registrationOfferingApi.updateOfferingUsingPATCH(dataOffering);

        return new ApiResponse(HttpStatus.SC_OK, null);
    }
}
