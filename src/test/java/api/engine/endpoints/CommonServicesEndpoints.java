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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommonServicesEndpoints {
	
	
	public static Response getAuthToken() {
	    RestAssured.baseURI=Routes.security_token_uri;
	    Response response=RestAssured.
	         given().contentType("application/x-www-form-urlencoded").accept(ContentType.JSON).
	         body("grant_type=password&client_id=SDKGeneratorClient&client_secret=a4279d93-994d-4de4-a2d6-3b1660d07842&scope=openid&username=i3market&password=sgfjlsn44r50.,fsf03").
	         when().post(Routes.security_token_uri);
        return response;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// OFFERINGS ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response deleteOffering(String access_token, String id_token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().delete(Routes.delete_offering_by_id, id);
		return response;
	}
	
	public static Response deleteProvider(String access_token, String id_token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().delete(Routes.delete_provider_by_id, id);
		return response;
	}

	public static Response getContractByOfferingId(String access_token, String id_token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("offeringId", id).
				when().get(Routes.get_contract_parameters_by_offeringid);
		return response;
	}

	public static Response getContractByProviderId(String access_token, String id_token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("providerId", id).
				when().get(Routes.get_contract_parameters_by_providerid);
		return response;
	}

	public static Response getOfferingsList(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_data_offering_list);
		return response;
	}

	public static Response getProvidersList(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_data_providers_list);
		return response;
	}

	public static Response getProvidersByCategory(String access_token, String id_token, String category){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_data_providers_from_category, category);
		return response;
	}

	public static Response getOfferingTemplate(String access_token, String id_token) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("access_token", access_token).
	    	given().header("id_token", id_token).
	    	when().get(Routes.get_offering_template);
        return response;
	}
	
	public static Response searchOfferingByCategory(String access_token, String id_token, String category) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("access_token", access_token).
	    	given().header("id_token", id_token).
	    	pathParam("category", category).
	    	when().get(Routes.get_put_delete_offering_search_category);
        return response;
	}

	public static Response searchOfferingByOfferingId(String access_token, String id_token, String offeringId) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("id", offeringId).
				when().get(Routes.get_dataoffering_by_offeringid);
		return response;
	}

	public static Response searchOfferingByProviderId(String access_token, String id_token, String providerId) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("id", providerId).
				when().get(Routes.get_dataoffering_by_providerid);
		return response;
	}

	public static Response searchCatergoriesList(String access_token, String id_token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_categories_list);
		return response;
	}

	public static Response postDataProvider(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_data_provider);
		return response;
	}

	public static Response postDataOffering(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_data_offering);
		return response;
	}

	public static Response updateDataOffering(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).patch(Routes.patch_update_data_offering);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// NOTIFICATIONS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getUserNotifications(String access_token, String id_token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().get(Routes.get_post_user_notifications);
		return response;
	}

	public static Response postUserNotification(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.get_post_user_notifications);
		return response;
	}

	public static Response postServiceNotification(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_service_notifications);
		return response;
	}

	public static Response getUnreadUserNotifications(String access_token, String id_token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_unread_notifications);
		return response;
	}

	public static Response getUserNotificationsByUserId(String access_token, String id_token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("user_id", user_id).
				when().get(Routes.get_notifications_by_userId);
		return response;
	}

	public static Response getUnreadUserNotificationsByUserId(String access_token, String id_token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("user_id", user_id).
				when().get(Routes.get_unread_notifications_by_userId);
		return response;
	}

	public static Response getUserNotificationsByNotificationId(String access_token, String id_token, String notification_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("notification_id", notification_id).
				when().get(Routes.get_delete_notification_by_notificationId);
		return response;
	}

	public static Response deleteUserNotificationsByNotificationId(String access_token, String id_token, String notification_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("notification_id", notification_id).
				when().delete(Routes.get_delete_notification_by_notificationId);
		return response;
	}

	public static Response patchMarkNotificationAsRead(String access_token, String id_token, String notification_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("notification_id", notification_id).
				when().patch(Routes.patch_notification_read);
		return response;
	}

	public static Response patchMarkNotificationAsUnread(String access_token, String id_token, String notification_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("notification_id", notification_id).
				when().patch(Routes.patch_notification_unread);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getUserSubscriptions(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_subscriptions);
		return response;
	}

	public static Response getSubscriptionByUserId(String access_token, String id_token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("user_id", user_id).
				when().get(Routes.get_post_subscription_userId);
		return response;
	}

	public static Response postSubscriptionByUserId(String access_token, String id_token, String user_id, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("user_id", user_id).
				when().body(body).post(Routes.get_post_subscription_userId);
		return response;
	}

	public static Response getSubscriptionBySubscriptionId(String access_token, String id_token, String user_id, String subscription_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().get(Routes.get_delete_subscription_subscriptonId);
		return response;
	}

	public static Response deleteSubscriptionBySubscriptionId(String access_token, String id_token, String user_id,String subscription_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().delete(Routes.get_delete_subscription_subscriptonId);
		return response;
	}

	public static Response patchDeactivateSubscriptionById(String access_token, String id_token, String user_id,String subscription_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().patch(Routes.patch_subscrition_deactivate);
		return response;
	}

	public static Response patchActivateSubscriptionById(String access_token, String id_token, String user_id, String subscription_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().patch(Routes.patch_subscrition_activate);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getContractTemplateByIdOffering(String access_token, String id_token, String idOffering){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("idOffering", idOffering).
				when().get(Routes.get_contract_template_idOffering);
		return response;
	}

	public static Response createDataPurchaseByTemplate(String access_token, String id_token, String origin_market_id, String consumer_did, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				queryParam("origin_market_id", origin_market_id).
				queryParam("consumer_did", consumer_did).
				contentType("application/json").
				when().body(body).post(Routes.post_purchase_data_request);
			
		return response;
	}
	
	public static Response getAgreementByAgreementId(String access_token, String id_token, String agreement_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("agreement_id", agreement_id).
				contentType("application/json").
				when().get(Routes.get_agreement_by_agreement_id);
			
		return response;
	}
	
	public static Response getCheckAgreementsByConsumerId(String access_token, String id_token, String consumer_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("agreement_id", consumer_id).
				contentType("application/json").
				when().get(Routes.get_agreements_by_consumer_id);
			
		return response;
	}
	
	public static Response getCheckAgreementsByProviderID(String access_token, String id_token, String provider_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("agreement_id", provider_id).
				contentType("application/json").
				when().get(Routes.get_agreements_by_provider_id);
			
		return response;
	}
	
	public static Response getCheckActiveAgreements(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().get(Routes.get_active_agreements);
			
		return response;
	}
	
	public static Response getStateByAgreementId(String access_token, String id_token, String agreement_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("agreement_id", agreement_id).
				contentType("application/json").
				when().get(Routes.get_state_by_agreements_id);
			
		return response;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getIssuedCredentialList(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_issued_credential_list);
		return response;
	}

	public static Response getGenerateVerifiableCredential(String access_token, String id_token, String credential){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("credential", credential).
				when().get(Routes.get_generate_verifiable_credential);
		return response;
	}

	public static Response postRevokeVerifiableCredential(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_revoke_verifiable_credential);
		return response;
	}

	public static Response postVerifyCredential(String access_token, String id_token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_verify_credential);
		return response;
	}

	public static Response getSubscribeIssuer(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_subscribe_issuer);
		return response;
	}

	public static Response getUnsubscribeIssuer(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_unsubscribe_issuer);
		return response;
	}

	public static Response getVerifyIssuerSubscription(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_verify_issuer_subscription);
		return response;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response postCreateInvoice(String access_token, String id_token, String bearerToken, String fromDate, String toDate) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				queryParam("bearerToken", bearerToken).
				queryParam("fromDate", fromDate).
				queryParam("toDate", toDate).
				when().post(Routes.post_create_invoice);
		return response;
	}

	public static Response postDecryptCipherblock(String access_token, String id_token, String cipherblock, String jwk) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				queryParam("cipherblock", cipherblock).
				queryParam("jwk", jwk).
				when().post(Routes.post_decrypt_cipherblock);
		return response;
	}

	public static Response deleteFile(String access_token, String id_token, String data) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				queryParam("data", data).
				when().delete(Routes.delete_file);
		return response;
	}

	public static Response postDownloadFile(String access_token, String id_token, String data) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				queryParam("data", data).
				when().post(Routes.post_download_file);
		return response;
	}

	public static Response postGetDataBlock(String access_token, String id_token, String bearerToken, String data, String blockId, String blockAck){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("data", data).
				queryParam("bearerToken", bearerToken).
				queryParam("block_id", blockId).
				queryParam("block_ack", blockAck).
				when().post(Routes.post_get_data_block);
		return response;
	}

	public static Response postGetFile(String access_token, String id_token, String bearerToken, String data){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("data", data).
				queryParam("bearerToken", bearerToken).
				when().post(Routes.post_get_file);
		return response;
	}

	public static Response getJwk(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_jwk);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getSdkriConfigInfo(String access_token, String id_token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				when().get(Routes.get_sdkri_config_info);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getMarketplaceBalanceByAddress(String access_token, String id_token, String address){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("address", address).
				when().get(Routes.get_marketplace_balance_by_address);
		return response;
	}

	public static Response postClearingOperation(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_clearing_operation);
		return response;
	}

	public static Response postDeployTransaction(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_deploy_operation);
		return response;
	}

	public static Response postExchangeIn(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_exchange_in_operation);
		return response;
	}

	public static Response postExchangeOut(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_exchange_out_operation);
		return response;
	}

	public static Response postAddMarketplace(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_new_marketplace);
		return response;
	}

	public static Response getMarketplaceByAddress(String access_token, String id_token, String address){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				pathParam("address", address).
				when().post(Routes.get_marketpace_index_by_address);
		return response;
	}

	public static Response postTokenPaymentOperation(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_token_payment_operation);
		return response;
	}

	public static Response postSetPaidStatusOperation(String access_token, String id_token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				contentType("application/json").
				when().body(body).post(Routes.post_set_paid_status_operation);
		return response;
	}

	public static Response getTransactionStatusByTransferId(String access_token, String id_token, String transferId){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("transferId", transferId).
				when().get(Routes.get_transaction_status_by_transferId);
		return response;
	}

	public static Response getTransactionByTransactionHash(String access_token, String id_token, String transactionHash){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("access_token", access_token).
				given().header("id_token", id_token).
				pathParam("transactionHash", transactionHash).
				when().get(Routes.get_transaction_by_transactionHash);
		return response;
	}

}
