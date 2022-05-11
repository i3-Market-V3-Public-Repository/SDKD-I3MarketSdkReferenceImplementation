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
package com.i3market.sdk.ri.common_services.pricingManager;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.CostControllerApi;
import com.i3m.api.backplane.PriceControllerApi;
import com.i3m.model.backplane.FormulaConfig;
import com.i3m.model.backplane.FormulaConstantConfiguration;
import com.i3m.model.backplane.FormulaParameterConfiguration;
import com.i3m.model.backplane.FormulaWithConfiguration;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PricingManager {

    private static final Logger _log = LoggerFactory.getLogger(CreateOffering.class);

    /*
    *   GET /pricingManager/fee/getfee  -  get I3M fee
    */
    public Object getFee(String access_token, String id_token, String price) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("GET /pricingManager/fee/getfee  -  get I3M fee");
        CostControllerApi costApi = new CostControllerApi();

        return costApi.get(price);
    }

    /*
     *   PUT /pricingManager/fee/setfee  -  set I3M fee
     */
    public Object setFee(String access_token, String id_token, String fee) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("PUT /pricingManager/fee/setfee  -  set I3M fee");
        CostControllerApi costApi = new CostControllerApi();

        return costApi.put(fee);
    }

    /*
     *   GET /pricingManager/price/checkformulaconfiguration  -  Check formula and parameters consistency
     */
    public Object getCheckFormulaConfiguration(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("GET /pricingManager/price/checkformulaconfiguration  -  Check formula and parameters consistency");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.checkformulaconfiguration();
    }

    /*
     *   GET /price/getformulajsonconfiguration  -  Get configuration using Json format
     */
    public Object getFormulaJsonConfiguration(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("GET /price/getformulajsonconfiguration  -  Get configuration using Json format");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.getFormulaConfiguration();
    }

    /*
     *   GET /price/getprice  -  Get the price of data
     */
    public Object getPrice(String access_token, String id_token, Map<String, String> parameters) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("GET /price/getprice  -  Get the price of data");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.getPrice(parameters);
    }

    /*
     *   PUT /price/setformulaconstant  -  Set formula constant
     */
    public Object setFormulaConstant(String access_token, String id_token, FormulaConstantConfiguration formulaParameter) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("PUT /price/setformulaconstant  -  Set formula constant");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.configureFormulaConstants(formulaParameter);
    }

    /*
     *   PUT /price/setformulajsonconfiguration  -  Set configuration using Json format
     */
    public Object setFormulajsonConfiguration(String access_token, String id_token, FormulaConfig formulaParameter) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("PUT /price/setformulajsonconfiguration  -  Set configuration using Json format");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.setFormulaConfiguration(formulaParameter);
    }

    /*
     *   PUT /pricingManager/price/setformulaparameter  -  Set formula parameter
     */
    public Object setFormulaParameter(String access_token, String id_token, FormulaParameterConfiguration parameter) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("PUT /pricingManager/price/setformulaparameter  -  Set formula parameter");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.configureFormulaParameters(parameter);
    }


    /*
     *   PUT /pricingManager/price/setformulawithdefaultconfiguration  -  Set Formula with default values for constants and parameters
     */
    public Object setFormulaWithDefaultConfiguration(String access_token, String id_token, FormulaWithConfiguration parameter) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;

        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);

        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        _log.debug("PUT /pricingManager/price/setformulawithdefaultconfiguration  -  Set Formula with default values for constants and parameters");

        PriceControllerApi priceApi = new PriceControllerApi();
        return priceApi.setFormulaWithConfiguration(parameter);
    }


}