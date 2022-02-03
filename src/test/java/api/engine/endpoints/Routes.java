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
	public static String security_token_uri= "http://83.149.125.78:8080/auth/realms/i3market/protocol/openid-connect/token";
	
	// The base uri is the resource where all the common services are running.
	public static String base_uri  ="http://localhost:9191/SdkRefImpl";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// OFFERINGS ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get routes the request to respective services to delete a data offering by offeringId
	public static String delete_offering_by_id= "/api/sdk-ri/delete-offering/{id}";
	
	// The get routes the request to respective services to delete a provider by providerId
	public static String delete_provider_by_id= "/api/sdk-ri/registration/data-provider/{providerId}";

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

	// The get/post method routes the request to respective services to get all user notifications or create one
	public static String get_post_user_notifications="/api/sdk-ri/notification";

	// The post method routes the request to respective services to create a service notification
	public static String post_service_notifications="/api/sdk-ri/notification/service";

	// The get method routes the request to respective services to get all the unread notifications
	public static String get_unread_notifications="/api/sdk-ri/notification/unread";

	// The get method routes the request to respective services to get the notifications by user id
	public static String get_notifications_by_userId="/api/sdk-ri/notification/user/{user_id}";

	// The get method routes the request to respective services to get a user unread notifications by user id
	public static String get_unread_notifications_by_userId="/api/sdk-ri/notification/user/{user_id}/unread";

	// The get/delete method routes the request to respective services to get or delete a notification by notification id
	public static String get_delete_notification_by_notificationId="/api/sdk-ri/notification/{notification_id}";

	// The patch method routes the request to respective services to mark a notification as read
	public static String patch_notification_read="/api/sdk-ri/notification/{notification_id}/read";

	// The patch method routes the request to respective services to mark a notification as unread
	public static String patch_notification_unread="/api/sdk-ri/notification/{notification_id}/unread";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to get all the registered users subscriptions
	public static String get_subscriptions="/api/sdk-ri/alerts/users/subscriptions";

	// The get/post method routes the request to respective services to get all user subscriptions or register one
	public static String get_post_subscription_userId="/api/sdk-ri/alerts/users/{user_id}/subscriptions";

	// The get/delete method routes the request to respective services to get a subscription by subscription id or delete it
	public static String get_delete_subscription_subscriptonId="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}";

	// The patch method routes the request to respective services to activate a user subscription
	public static String patch_subscrition_activate="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}/activate";

	// The patch method routes the request to respective services to deactivate a user subscription
	public static String patch_subscrition_deactivate="/api/sdk-ri/alerts/users/{user_id}/subscriptions/{subscription_id}/deactivate";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to retrieve the contract template of an offering by offering id
	public static String get_contract_template_idOffering="/api/sdk-ri/contract/get-contract-template/{idOffering}";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to get the issued credential list
	public static String get_issued_credential_list="/api/sdk-ri/credential/";

	// The get method routes the request to respective services to generate a verifiable credential
	public static String get_generate_verifiable_credential="/api/sdk-ri/credential/issue/{credential}";

	// The get method routes the request to respective services to revoke a credential by jwt
	public static String post_revoke_verifiable_credential="/api/sdk-ri/credential/revoke";

	// The get method routes the request to respective services to verify a credential by jwt
	public static String post_verify_credential="/api/sdk-ri/credential/verify";

	// The get method routes the request to respective services to subscribe the issuer
	public static String get_subscribe_issuer="/api/sdk-ri/issuer/subscribe";

	// The get method routes the request to respective services to unsubscribe the issuer
	public static String get_unsubscribe_issuer="/api/sdk-ri/issuer/unsubscribe";

	// The get method routes the request to respective services to verify the issuer subscription
	public static String get_verify_issuer_subscription="/api/sdk-ri/issuer/verify";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The post method routes the request to respective services to create an invoice
	public static String post_create_invoice="/api/sdk-ri/create-invoice";

	// The post method routes the request to respective services to decrypt a cipherblock
	public static String post_decrypt_cipherblock="/api/sdk-ri/decrypt";

	// The post method routes the request to respective services to delete a file
	public static String delete_file="/api/sdk-ri/delete-file";

	// The post method routes the request to respective services to download a file
	public static String post_download_file="/api/sdk-ri/download-file";

	// The post method routes the request to respective services to get data block
	public static String post_get_data_block="/api/sdk-ri/get-block/{data}";

	// The post method routes the request to respective services to get file
	public static String post_get_file="/api/sdk-ri/get-file/{data}";

	// The get method routes the request to respective services to get jwk
	public static String get_jwk="/api/sdk-ri/get-jwk";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to get sdk-ri configuration info
	public static String get_sdkri_config_info="/api/sdk-ri/getConfigurationInfo";

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// The get method routes the request to respective services to obtain the marketplace balance by marketplace address
	public static String get_marketplace_balance_by_address="/api/sdk-ri/token/balances/{address}";

	// The post method routes the request to respective services to clearing operation
	public static String post_clearing_operation="/api/sdk-ri/token/clearing";

	// The post method routes the request to respective services to deploy operation
	public static String post_deploy_operation="/api/sdk-ri/token/deploytransaction";

	// The post method routes the request to respective services to exchange in operation
	public static String post_exchange_in_operation="/api/sdk-ri/token/exchangein";

	// The post method routes the request to respective services to exchange out operation
	public static String post_exchange_out_operation="/api/sdk-ri/token/exchangeout";

	// The post method routes the request to respective services to add a new marketplace to the treasury smart contract and create a new token
	public static String post_new_marketplace="/api/sdk-ri/token/marketplace";

	// The get method routes the request to respective services to get marketplace index by marketplace address
	public static String get_marketpace_index_by_address="/api/sdk-ri/token/marketplaces/{address}";

	// The post method routes the request to respective services to token payment operation
	public static String post_token_payment_operation="/api/sdk-ri/token/payment";

	// The post method routes the request to respective services to set paid status of operation
	public static String post_set_paid_status_operation="/api/sdk-ri/token/setpaid";

	// The get method routes the request to respective services to get the transaction status object with transfer identifier
	public static String get_transaction_status_by_transferId="/api/sdk-ri/token/token-transfer/{transferId}";

	// The get method routes the request to respective services to get transaction with transaction hash
	public static String get_transaction_by_transactionHash="/api/sdk-ri/token/token-transfer/{transactionHash}";


}