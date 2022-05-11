/*
  Copyright 2020-2022 i3-MARKET Consortium:

  ATHENS UNIVERSITY OF ECONOMICS AND BUSINESS - RESEARCH CENTER
  ATOS SPAIN SA
  EUROPEAN DIGITAL SME ALLIANCE
  GFT ITALIA SRL
  GUARDTIME OU
  HOP UBIQUITOUS SL
  IBM RESEARCH GMBH
  IDEMIA FRANCE
  SIEMENS AKTIENGESELLSCHAFT
  SIEMENS SRL
  TELESTO TECHNOLOGIES PLIROFORIKIS KAI EPIKOINONION EPE
  UNIVERSITAT POLITECNICA DE CATALUNYA
  UNPARALLEL INNOVATION LDA

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.i3market.sdk.ri.common_services.purchase;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.Configuration;
import com.i3m.api.smart_contract.AgreementApi;
import com.i3m.model.smart_contract.Template;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

public class BackplaneClient {
	
	public Template getTemplate (String access_token, String id_token, String idTemplate) throws ApiException {
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
        
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);
		
        //Add token as headers
		defaultClient.addDefaultHeader("access_token", access_token);
		defaultClient.addDefaultHeader("id_token", id_token);
		
		AgreementApi controller = new AgreementApi();
		return controller.templateOfferingIdGet(idTemplate);
	}

}
