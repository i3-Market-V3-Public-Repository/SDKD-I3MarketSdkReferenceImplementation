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
package api.engine.endpoints;

/**
 * Routes class allow the testers to design the end-points for all services that are  available
 */
public class Routes {
	
	// The security server uri
	public static String security_token_uri= "http://xxxxxx:8080/auth/realms/i3market/protocol/openid-connect/token";
	
	// The base uri is the resource where all the common services are running.
	public static String base_uri  ="http://xxxxxx:8182/SdkRefImpl";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// OFFERINGS ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get routes the request to respective services to delete a data offering by offeringId
	public static String delete_offering_by_id= "/api/sdk-ri/delete-offering/{id}";

	// The get routes the request to respective services to retrieve contract parameters by offeringId
	public static String get_contract_parameters_by_offeringid= "/api/sdk-ri/offering/contract-parameter/{offeringId}/offeringId";

	// The get routes the request to respective services to retrieve contract parameters by providerId
	public static String get_contract_parameters_by_providerid= "/api/sdk-ri/offering/contract-parameter/{providerId}/providerId";

	// The get routes the request to respective services to retrieve offering list from internal database only
	public static String get_data_offering_list= "/api/sdk-ri/offering/offerings-list";

	// The get routes the request to respective services to to retrieve data provider list from internal database
	public static String get_data_providers_list= "/api/sdk-ri/offering/providers-list";

	// The POST routes the request to respective services to retrieve provider list by category from internal database only
	public static String get_data_providers_from_category= "/api/sdk-ri/offering/providers/{category}/category";

	// The get routes the request to respective services to read an offering template.
	public static String get_offering_template= "/api/sdk-ri/offering/template";

	// The get,put,delete uri routes the request to respective services to read,update,delete an offering by category.
	public static String get_put_delete_offering_search_category= "/api/sdk-ri/offering/{category}";

	// The get,put,delete uri routes the request to respective services to read an offering by offering id.
	public static String get_dataoffering_by_offeringid= "/api/sdk-ri/offering/{id}/offeringId";

	// The get,put,delete uri routes the request to respective services to read an offering by provider id.
	public static String get_dataoffering_by_providerid= "/api/sdk-ri/offering/{id}/providerId";

	// The get,put,delete uri routes the request to retrieve category list
	public static String get_categories_list= "/api/sdk-ri/registration/categories-list";

	// The post routes the request to respective services to register a data provider
	public static String post_data_provider= "/api/sdk-ri/registration/data-provider";

	// The post routes the request to respective services to register a data offering
	public static String post_data_offering= "/api/sdk-ri/registration/data-offering";

	// The post routes the request to respective services to update a data offering
	public static String patch_update_data_offering = "/api/sdk-ri/update-offering";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// NOTIFICATIONS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_post_user_notifications="/api/sdk-ri/notification";

	// The get method routes the request to respective services to
	public static String post_service_notifications="/api/sdk-ri/notification/service";

	// The get method routes the request to respective services to
	public static String get_unread_notifications="/api/sdk-ri/notification/unread";

	// The get method routes the request to respective services to
	public static String get_notifications_by_userId="/api/sdk-ri/notification/user/{user_id}";

	// The get method routes the request to respective services to
	public static String get_unread_notifications_by_userId="/api/sdk-ri/notification/user/{user_id}/unread";

	// The get method routes the request to respective services to
	public static String get_delete_notification_by_notificationId="/api/sdk-ri/notification/{notification_id}";

	// The get method routes the request to respective services to
	public static String patch_notification_read="/api/sdk-ri/notification/{notification_id}/read";

	// The get method routes the request to respective services to
	public static String patch_notification_unread="/api/sdk-ri/notification/{notification_id}/unread";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_subscriptions="/api/sdk-ri/alerts/users/subscriptions";

	// The get method routes the request to respective services to
	public static String get_post_subscription_userId="/api/sdk-ri/alerts/users/{user_id}/subscriptions";

	// The get method routes the request to respective services to
	public static String get_delete_subscription_subscriptonId="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}";

	// The get method routes the request to respective services to
	public static String patch_subscrition_activate="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}/activate";

	// The get method routes the request to respective services to
	public static String patch_subscrition_deactivate="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}/deactivate";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_contract_template_idOffering="/api/sdk-ri/contract/get-contract-template/{idOffering}";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_issued_credential_list="/api/sdk-ri/credential/";

	// The get method routes the request to respective services to
	public static String get_generate_verifiable_credential="/api/sdk-ri/credential/issue/{credential}";

	// The get method routes the request to respective services to
	public static String post_revoke_verifiable_credential="/api/sdk-ri/credential/revoke";

	// The get method routes the request to respective services to
	public static String post_verify_credential="/api/sdk-ri/credential/verify";

	// The get method routes the request to respective services to
	public static String get_subscribe_issuer="/api/sdk-ri/issuer/subscribe";

	// The get method routes the request to respective services to
	public static String get_unsubscribe_issuer="/api/sdk-ri/issuer/unsubscribe";

	// The get method routes the request to respective services to
	public static String get_verify_issuer_subscription="/api/sdk-ri/issuer/verify";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String post_create_invoice="/api/sdk-ri/create-invoice";

	// The get method routes the request to respective services to
	public static String post_decrypt_cipherblock="/api/sdk-ri/decrypt";

	// The get method routes the request to respective services to
	public static String delete_file="/api/sdk-ri/delete-file";

	// The get method routes the request to respective services to
	public static String post_download_file="/api/sdk-ri/download-file";

	// The get method routes the request to respective services to
	public static String post_get_data_block="/api/sdk-ri/get-block/{data}";

	// The get method routes the request to respective services to
	public static String post_get_file="/api/sdk-ri/get-file/{data}";

	// The get method routes the request to respective services to
	public static String get_jwk="/api/sdk-ri/get-jwk";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_sdkri_config_info="/api/sdk-ri/getConfigurationInfo";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to
	public static String get_marketplace_balance_by_address="/api/sdk-ri/token/balances/{address}";

	// The get method routes the request to respective services to
	public static String post_clearing_operation="/api/sdk-ri/token/clearing";

	// The get method routes the request to respective services to
	public static String post_deploy_operation="/api/sdk-ri/token/deploytransaction";

	// The get method routes the request to respective services to
	public static String post_exchange_in_operation="/api/sdk-ri/token/exchangein";

	// The get method routes the request to respective services to
	public static String post_exchange_out_operation="/api/sdk-ri/token/exchangeout";

	// The get method routes the request to respective services to
	public static String post_new_marketplace="/api/sdk-ri/token/marketplace";

	// The get method routes the request to respective services to
	public static String get_marketpace_index_by_address="/api/sdk-ri/token/marketplaces/{address}";

	// The get method routes the request to respective services to
	public static String post_token_payment_operation="/api/sdk-ri/token/payment";

	// The get method routes the request to respective services to
	public static String post_set_paid_status_operation="/api/sdk-ri/token/setpaid";

	// The get method routes the request to respective services to
	public static String get_transaction_status_by_transferId="/api/sdk-ri/token/token-transfer/{transferId}";

	// The get method routes the request to respective services to
	public static String get_transaction_by_transactionHash="/api/sdk-ri/token/token-transfer/{transactionHash}";


}
