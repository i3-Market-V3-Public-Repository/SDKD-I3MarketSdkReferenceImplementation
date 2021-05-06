package com.i3market.sdk.ri.examples;

import java.util.logging.Logger;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.HttpBasicAuth;
import com.i3m.api.backplane.HelloControllerApi;
import com.i3m.api.oidc.OidcCoreApi;
import com.i3m.api.oidc.OidcDiscoveryApi;
import com.i3m.model.backplane.HelloResponse;
import com.i3m.model.oidc.JWKSet;
import com.i3m.model.oidc.OpenIDProviderMetadata;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import com.i3market.sdk.ri.resource.SdkRiHub;

public class PocOIDC {
	
	private Logger LOGGER = Logger.getLogger(SdkRiHub.class.getName());
	
	public PocOIDC(){
		
	}
	
	public ApiResponse<JWKSet> getJWKS () throws ApiException {
		
		String basePath = SdkRiConstants.OIDC_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);
		
		// Configure Authentication if it is required
        // HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        // BasicAuth.setUsername("YOUR USERNAME");
        // BasicAuth.setPassword("YOUR PASSWORD");
		
		OidcCoreApi oidcController = new OidcCoreApi();
		LOGGER.info(" ### ApiClient Base path: " + oidcController.getApiClient().getBasePath());
		return  oidcController.oidcJwksGetWithHttpInfo();
		
	}

}
