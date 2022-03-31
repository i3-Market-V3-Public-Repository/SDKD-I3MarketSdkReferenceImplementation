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
package api.test;

import api.engine.endpoints.CommonServicesEndpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.i3m.model.backplane.DataOfferingDto;

/**
 * Perform API Testing for the services using CRUD Design Pattern
 */
public class TestEndpoints {
	
	String auth_token = "";
	
	//TOKENS
	String access_token = "erwe-4543645grf-grdytrey";
	String id_token = "fewoptjwetn53-4356346,234.54654367";

	// OFFERINGS
	String newProviderString = "{\"providerId\":\"id123456789\",\"name\":\"provider test\",\"description\":\"this is a test\",\"organization\":[{\"organizationId\":\"organizationTest123\",\"name\":\"this organization is a test\",\"description\":\"this organization is a test plus description\",\"address\":\"this is an optional address\",\"contactPoint\":\"this is an optional contact point\"}]}";
	JSONObject newProviderBody = new JSONObject(newProviderString);
	String providerId="id123456789";
	String newOfferingString = "{\n" +
			"  \"provider\": \"id123456789\",\n" +
			"  \"marketId\" : \"UIOT-M\",\n" +
			"  \"owner\": \"Owner webri node 249\",\n" +
			"  \"dataOfferingTitle\": \"Offsore Wind Turbine\",\n" +
			"  \"dataOfferingDescription\": \"This is offshore wind turbine data\",\n" +
			"  \"category\": \"Energy\",\n" +
			"  \"status\": \"activated\",\n" +
			"  \"dataOfferingExpirationTime\": \"1month\",\n" +
			"  \"contractParameters\": " +
			"    {\n" +
			"      \"interestOfProvider\": \"Chi\",\n" +
			"      \"interestDescription\": \"Test\",\n" +
			"      \"hasGoverningJurisdiction\": \"Test data\",\n" +
			"      \"purpose\": \"Test data\",\n" +
			"      \"purposeDescription\": \"Test data\",\n" +
			"      \"hasIntendedUse\": " +
			"        {\n" +
			"          \"processData\": \"true\",\n" +
			"          \"shareDataWithThirdParty\": \"true\",\n" +
			"          \"editData\": \"true\"\n" +
			"        },\n" +
			"      \"hasLicenseGrant\": " +
			"        {\n" +
			"          \"copyData\": \"true\",\n" +
			"          \"transferable\": \"true\",\n" +
			"          \"exclusiveness\": \"true\",\n" +
			"          \"revocable\": \"true\"\n" +
			"        }\n" +
			"    },\n" +
			"  \"hasPricingModel\": " +
			"    {\n" +
			"      \"pricingModelName\": \"Test data1\",\n" +
			"      \"basicPrice\": \"101390\",\n" +
			"      \"currency\": \"Test data\",\n" +
			"      \"hasPaymentOnSubscription\": " +
			"        {\n" +
			"          \"paymentOnSubscriptionName\": \"Test data\",\n" +
			"          \"paymentType\": \"Test data\",\n" +
			"          \"timeDuration\": \"Test data\",\n" +
			"          \"description\": \"Test data\",\n" +
			"          \"repeat\": \"DAILY\",\n" +
			"          \"hasSubscriptionPrice\": \"43634634\"\n" +
			"        },\n" +
			"      \"hasPaymentOnApi\": " +
			"        {\n" +
			"          \"paymentOnApiName\": \"Test data\",\n" +
			"          \"description\": \"Test data\",\n" +
			"          \"numberOfObject\": \"5\",\n" +
			"          \"hasApiPrice\": \"534\"\n" +
			"        },\n" +
			"      \"hasPaymentOnUnit\": " +
			"        {\n" +
			"          \"paymentOnUnitName\": \"Test data\",\n" +
			"          \"description\": \"Test data\",\n" +
			"          \"dataUnit\": \"213123\",\n" +
			"          \"hasUnitPrice\": \"999\"\n" +
			"        },\n" +
			"      \"hasPaymentOnSize\": " +
			"        {\n" +
			"          \"paymentOnSizeName\": \"Test data\",\n" +
			"          \"description\": \"Test data\",\n" +
			"          \"dataSize\": \"Test data\",\n" +
			"          \"hasSizePrice\": \"543534\"\n" +
			"        },\n" +
			"      \"hasFreePrice\": " +
			"        {\n" +
			"          \"hasPriceFree\": \"true\"\n" +
			"        }\n" +
			"    }, \n" +
			"  \"hasDataset\": " +
			"    {\n" +
			"      \"title\": \"Test data\",\n" +
			"      \"keyword\": \"Test data\",\n" +
			"      \"dataset\": \"Test data\",\n" +
			"      \"description\": \"Test data\",\n" +
			"      \"issued\": \"2021-10-04T14:20:23.162Z\",\n" +
			"      \"modified\": \"2021-10-04T14:20:23.162Z\",\n" +
			"      \"temporal\": \"Test data\",\n" +
			"      \"language\": \"Test data\",\n" +
			"      \"spatial\": \"Test data\",\n" +
			"      \"accrualPeriodicity\": \"Test data\",\n" +
			"      \"temporalResolution\": \"Test data\",\n" +
			"      \"theme\": [\n" +
			"        \"Test data\"\n" +
			"      ],\n" +
			"      \"distribution\": [\n" +
			"        {\n" +
			"          \"title\": \"Test data\",\n" +
			"          \"description\": \"Test data\",\n" +
			"          \"license\": \"Test data\",\n" +
			"\t\t  \"accessRights\": \"private\",\n" +
			"\t\t  \"downloadType\": \"JSON\",\n" +
			"          \"conformsTo\": \"Test data\",\n" +
			"          \"mediaType\": \"Test data\",\n" +
			"          \"packageFormat\": \"Test data\",\n" +
			"          \"accessService\": " +
			"            {\n" +
			"              \"conformsTo\": \"Test data\",\n" +
			"              \"endpointDescription\": \"Test data\",\n" +
			"              \"endpointURL\": \"Test data\",\n" +
			"              \"servesDataset\": \"Test data\",\n" +
			"              \"serviceSpecs\": \"Test data\"\n" +
			"            }\n" +
			"        }\n" +
			"      ],\n" +
			"      \"datasetInformation\": [\n" +
			"        {\n" +
			"          \"measurementType\": \"Test data\",\n" +
			"          \"measurementChannelType\": \"Test data\",\n" +
			"          \"sensorId\": \"Test data\",\n" +
			"          \"deviceId\": \"Test data\",\n" +
			"          \"cppType\": \"Test data\",\n" +
			"          \"sensorType\": \"Test data\"\n" +
			"        }\n" +
			"      ]\n" +
			"    }\n" +
			"}\n";
	JSONObject newOfferingBody = new JSONObject(newOfferingString);
	String dataOfferingId = "";
	JSONObject obtainedOfferingBody = null;

