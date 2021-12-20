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

	public static Response deleteOffering(String token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().delete(Routes.delete_offering_by_id, id);
		return response;
	}

	public static Response getContractByOfferingId(String token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("offeringId", id).
				when().get(Routes.get_contract_parameters_by_offeringid);
		return response;
	}

	public static Response getContractByProviderId(String token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("providerId", id).
				when().get(Routes.get_contract_parameters_by_providerid);
		return response;
	}

	public static Response getOfferingsList(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_data_offering_list);
		return response;
	}

	public static Response getProvidersList(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_data_providers_list);
		return response;
	}

	public static Response getProvidersByCategory(String token, String category){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_data_providers_from_category, category);
		return response;
	}

	public static Response getOfferingTemplate(String token) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("Authorization", "Bearer " + token).		
	    	when().get(Routes.get_offering_template);
        return response;
	}
	
	public static Response searchOfferingByCategory(String token, String category) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("Authorization", "Bearer " + token).		
	    	pathParam("category", category).
	    	when().get(Routes.get_put_delete_offering_search_category);
        return response;
	}

	public static Response searchOfferingByOfferingId(String token, String offeringId) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("id", offeringId).
				when().get(Routes.get_dataoffering_by_offeringid);
		return response;
	}

	public static Response searchOfferingByProviderId(String token, String providerId) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("id", providerId).
				when().get(Routes.get_dataoffering_by_providerid);
		return response;
	}

	public static Response searchCatergoriesList(String token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_categories_list);
		return response;
	}

	public static Response postDataProvider(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_data_provider);
		return response;
	}

	public static Response postDataOffering(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_data_offering);
		return response;
	}

	public static Response updateDataOffering(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).patch(Routes.patch_update_data_offering);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// NOTIFICATIONS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getUserNotifications(String token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().get(Routes.get_post_user_notifications);
		return response;
	}

	public static Response postUserNotification(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.get_post_user_notifications);
		return response;
	}

	public static Response postServiceNotification(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_service_notifications);
		return response;
	}

	public static Response getUnreadUserNotifications(String token) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_unread_notifications);
		return response;
	}

	public static Response getUserNotificationsByUserId(String token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("user_id", user_id).
				when().get(Routes.get_notifications_by_userId);
		return response;
	}

	public static Response getUnreadUserNotificationsByUserId(String token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("user_id", user_id).
				when().get(Routes.get_unread_notifications_by_userId);
		return response;
	}

	public static Response getUserNotificationsByNotificationId(String token, String notification_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("notification_id", notification_id).
				when().get(Routes.get_delete_notification_by_notificationId);
		return response;
	}

	public static Response deleteUserNotificationsByNotificationId(String token, String notification_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("notification_id", notification_id).
				when().delete(Routes.get_delete_notification_by_notificationId);
		return response;
	}

	public static Response patchMarkNotificationAsRead(String token, String notification_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("notification_id", notification_id).
				when().patch(Routes.patch_notification_read);
		return response;
	}

	public static Response patchMarkNotificationAsUnread(String token, String notification_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("notification_id", notification_id).
				when().patch(Routes.patch_notification_unread);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getUserSubscriptions(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_subscriptions);
		return response;
	}

	public static Response getSubscriptionByUserId(String token, String user_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("user_id", user_id).
				when().get(Routes.get_post_subscription_userId);
		return response;
	}

	public static Response postSubscriptionByUserId(String token, String user_id, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("user_id", user_id).
				when().body(body).post(Routes.get_post_subscription_userId);
		return response;
	}

	public static Response getSubscriptionBySubscriptionId(String token, String user_id, String subscription_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().get(Routes.get_delete_subscription_subscriptonId);
		return response;
	}

	public static Response deleteSubscriptionBySubscriptionId(String token, String user_id,String subscription_id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().delete(Routes.get_delete_subscription_subscriptonId);
		return response;
	}

	public static Response patchDeactivateSubscriptionById(String token, String user_id,String subscription_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().patch(Routes.patch_subscrition_deactivate);
		return response;
	}

	public static Response patchActivateSubscriptionById(String token, String user_id, String subscription_id) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("subscription_id", subscription_id).
				pathParam("user_id", user_id).
				when().patch(Routes.patch_subscrition_activate);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getContractTemplateByIdOffering(String token, String idOffering){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("idOffering", idOffering).
				when().get(Routes.get_contract_template_idOffering);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getIssuedCredentialList(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_issued_credential_list);
		return response;
	}

	public static Response getGenerateVerifiableCredential(String token, String credential){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("credential", credential).
				when().get(Routes.get_generate_verifiable_credential);
		return response;
	}

	public static Response postRevokeVerifiableCredential(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_revoke_verifiable_credential);
		return response;
	}

	public static Response postVerifyCredential(String token, String body) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_verify_credential);
		return response;
	}

	public static Response getSubscribeIssuer(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_subscribe_issuer);
		return response;
	}

	public static Response getUnsubscribeIssuer(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_unsubscribe_issuer);
		return response;
	}

	public static Response getVerifyIssuerSubscription(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_verify_issuer_subscription);
		return response;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response postCreateInvoice(String token, String bearerToken, String fromDate, String toDate) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				queryParam("bearerToken", bearerToken).
				queryParam("fromDate", fromDate).
				queryParam("toDate", toDate).
				when().post(Routes.post_create_invoice);
		return response;
	}

	public static Response postDecryptCipherblock(String token, String cipherblock, String jwk) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				queryParam("cipherblock", cipherblock).
				queryParam("jwk", jwk).
				when().post(Routes.post_decrypt_cipherblock);
		return response;
	}

	public static Response deleteFile(String token, String data) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				queryParam("data", data).
				when().delete(Routes.delete_file);
		return response;
	}

	public static Response postDownloadFile(String token, String data) {
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				queryParam("data", data).
				when().post(Routes.post_download_file);
		return response;
	}

	public static Response postGetDataBlock(String token, String bearerToken, String data, String blockId, String blockAck){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("data", data).
				queryParam("bearerToken", bearerToken).
				queryParam("block_id", blockId).
				queryParam("block_ack", blockAck).
				when().post(Routes.post_get_data_block);
		return response;
	}

	public static Response postGetFile(String token, String bearerToken, String data){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("data", data).
				queryParam("bearerToken", bearerToken).
				when().post(Routes.post_get_file);
		return response;
	}

	public static Response getJwk(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_jwk);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getSdkriConfigInfo(String token){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_sdkri_config_info);
		return response;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Response getMarketplaceBalanceByAddress(String token, String address){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("address", address).
				when().get(Routes.get_marketplace_balance_by_address);
		return response;
	}

	public static Response postClearingOperation(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_clearing_operation);
		return response;
	}

	public static Response postDeployTransaction(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_deploy_operation);
		return response;
	}

	public static Response postExchangeIn(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_exchange_in_operation);
		return response;
	}

	public static Response postExchangeOut(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_exchange_out_operation);
		return response;
	}

	public static Response postAddMarketplace(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_new_marketplace);
		return response;
	}

	public static Response getMarketplaceByAddress(String token, String address){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				pathParam("address", address).
				when().post(Routes.get_marketpace_index_by_address);
		return response;
	}

	public static Response postTokenPaymentOperation(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_token_payment_operation);
		return response;
	}

	public static Response postSetPaidStatusOperation(String token, String body){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).contentType("application/json").
				when().body(body).post(Routes.post_set_paid_status_operation);
		return response;
	}

	public static Response getTransactionStatusByTransferId(String token, String transferId){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("transferId", transferId).
				when().get(Routes.get_transaction_status_by_transferId);
		return response;
	}

	public static Response getTransactionByTransactionHash(String token, String transactionHash){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				pathParam("transactionHash", transactionHash).
				when().get(Routes.get_transaction_by_transactionHash);
		return response;
	}

}
