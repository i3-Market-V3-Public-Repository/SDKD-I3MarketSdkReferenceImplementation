package com.i3market.sdk.ri.common_services.data.offering;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.DataOfferingID;
import com.i3m.model.backplane.OfferingsList;
import com.i3m.model.backplane.ProvidersList;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingById;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CreateOffering {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    public ApiResponse<List<DataOfferingID>> createOffering (DataOffering dataOffering) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        _log.debug("creating a data offering {} ", dataOffering);
        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return registrationOfferingApi.dataOfferingUsingPOSTWithHttpInfo(dataOffering);

//        return new ApiResponse(HttpStatus.SC_OK, null);

    }

}