	String category = "Energy";

	// NOTIFICATIONS
	String newUserNotification = "{\n" +
			"  \"origin\": \"sdkri\",\n" +
			"  \"predefined\": true,\n" +
			"  \"type\": \"offering.new\",\n" +
			"  \"receiver_id\": \"eleazar\",\n" +
			"  \"message\": {\"name\":\"This is a test for sdk-ri\"},\n" +
			"  \"status\": \"Ok\"\n" +
			"}";
	JSONObject newUserNotificationBody = new JSONObject(newUserNotification);
	public String createdNotificationId = "";

	String newServiceNotification = "{\n" +
			"  \"receiver_id\": \"offering.new\",\n" +
			"  \"message\": {\"name\":\"this is a service notification test for sdkri\"}\n" +
			"}";
	JSONObject newServiceNotificationBody = new JSONObject(newServiceNotification);

	// SUBSCRIPTIONS
	String subscriptionUserId = "eleazar";
	String newUserSubscription = "{\"category\":\""+category+"\"}";
	JSONObject newUserSubscriptionBody = new JSONObject(newUserSubscription);
	public String createdSubscriptionId = "";

	//CREDENTIAL
	public String credential = "";
	String jwk = "";
	String credentialjwk = "{\n" +
			"  \"credentialjwk\": \""+jwk+"\"\n" +
			"}";
	JSONObject revokeCredentialBody = new JSONObject(credentialjwk);

	String verifyCredential = "{\n" +
			"  \"credentialjwk\": \""+jwk+"\",\n" +
			"  \"credentialIssuer\": \"string\"\n" +
			"}";
	JSONObject verifyCredentialBody = new JSONObject(verifyCredential);

