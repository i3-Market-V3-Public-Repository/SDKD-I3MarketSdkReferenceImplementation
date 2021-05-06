package com.i3market.sdk.ri.examples;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.HttpBasicAuth;
import com.i3m.api.backplane.HelloControllerApi;
import com.i3m.api.backplane.PingControllerApi;
import com.i3m.model.backplane.HelloResponse;
import com.i3m.model.backplane.PingResponse;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

public class PocBackplane {
	
	public PocBackplane(){
		
	}
	
	public PingResponse ping () throws ApiException {
		
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);
		
		// TO-DO Configure JWT Authentication
        // HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        // BasicAuth.setUsername("YOUR USERNAME");
        // BasicAuth.setPassword("YOUR PASSWORD");
		
		PingControllerApi controller = new PingControllerApi();
		return  controller.pingControllerPing();
		
	}

}
