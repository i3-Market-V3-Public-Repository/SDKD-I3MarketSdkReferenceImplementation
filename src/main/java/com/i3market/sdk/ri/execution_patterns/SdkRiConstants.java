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

package com.i3market.sdk.ri.execution_patterns;

// SDK RI Constans
public final class SdkRiConstants {
	
	// SdkRiConfiguration file path
	public static final String PATH_CONFFILE = "sdk_ri_config.properties";
	
	// BackplaneAPI public endpoint (URL)
	public static final String BACKPLANE_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.BACKPLANE_URL);
	
	// OIDC API public endpoint (URL)
	public static final String OIDC_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.OIDC_URL);
		
	// Verifiable Credentials API public endpoint (URL)
	public static final String VC_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.VC_URL);
	
	// Data Access API public endpoint (URL)
	public static final String DATA_ACCESS_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.DATA_ACCESS_URL);
	
	// SW Wallet API public endpoint (URL)
	public static final String SW_WALLET_ENDPOINT = SdkRiConfiguration.getInstance().getProperty(SdkRiProperties.SW_WALLET_URL);

}