	// EXCHANGE
	String bearedToken = auth_token;
	String fromDate = "2021/12/1";
	String toDate = "2021/12/31";
	String cipherblock = "";
	String data = "";
	String blockId = "";
	String blockAck = "";
	
	// TOKEN
	String marketplaceAddressSender1 = "0xD94f3239185C27937367B9A1A17aB70f4F631667";
	String marketplaceAddressSender2 = "0xD94f3239185C27937367B9A1A17aB70f4F631883";
	String marketplaceAddressReceiver = "0x79CD92CD7c1e380c1a6Ba5E9EF09D2F7c4820D45";
	String marketplaceClearingAddress = "0x79CD92CD7c1e380c1a6Ba5E9EF09D2F7c4820U5R";
	String marketplaceAddress = "0xb8E0101259550765a5f1287d0F680Ee9B09b4235";
	String userAddress = "0xb8E0101259550765a5f1287d0F680Ee9B09b41E7";

	String marketplaceClearingOperation = "{\n" +
			"  \"senderAddress\": \""+ marketplaceClearingAddress +"\"\n" +
			"}";
	JSONObject marketplaceClearingBody = new JSONObject(marketplaceClearingOperation);

	String newMarketplace = "{\n" +
			"  \"senderAddress\": \""+marketplaceAddressSender2+"\",\n" +
			"  \"marketplaceAddress\": \""+marketplaceAddressReceiver+"\"\n" +
			"}";
	JSONObject newMarketplaceBody = new JSONObject(newMarketplace);


	String exchangeIn = "{\n" +
			"  \"senderAddress\": \""+marketplaceAddressSender2+"\",\n" +
			"  \"userAddress\": \""+userAddress+"\",\n" +
			"  \"tokens\": \"10\"\n" +
			"}";
	JSONObject exchangeInBody = new JSONObject(exchangeIn);

	String exchangeOut = "{\n" +
			"  \"senderAddress\": \""+marketplaceAddressSender1+"\",\n" +
			"  \"marketplaceAddress\": \""+marketplaceAddress+"\"\n" +
			"}";
	JSONObject exchangeOutBody = new JSONObject(exchangeOut);

	String deployOperation = "{\n" +
			"  \"serializedTx\": \"0xf90127148302e34683bebc209483a000000000000000ff8eab3673c32559b63ff391772aa300121a94d40000000000042d616639372d000000000d6cc284f4d4ca9a737ea9ec39805f811a35905b61ec275fac92aa1f2c0ca2\"\n" +
			"}";
	JSONObject deployOperationBody = new JSONObject(deployOperation);

	String paymentOperation = "{\n" +
			"  \"senderAddress\": \""+marketplaceAddressSender1+"\",\n" +
			"  \"providerAddress\": \"0xb13894b969ad9A69108684dA8004E53A32c6df66\",\n" +
			"  \"amount\": \"10\"\n" +
			"}";
	JSONObject paymentOperationBody = new JSONObject(paymentOperation);

	String setPaidOperation = "{\n" +
			"  \"senderAddress\": \"0x79CD92CD7c1e380c1a6Ba5E9EF09D2F7c4820T40\",\n" +
			"  \"transferId\": \"6fa4973b-11ce-56d8-8544-660e1a334b23\",\n" +
			"  \"transferCode\": \"GR99203205004989123400\"\n" +
			"}";
	JSONObject setPaidOperationBody = new JSONObject(setPaidOperation);

