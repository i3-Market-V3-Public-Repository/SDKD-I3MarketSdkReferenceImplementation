package com.i3market.sdk.ri.common_services.purchase;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.smart_contract.AgreementApi;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.model.smart_contract.Template;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.Map;

public class BackplaneClient {
	
	public Template getTemplate (String bearerToken, String idTemplate) throws ApiException {
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);

	    // Setup authentications (JWT).
		String jwt = "Bearer " + bearerToken;

		Map<String, Authentication> authentications = defaultClient.getAuthentications();
		HttpBearerAuth bearerAuth = new HttpBearerAuth(null);
		bearerAuth.setBearerToken(jwt);
		System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("bearerAuth", bearerAuth);
		
		AgreementApi controller = new AgreementApi();
		return  controller.templateTemplateIdGet(idTemplate);
	}

}
