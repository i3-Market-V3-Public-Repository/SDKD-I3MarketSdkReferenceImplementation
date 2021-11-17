/*
 * Copyright 2019 Atos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.i3market.sdk.ri.examples;

import java.util.HashMap;
import java.util.Map;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBasicAuth;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.api.backplane.HelloControllerApi;
import com.i3m.api.backplane.PingControllerApi;
import com.i3m.model.backplane.HelloResponse;
import com.i3m.model.backplane.PingResponse;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

public class PocBackplane {
	
	public PocBackplane(){
		
	}
	
	public PingResponse ping (String jwt) throws ApiException {
		
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);
		
	    // Setup authentications (JWT).
		Map<String, Authentication> authentications = defaultClient.getAuthentications();
		HttpBearerAuth jwtAuth = new  HttpBearerAuth(null);
		jwtAuth.setBearerToken(jwt);
		authentications.put("jwt", jwtAuth);
		
		PingControllerApi controller = new PingControllerApi();
		return  controller.pingControllerPing();
		
	}

}