	String transferId = "6fa4973b-11ce-56d8-8544-660e1a334b23";
	String transactionHash = "";
	// -----------------------------------------------------------------TESTS
	@BeforeTest
	public void beforeTest()  {
	
		System.out.println("**********************************GETTING AUTH TOKEN UNDER TEST**********************************");
		Response response= CommonServicesEndpoints.getAuthToken();
		auth_token = response.then().log().body().statusCode(200).extract().path("access_token");
		System.out.println("********************************** TOKEN OBTAINED: " + auth_token + " **********************************");
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// OFFERINGS ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test(priority = 1)
	public void testOfferingTemplate()  {
	
		System.out.println("************************************{OFFERING TEMPLATE}************************************");
	    Response response= CommonServicesEndpoints.getOfferingTemplate(access_token, id_token);
		String data = response.then().log().body().statusCode(200).extract().path("data");
	    System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 2)
	public void testProviderCreation()  {

		System.out.println("************************************{PROVIDER CREATION}************************************");

		Response response= CommonServicesEndpoints.postDataProvider(access_token, id_token, newProviderBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 3)
	public void testOfferingCreation()  {

		System.out.println("************************************{OFFERING CREATION}************************************");
		Response response= CommonServicesEndpoints.postDataOffering(access_token, id_token, newOfferingBody.toString());
		dataOfferingId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data.dataOfferingId").toString();
		response.then().log().body().statusCode(200);

		System.out.println("******************************************************************************************");
	}

	@Test(priority = 4)
	public void testSearchOfferingByOfferingId()  {

		System.out.println("****************************{OFFERING SEARCH BY OFFERINGID}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByOfferingId(access_token, id_token, dataOfferingId);
		response.then().log().body().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		//JSONArray data = new JSONArray((Collection<?>)jsonPathEvaluator.get("data"));
		obtainedOfferingBody = new JSONObject(jsonPathEvaluator.getObject("data", DataOfferingDto.class));
		//obtainedOfferingBody = data.getJSONObject(0);
		System.out.println("*************************DATA OBTAINED: " + obtainedOfferingBody.toString()+ "*******************************************");
	}

	@Test(priority = 5)
	public void testUpdateOffering()  {

		System.out.println("************************************{OFFERING UPDATE}************************************");
		//Integer version = (int) obtainedOfferingBody.get("version");  //GET CURRENT VERSION AS INT
		//version = version + 1; // INCREASE VERSION COUNTER
		//obtainedOfferingBody.put("version", version); //UPDATE VERSION KEY
		System.out.println("Offering ID "+dataOfferingId+" to update with values:\n"+obtainedOfferingBody);
		Response response= CommonServicesEndpoints.updateDataOffering(access_token, id_token, obtainedOfferingBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 6)
	public void testCategoriesList()  {

		System.out.println("************************************{CATEGORIES LISTING}************************************");
		Response response= CommonServicesEndpoints.searchCatergoriesList(access_token, id_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 7)
	public void testOfferingSearchByCategory()  {
	
		System.out.println("************************************{OFFERING SEARCH BY CATEGORY}************************************");
	    Response response= CommonServicesEndpoints.searchOfferingByCategory(access_token, id_token, category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 8)
	public void testSearchOfferingByProviderId()  {

		System.out.println("************************************{OFFERING SEARCH BY PROVIDERID}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByProviderId(access_token, id_token, providerId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 9)
	public void testProvidersByCategory()  {

		System.out.println("************************************{OFFERING SEARCH BY CATEGORY}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByCategory(access_token, id_token, category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 10)
	public void testProvidersByCategories()  {

		System.out.println("************************************{PROVIDERS LIST}************************************");
		Response response= CommonServicesEndpoints.getProvidersByCategory(access_token, id_token, category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 11)
	public void testProvidersList()  {

		System.out.println("************************************{PROVIDERS LIST}************************************");
		Response response= CommonServicesEndpoints.getProvidersList(access_token, id_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 12)
	public void testOfferingsList()  {

		System.out.println("************************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(access_token, id_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	/** TODO FIX IT
	@Test(priority = 13)
	public void testContractByProviderId()  {

		System.out.println("************************************{CONTRACT BY PROVIDERID}************************************");
		System.out.println("Obtaining provider "+providerId+" contracts...");
		Response response= CommonServicesEndpoints.getContractByProviderId(auth_token, providerId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}
	**/

	@Test(priority = 14)
	public void testContractByOfferingId()  {

		System.out.println("************************************{CONTRACT BY OFFERINGID}************************************");
		Response response= CommonServicesEndpoints.getContractByOfferingId(access_token, id_token, dataOfferingId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 15)
	public void testDeleteOffering()  {

		System.out.println("************************************{OFFERING DELETE}************************************");
		Response response= CommonServicesEndpoints.deleteOffering(access_token, id_token, dataOfferingId);
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 16)
	public void testGetOfferingList()  {

		System.out.println("************************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(access_token, id_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}
	
	@Test(priority = 16)
	public void testDeleteProvider()  {

		System.out.println("************************************{DELETE PROVIDER}************************************");
		Response response= CommonServicesEndpoints.deleteProvider(access_token, id_token, providerId);
		Object data = response.then().log().body().statusCode(200);
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// NOTIFICATIONS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 17)
//	public void testGetUsersStoredNotificationList()  {
//
//		System.out.println("************************************{NOTIFICATION LIST}************************************");
//		Response response= CommonServicesEndpoints.getUserNotifications(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 18)
//	public void testPostUserNotification()  {
//
//		System.out.println("************************************{CREATE USER NOTIFICATION}************************************");
//		Response response= CommonServicesEndpoints.postUserNotification(access_token, id_token, newUserNotificationBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		createdNotificationId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data.id").toString();
//		System.out.println("************************************ID OBTAINED: "+ createdNotificationId + "\nDATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 19)
//	public void testPostServiceNotification()  {
//
//		System.out.println("************************************{CREATE SERVICE NOTIFICATION}************************************");
//		Response response= CommonServicesEndpoints.postServiceNotification(access_token, id_token, newServiceNotificationBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//
//	}
//
//	@Test(priority = 20)
//	public void testGetUnreadNotificationList()  {
//
//		System.out.println("************************************{UNREAD NOTIFICATIONS LIST}************************************");
//		Response response= CommonServicesEndpoints.getUserNotifications(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 21)
//	public void testGetNotificationByUserId()  {
//
//		System.out.println("************************************{NOTIFICATIONS BY USER ID}************************************");
//		Response response= CommonServicesEndpoints.getUserNotificationsByUserId(access_token, id_token, "UserID345");
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
//	}
//
//	@Test(priority = 22)
//	public void testGetUnreadNotificationByUserId()  {
//
//		System.out.println("************************************{UNREAD NOTIFICATIONS BY USER ID}************************************");
//		Response response= CommonServicesEndpoints.getUnreadUserNotificationsByUserId(access_token, id_token, "UserID345");
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 23)
//	public void testGetNotificationByNotificationId()  {
//
//		System.out.println("************************************{NOTIFICATIONS BY NOTIFICATION ID}************************************");
//		Response response= CommonServicesEndpoints.getUserNotificationsByNotificationId(access_token, id_token, createdNotificationId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 24)
//	public void testPatchNotificationAsReadByNotificationId()  {
//
//		System.out.println("************************************{MARK NOTIFICATION AS READ BY NOTIFICATION ID}************************************");
//		Response response= CommonServicesEndpoints.patchMarkNotificationAsRead(access_token, id_token, createdNotificationId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 25)
//	public void testPatchNotificationAsUnreadByNotificationId()  {
//
//		System.out.println("************************************{MARK NOTIFICATION AS UNREAD BY NOTIFICATION ID}************************************");
//		Response response= CommonServicesEndpoints.patchMarkNotificationAsUnread(access_token, id_token, createdNotificationId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 26)
//	public void testDeleteNotificationByNotificationId()  {
//
//		System.out.println("************************************{DELETE NOTIFICATION BY NOTIFICATION ID}************************************");
//		Response response= CommonServicesEndpoints.deleteUserNotificationsByNotificationId(access_token, id_token, createdNotificationId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 27)
//	public void testGetAllUsersSubscriptions()  {
//
//		System.out.println("************************************{GET ALL USERS SUBSCRIPTIONS}************************************");
//		Response response= CommonServicesEndpoints.getUserSubscriptions(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 28)
//	public void testCreateUsersSubscription()  {
//
//		System.out.println("************************************{CREATE USER SUBSCRIPTIONS}************************************");
//		Response response= CommonServicesEndpoints.postSubscriptionByUserId(access_token, id_token, subscriptionUserId, newUserSubscriptionBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		createdSubscriptionId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data.id").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 29)
//	public void testUsersSubscriptionsByUserId()  {
//
//		System.out.println("************************************{GET USER SUBSCRIPTIONS BY USER ID}************************************");
//		Response response= CommonServicesEndpoints.getSubscriptionByUserId(access_token, id_token, subscriptionUserId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 30)
//	public void testUsersSubscriptionsBySubscriptionId()  {
//
//		System.out.println("************************************{GET USER SUBSCRIPTIONS BY SUBSCRIPTION ID}************************************");
//		Response response= CommonServicesEndpoints.getSubscriptionBySubscriptionId(access_token, id_token, subscriptionUserId,createdSubscriptionId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 31)
//	public void testDeactivateUserSubscription()  {
//
//		System.out.println("************************************{DEACTIVATE USER SUBSCRIPTION}************************************");
//		Response response= CommonServicesEndpoints.patchDeactivateSubscriptionById(access_token, id_token, subscriptionUserId, createdSubscriptionId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 32)
//	public void testActivateUserSubscription()  {
//
//		System.out.println("************************************{ACTIVATE USER SUBSCRIPTION}************************************");
//		Response response= CommonServicesEndpoints.patchActivateSubscriptionById(access_token, id_token, subscriptionUserId, createdSubscriptionId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 33)
//	public void testDeleteUserSubscription()  {
//
//		System.out.println("************************************{DELETE USER SUBSCRIPTION}************************************");
//		Response response= CommonServicesEndpoints.deleteSubscriptionBySubscriptionId(access_token, id_token, subscriptionUserId, createdSubscriptionId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	@Test(priority = 34)
//	public void testGetContractTemplateByIdOffering()  {
//
//		System.out.println("************************************{GET CONTRACT TEMPLATE BY ID OFFERING}************************************");
//		Response response= CommonServicesEndpoints.getContractTemplateByIdOffering(access_token, id_token, dataOfferingId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 35)
//	public void testGetIssuedCredentialList()  {
//
//		System.out.println("************************************{GET CREDENTIAL LIST}************************************");
//		Response response= CommonServicesEndpoints.getIssuedCredentialList(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 36)
//	public void testGenerateCredential()  {
//
//		System.out.println("************************************{GENERATE CREDENTIAL}************************************");
//		Response response= CommonServicesEndpoints.getGenerateVerifiableCredential(access_token, id_token, credential);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 37)
//	public void testVerifyCredentialByjwk()  {
//
//		System.out.println("************************************{VERIFY CREDENTIAL BY jwk}************************************");
//		Response response= CommonServicesEndpoints.postVerifyCredential(access_token, id_token, verifyCredentialBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 38)
//	public void testRevokeCredentialByjwk()  {
//
//		System.out.println("************************************{REVOKE CREDENTIAL}************************************");
//		Response response= CommonServicesEndpoints.postRevokeVerifiableCredential(access_token, id_token, revokeCredentialBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 39)
//	public void testSubscribeIssuer()  {
//
//		System.out.println("************************************{SUBSCRIBE THE ISSUER}************************************");
//		Response response= CommonServicesEndpoints.getSubscribeIssuer(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("*************************DATA OBTAINED: " + data + "*******************************************");
//	}
//
//	@Test(priority = 40)
//	public void testUnsubscribeIssuer()  {
//
//		System.out.println("************************************{UNSUBSCRIBE THE ISSUER}************************************");
//		Response response= CommonServicesEndpoints.getUnsubscribeIssuer(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 41)
//	public void testVerifyIssuerSubscription()  {
//
//		System.out.println("************************************{VERIFY ISSUER SUBSCRIPTION}************************************");
//		Response response= CommonServicesEndpoints.getVerifyIssuerSubscription(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 42)
//	public void testCreateInvoice()  {
//
//		System.out.println("************************************{GENERATE INVOICE}************************************");
//		Response response= CommonServicesEndpoints.postCreateInvoice(access_token, id_token, bearedToken, fromDate, toDate);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 43)
//	public void testDecryptCipherblock()  {
//
//		System.out.println("************************************{DECRYPT CIPHERBLOCK}************************************");
//		Response response= CommonServicesEndpoints.postDecryptCipherblock(access_token, id_token, cipherblock, jwk);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 44)
//	public void testDownloadFile()  {
//
//		System.out.println("************************************{DOWNLOAD FILE}************************************");
//		Response response= CommonServicesEndpoints.postDownloadFile(access_token, id_token, data);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 45)
//	public void testGetDataBlock()  {
//
//		System.out.println("************************************{GET DATA BLOCK}************************************");
//		Response response= CommonServicesEndpoints.postGetDataBlock(access_token, id_token, bearedToken, data, blockId, blockAck);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 46)
//	public void testGetFile()  {
//
//		System.out.println("************************************{GET FILE}************************************");
//		Response response= CommonServicesEndpoints.postGetFile(access_token, id_token, bearedToken, data);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 47)
//	public void testGetjwk()  {
//
//		System.out.println("************************************{GET JWK}************************************");
//		Response response= CommonServicesEndpoints.getJwk(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "*************************************");
//	}
//
//	@Test(priority = 48)
//	public void testDeleteFile()  {
//
//		System.out.println("************************************{DELETE FILE}************************************");
//		Response response= CommonServicesEndpoints.deleteFile(access_token, id_token, data);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 49)
//	public void testGetConfigurationInfo()  {
//
//		System.out.println("************************************{GET SDK-RI CONFIG INFO}************************************");
//		Response response= CommonServicesEndpoints.getSdkriConfigInfo(access_token, id_token);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	@Test(priority = 50)
//	public void testAddMarketplaceToTreasury()  {
//
//		System.out.println("************************************{ADD MARKETPLACE TO TREASURY}************************************");
//		Response response= CommonServicesEndpoints.postAddMarketplace(access_token, id_token, newMarketplaceBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 51)
//	public void testMarketplaceBalance1()  {
//
//		System.out.println("************************************{GET MARKETPLACE1 BALANCE}************************************");
//		Response response= CommonServicesEndpoints.getMarketplaceBalanceByAddress(access_token, id_token,marketplaceAddressSender2);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 52)
//	public void testMarketplaceBalance2()  {
//
//		System.out.println("************************************{GET MARKETPLACE2 BALANCE}************************************");
//		Response response= CommonServicesEndpoints.getMarketplaceBalanceByAddress(access_token, id_token,marketplaceAddressReceiver);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 53)
//	public void testExchangeIn()  {
//
//		System.out.println("************************************{MARKETPLACE EXCHANGE IN}************************************");
//		Response response= CommonServicesEndpoints.postExchangeIn(access_token, id_token,exchangeInBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 54)
//	public void testExchangeOut()  {
//
//		System.out.println("************************************{MARKETPLACE EXCHANGE OUT}************************************");
//		Response response= CommonServicesEndpoints.postExchangeOut(access_token, id_token,exchangeOutBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 55)
//	public void testDeployOperation()  {
//
//		System.out.println("************************************{MARKETPLACE DEPLOY OPERATION}************************************");
//		Response response= CommonServicesEndpoints.postDeployTransaction(access_token, id_token, deployOperationBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 56)
//	public void testClearingOperation()  {
//
//		System.out.println("************************************{MARKETPLACE CLEARING OPERATION}************************************");
//		Response response= CommonServicesEndpoints.postClearingOperation(access_token, id_token, marketplaceClearingBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 57)
//	public void testMarketplaceIndexByAddress()  {
//
//		System.out.println("************************************{MARKETPLACE INDEX BY ADDRESS}************************************");
//		Response response= CommonServicesEndpoints.postClearingOperation(access_token, id_token, marketplaceAddressSender1);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 58)
//	public void testTokenPayment()  {
//
//		System.out.println("************************************{MARKETPLACE TOKEN PAYMENT OPERATION}************************************");
//		Response response= CommonServicesEndpoints.postTokenPaymentOperation(access_token, id_token, paymentOperationBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 58)
//	public void testSetPaidStatus()  {
//
//		System.out.println("************************************{MARKETPLACE SET PAID STATUS}************************************");
//		Response response= CommonServicesEndpoints.postSetPaidStatusOperation(access_token, id_token, setPaidOperationBody.toString());
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 59)
//	public void testGetTransactionStatusById()  {
//
//		System.out.println("************************************{MARKETPLACE GET TRANSACTION STATUS BY TRANSFER ID}************************************");
//		Response response= CommonServicesEndpoints.getTransactionStatusByTransferId(access_token, id_token, transferId);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
//
//	@Test(priority = 60)
//	public void testTransactionStatusByHash()  {
//
//		System.out.println("************************************{MARKETPLACE GET TRANSACTION STATUS BY TRANSACTION HASH}************************************");
//		Response response= CommonServicesEndpoints.getTransactionByTransactionHash(access_token, id_token, transactionHash);
//		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
//		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
//	}
	
}
