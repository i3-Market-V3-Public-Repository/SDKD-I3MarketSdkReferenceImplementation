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
package com.i3market.sdk.ri.common_services.data.discovery;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3m.model.backplane.SemanticEngineOfferingsList;
import com.i3m.model.backplane.SemanticEngineProvidersList;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

public class RetrieveListOfProvider {

    public List<SemanticEngineProvidersList> getProviderListByCategory(String access_token, String id_token, String category, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", id_token);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return registrationOfferingApi.getProvidersListByCategoryGET(category, page, size, sort);

    }

    public ApiResponse<List<SemanticEngineProvidersList>> getAllProviders(String access_token, String id_token, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

        return  registrationOfferingApi.getProvidersListUsingGETWithHttpInfo(page, size, sort);

    }
    public ApiResponse<List<SemanticEngineProvidersList>> getFederatedProvidersList(String access_token, String id_token, Integer page, Integer size, List<String> sort) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();

//        registrationOfferingApi.getAllFederatedRegisteredOfferingsByTextUsingGET()
      return  registrationOfferingApi.getFederatedProvidersListUsingGETWithHttpInfo(page, size, sort);


    }
}
