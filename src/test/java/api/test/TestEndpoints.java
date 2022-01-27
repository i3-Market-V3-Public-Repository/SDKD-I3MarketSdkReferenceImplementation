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
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Perform API Testing for the services using CRUD Design Pattern
 */
public class TestEndpoints {
	
	String auth_token = "";

	// OFFERINGS
	String newProviderString = "{\"providerId\":\"id123456789\",\"name\":\"provider test\",\"description\":\"this is a test\",\"organization\":[{\"organizationId\":\"organizationTest123\",\"name\":\"this organization is a test\",\"description\":\"this organization is a test plus description\",\"address\":\"this is an optional address\",\"contactPoint\":\"this is an optional contact point\"}]}";
	JSONObject newProviderBody = new JSONObject(newProviderString);
	String providerId="id123456789";
	String newOfferingString = "{\n" +
			"  \"provider\": \""+providerId+"\",\n" +
			"  \"marketId\": \"string\",\n" +
			"  \"owner\": \"string\",\n" +
			"  \"dataOfferingTitle\": \"mandatory_field\",\n" +
			"  \"dataOfferingDescription\": \"string\",\n" +
			"  \"category\": \"Other\",\n" +
			"  \"status\": \"e.g. Activated, InActivated, ToBeDeleted, Deleted\",\n" +
			"  \"dataOfferingExpirationTime\": \"NA\",\n" +
			"  \"contractParameters\": [\n" +
			"    {\n" +
			"      \"contractParametersId\": \"string\",\n" +
			"      \"interestOfProvider\": \"NA\",\n" +
			"      \"interestDescription\": \"NA\",\n" +
			"      \"hasGoverningJurisdiction\": \"NA\",\n" +
			"      \"purpose\": \"NA\",\n" +
			"      \"purposeDescription\": \"NA\",\n" +
			"      \"hasIntendedUse\": [\n" +
			"        {\n" +
			"          \"intendedUseId\": \"string\",\n" +
			"          \"processData\": \"true OR false\",\n" +
			"          \"shareDataWithThirdParty\": \"true OR false\",\n" +
			"          \"editData\": \"true OR false\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"hasLicenseGrant\": [\n" +
			"        {\n" +
			"          \"licenseGrantId\": \"string\",\n" +
			"          \"copyData\": \"true OR false\",\n" +
			"          \"transferable\": \"true OR false\",\n" +
			"          \"exclusiveness\": \"true OR false\",\n" +
			"          \"revocable\": \"true OR false\"\n" +
			"        }\n" +
			"      ]\n" +
			"    }\n" +
			"  ],\n" +
			"  \"hasPricingModel\": [\n" +
			"    {\n" +
			"      \"pricingModelId\": \"string\",\n" +
			"      \"pricingModelName\": \"mandatory_field\",\n" +
			"      \"basicPrice\": \"mandatory_field\",\n" +
			"      \"currency\": \"mandatory_field\",\n" +
			"      \"hasPaymentOnSubscription\": [\n" +
			"        {\n" +
			"          \"paymentId\": \"string\",\n" +
			"          \"paymentOnSubscriptionName\": \"optional_field\",\n" +
			"          \"paymentType\": \"optional_field\",\n" +
			"          \"timeDuration\": \"optional_field\",\n" +
			"          \"description\": \"optional_field\",\n" +
			"          \"repeat\": \"Daily\",\n" +
			"          \"hasSubscriptionPrice\": \"optional_field\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"hasPaymentOnApi\": [\n" +
			"        {\n" +
			"          \"paymentId\": \"string\",\n" +
			"          \"paymentOnApiName\": \"optional_field\",\n" +
			"          \"description\": \"optional_field\",\n" +
			"          \"numberOfObject\": \"optional_field\",\n" +
			"          \"hasApiPrice\": \"optional_field\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"hasPaymentOnUnit\": [\n" +
			"        {\n" +
			"          \"paymentId\": \"string\",\n" +
			"          \"paymentOnUnitName\": \"optional_field\",\n" +
			"          \"description\": \"optional_field\",\n" +
			"          \"dataUnit\": \"optional_field\",\n" +
			"          \"hasUnitPrice\": \"optional_field\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"hasPaymentOnSize\": [\n" +
			"        {\n" +
			"          \"paymentId\": \"string\",\n" +
			"          \"paymentOnSizeName\": \"optional_field\",\n" +
			"          \"description\": \"optional_field\",\n" +
			"          \"dataSize\": \"optional_field\",\n" +
			"          \"hasSizePrice\": \"optional_field\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"hasFreePrice\": [\n" +
			"        {\n" +
			"          \"paymentId\": \"string\",\n" +
			"          \"hasPriceFree\": \"optional_field\"\n" +
			"        }\n" +
			"      ]\n" +
			"    }\n" +
			"  ],\n" +
			"  \"hasDataset\": [\n" +
			"    {\n" +
			"      \"datasetId\": \"string\",\n" +
			"      \"title\": \"mandatory_field\",\n" +
			"      \"keyword\": \"mandatory_field\",\n" +
			"      \"dataset\": \"optional_field\",\n" +
			"      \"description\": \"optional_field\",\n" +
			"      \"issued\": \"date-time\",\n" +
			"      \"modified\": \"date-time\",\n" +
			"      \"temporal\": \"optional_field\",\n" +
			"      \"language\": \"optional_field\",\n" +
			"      \"spatial\": \"optional_field\",\n" +
			"      \"accrualPeriodicity\": \"optional_field\",\n" +
			"      \"temporalResolution\": \"optional_field\",\n" +
			"      \"distribution\": [\n" +
			"        {\n" +
			"          \"distributionId\": \"string\",\n" +
			"          \"title\": \"optional_field\",\n" +
			"          \"description\": \"optional_field\",\n" +
			"          \"license\": \"optional_field\",\n" +
			"          \"accessRights\": \"optional_field\",\n" +
			"          \"downloadType\": \"optional_field\",\n" +
			"          \"conformsTo\": \"optional_field\",\n" +
			"          \"mediaType\": \"optional_field\",\n" +
			"          \"packageFormat\": \"optional_field\",\n" +
			"          \"accessService\": [\n" +
			"            {\n" +
			"              \"dataserviceId\": \"string\",\n" +
			"              \"conformsTo\": \"optional_field\",\n" +
			"              \"endpointDescription\": \"optional_field\",\n" +
			"              \"endpointURL\": \"optional_field\",\n" +
			"              \"servesDataset\": \"optional_field\",\n" +
			"              \"serviceSpecs\": \"optional_field\"\n" +
			"            }\n" +
			"          ]\n" +
			"        }\n" +
			"      ],\n" +
			"      \"datasetInformation\": [\n" +
			"        {\n" +
			"          \"datasetInformationId\": \"string\",\n" +
			"          \"measurementType\": \"optional_field\",\n" +
			"          \"measurementChannelType\": \"optional_field\",\n" +
			"          \"sensorId\": \"optional_field\",\n" +
			"          \"deviceId\": \"optional_field\",\n" +
			"          \"cppType\": \"optional_field\",\n" +
			"          \"sensorType\": \"optional_field\"\n" +
			"        }\n" +
			"      ],\n" +
			"      \"theme\": [\n" +
			"        \"optional_field\"\n" +
			"      ]\n" +
			"    }\n" +
			"  ]\n" +
			"}";
	JSONObject newOfferingBody = new JSONObject(newOfferingString);
	String dataOfferingId = "";
	JSONObject obtainedOfferingBody = null;

	String category = "Agriculture";

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
	    Response response= CommonServicesEndpoints.getOfferingTemplate(auth_token);
		String data = response.then().log().body().statusCode(200).extract().path("data");
	    System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 2)
	public void testProviderCreation()  {

		System.out.println("************************************{PROVIDER CREATION}************************************");

		Response response= CommonServicesEndpoints.postDataProvider(auth_token, newProviderBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 3)
	public void testOfferingCreation()  {

		System.out.println("************************************{OFFERING CREATION}************************************");
		Response response= CommonServicesEndpoints.postDataOffering(auth_token, newOfferingBody.toString());
		dataOfferingId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data[0].dataOfferingId").toString();
		response.then().log().body().statusCode(200);

		System.out.println("******************************************************************************************");
	}

	@Test(priority = 4)
	public void testSearchOfferingByOfferingId()  {

		System.out.println("****************************{OFFERING SEARCH BY OFFERINGID}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByOfferingId(auth_token, dataOfferingId);
		response.then().log().body().statusCode(200);
		JSONObject body = new JSONObject(response.getBody().asString());
		JSONArray JsonArray = body.getJSONArray("data");
		obtainedOfferingBody = JsonArray.getJSONObject(0);
		System.out.println("*************************DATA OBTAINED: " + obtainedOfferingBody.toString()+ "*******************************************");
	}

	@Test(priority = 5)
	public void testUpdateOffering()  {

		System.out.println("************************************{OFFERING UPDATE}************************************");
		Integer version = (int) obtainedOfferingBody.get("version");  //GET CURRENT VERSION AS INT
		version = version + 1; // INCREASE VERSION COUNTER
		obtainedOfferingBody.put("version", version); //UPDATE VERSION KEY
		System.out.println("Offering ID "+dataOfferingId+" to update with values:\n"+obtainedOfferingBody);
		Response response= CommonServicesEndpoints.updateDataOffering(auth_token, obtainedOfferingBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 6)
	public void testCategoriesList()  {

		System.out.println("************************************{CATEGORIES LISTING}************************************");
		Response response= CommonServicesEndpoints.searchCatergoriesList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 7)
	public void testOfferingSearchByCategory()  {
	
		System.out.println("************************************{OFFERING SEARCH BY CATEGORY}************************************");
	    Response response= CommonServicesEndpoints.searchOfferingByCategory(auth_token,"Agriculture");
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 8)
	public void testSearchOfferingByProviderId()  {

		System.out.println("************************************{OFFERING SEARCH BY PROVIDERID}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByProviderId(auth_token, providerId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 9)
	public void testProvidersByCategory()  {

		System.out.println("************************************{OFFERING SEARCH BY CATEGORY}************************************");
		Response response= CommonServicesEndpoints.searchOfferingByCategory(auth_token,category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 10)
	public void testProvidersByCategories()  {

		System.out.println("************************************{PROVIDERS LIST}************************************");
		Response response= CommonServicesEndpoints.getProvidersByCategory(auth_token, category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 11)
	public void testProvidersList()  {

		System.out.println("************************************{PROVIDERS LIST}************************************");
		Response response= CommonServicesEndpoints.getProvidersList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 12)
	public void testOfferingsList()  {

		System.out.println("************************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(auth_token);
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
		Response response= CommonServicesEndpoints.getContractByOfferingId(auth_token, dataOfferingId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 15)
	public void testDeleteOffering()  {

		System.out.println("************************************{OFFERING DELETE}************************************");
		Response response= CommonServicesEndpoints.deleteOffering(auth_token, dataOfferingId);
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 16)
	public void testGetOfferingList()  {

		System.out.println("************************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////// NOTIFICATIONS /////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 17)
	public void testGetUsersStoredNotificationList()  {

		System.out.println("************************************{NOTIFICATION LIST}************************************");
		Response response= CommonServicesEndpoints.getUserNotifications(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 18)
	public void testPostUserNotification()  {

		System.out.println("************************************{CREATE USER NOTIFICATION}************************************");
		Response response= CommonServicesEndpoints.postUserNotification(auth_token, newUserNotificationBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		createdNotificationId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data.id").toString();
		System.out.println("************************************ID OBTAINED: "+ createdNotificationId + "\nDATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 19)
	public void testPostServiceNotification()  {

		System.out.println("************************************{CREATE SERVICE NOTIFICATION}************************************");
		Response response= CommonServicesEndpoints.postServiceNotification(auth_token, newServiceNotificationBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");

	}

	@Test(priority = 20)
	public void testGetUnreadNotificationList()  {

		System.out.println("************************************{UNREAD NOTIFICATIONS LIST}************************************");
		Response response= CommonServicesEndpoints.getUserNotifications(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 21)
	public void testGetNotificationByUserId()  {

		System.out.println("************************************{NOTIFICATIONS BY USER ID}************************************");
		Response response= CommonServicesEndpoints.getUserNotificationsByUserId(auth_token, "UserID345");
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 22)
	public void testGetUnreadNotificationByUserId()  {

		System.out.println("************************************{UNREAD NOTIFICATIONS BY USER ID}************************************");
		Response response= CommonServicesEndpoints.getUnreadUserNotificationsByUserId(auth_token, "UserID345");
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 23)
	public void testGetNotificationByNotificationId()  {

		System.out.println("************************************{NOTIFICATIONS BY NOTIFICATION ID}************************************");
		Response response= CommonServicesEndpoints.getUserNotificationsByNotificationId(auth_token, createdNotificationId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 24)
	public void testPatchNotificationAsReadByNotificationId()  {

		System.out.println("************************************{MARK NOTIFICATION AS READ BY NOTIFICATION ID}************************************");
		Response response= CommonServicesEndpoints.patchMarkNotificationAsRead(auth_token, createdNotificationId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 25)
	public void testPatchNotificationAsUnreadByNotificationId()  {

		System.out.println("************************************{MARK NOTIFICATION AS UNREAD BY NOTIFICATION ID}************************************");
		Response response= CommonServicesEndpoints.patchMarkNotificationAsUnread(auth_token, createdNotificationId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 26)
	public void testDeleteNotificationByNotificationId()  {

		System.out.println("************************************{DELETE NOTIFICATION BY NOTIFICATION ID}************************************");
		Response response= CommonServicesEndpoints.deleteUserNotificationsByNotificationId(auth_token, createdNotificationId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////// ALERT SUBSCRIPTIONS //////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 27)
	public void testGetAllUsersSubscriptions()  {

		System.out.println("************************************{GET ALL USERS SUBSCRIPTIONS}************************************");
		Response response= CommonServicesEndpoints.getUserSubscriptions(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 28)
	public void testCreateUsersSubscription()  {

		System.out.println("************************************{CREATE USER SUBSCRIPTIONS}************************************");
		Response response= CommonServicesEndpoints.postSubscriptionByUserId(auth_token, subscriptionUserId, newUserSubscriptionBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		createdSubscriptionId = response.then().log().body().statusCode(200).contentType("application/json").extract().path("data.id").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 29)
	public void testUsersSubscriptionsByUserId()  {

		System.out.println("************************************{GET USER SUBSCRIPTIONS BY USER ID}************************************");
		Response response= CommonServicesEndpoints.getSubscriptionByUserId(auth_token, subscriptionUserId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 30)
	public void testUsersSubscriptionsBySubscriptionId()  {

		System.out.println("************************************{GET USER SUBSCRIPTIONS BY SUBSCRIPTION ID}************************************");
		Response response= CommonServicesEndpoints.getSubscriptionBySubscriptionId(auth_token, subscriptionUserId,createdSubscriptionId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 31)
	public void testDeactivateUserSubscription()  {

		System.out.println("************************************{DEACTIVATE USER SUBSCRIPTION}************************************");
		Response response= CommonServicesEndpoints.patchDeactivateSubscriptionById(auth_token, subscriptionUserId, createdSubscriptionId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 32)
	public void testActivateUserSubscription()  {

		System.out.println("************************************{ACTIVATE USER SUBSCRIPTION}************************************");
		Response response= CommonServicesEndpoints.patchActivateSubscriptionById(auth_token, subscriptionUserId, createdSubscriptionId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 33)
	public void testDeleteUserSubscription()  {

		System.out.println("************************************{DELETE USER SUBSCRIPTION}************************************");
		Response response= CommonServicesEndpoints.deleteSubscriptionBySubscriptionId(auth_token, subscriptionUserId, createdSubscriptionId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// CONTRACT ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	@Test(priority = 34)
	public void testGetContractTemplateByIdOffering()  {

		System.out.println("************************************{GET CONTRACT TEMPLATE BY ID OFFERING}************************************");
		Response response= CommonServicesEndpoints.getContractTemplateByIdOffering(auth_token, dataOfferingId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// CREDENTIAL //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 35)
	public void testGetIssuedCredentialList()  {

		System.out.println("************************************{GET CREDENTIAL LIST}************************************");
		Response response= CommonServicesEndpoints.getIssuedCredentialList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 36)
	public void testGenerateCredential()  {

		System.out.println("************************************{GENERATE CREDENTIAL}************************************");
		Response response= CommonServicesEndpoints.getGenerateVerifiableCredential(auth_token, credential);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 37)
	public void testVerifyCredentialByjwk()  {

		System.out.println("************************************{VERIFY CREDENTIAL BY jwk}************************************");
		Response response= CommonServicesEndpoints.postVerifyCredential(auth_token, verifyCredentialBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 38)
	public void testRevokeCredentialByjwk()  {

		System.out.println("************************************{REVOKE CREDENTIAL}************************************");
		Response response= CommonServicesEndpoints.postRevokeVerifiableCredential(auth_token, revokeCredentialBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 39)
	public void testSubscribeIssuer()  {

		System.out.println("************************************{SUBSCRIBE THE ISSUER}************************************");
		Response response= CommonServicesEndpoints.getSubscribeIssuer(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*************************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 40)
	public void testUnsubscribeIssuer()  {

		System.out.println("************************************{UNSUBSCRIBE THE ISSUER}************************************");
		Response response= CommonServicesEndpoints.getUnsubscribeIssuer(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 41)
	public void testVerifyIssuerSubscription()  {

		System.out.println("************************************{VERIFY ISSUER SUBSCRIPTION}************************************");
		Response response= CommonServicesEndpoints.getVerifyIssuerSubscription(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////// EXCHANGE ///////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 42)
	public void testCreateInvoice()  {

		System.out.println("************************************{GENERATE INVOICE}************************************");
		Response response= CommonServicesEndpoints.postCreateInvoice(auth_token, bearedToken, fromDate, toDate);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 43)
	public void testDecryptCipherblock()  {

		System.out.println("************************************{DECRYPT CIPHERBLOCK}************************************");
		Response response= CommonServicesEndpoints.postDecryptCipherblock(auth_token, cipherblock, jwk);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 44)
	public void testDownloadFile()  {

		System.out.println("************************************{DOWNLOAD FILE}************************************");
		Response response= CommonServicesEndpoints.postDownloadFile(auth_token, data);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 45)
	public void testGetDataBlock()  {

		System.out.println("************************************{GET DATA BLOCK}************************************");
		Response response= CommonServicesEndpoints.postGetDataBlock(auth_token, bearedToken, data, blockId, blockAck);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 46)
	public void testGetFile()  {

		System.out.println("************************************{GET FILE}************************************");
		Response response= CommonServicesEndpoints.postGetFile(auth_token, bearedToken, data);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 47)
	public void testGetjwk()  {

		System.out.println("************************************{GET JWK}************************************");
		Response response= CommonServicesEndpoints.getJwk(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "*************************************");
	}

	@Test(priority = 48)
	public void testDeleteFile()  {

		System.out.println("************************************{DELETE FILE}************************************");
		Response response= CommonServicesEndpoints.deleteFile(auth_token, data);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////// MANAGEMENT //////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 49)
	public void testGetConfigurationInfo()  {

		System.out.println("************************************{GET SDK-RI CONFIG INFO}************************************");
		Response response= CommonServicesEndpoints.getSdkriConfigInfo(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////// TOKEN ////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(priority = 50)
	public void testAddMarketplaceToTreasury()  {

		System.out.println("************************************{ADD MARKETPLACE TO TREASURY}************************************");
		Response response= CommonServicesEndpoints.postAddMarketplace(auth_token, newMarketplaceBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 51)
	public void testMarketplaceBalance1()  {

		System.out.println("************************************{GET MARKETPLACE1 BALANCE}************************************");
		Response response= CommonServicesEndpoints.getMarketplaceBalanceByAddress(auth_token,marketplaceAddressSender2);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 52)
	public void testMarketplaceBalance2()  {

		System.out.println("************************************{GET MARKETPLACE2 BALANCE}************************************");
		Response response= CommonServicesEndpoints.getMarketplaceBalanceByAddress(auth_token,marketplaceAddressReceiver);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 53)
	public void testExchangeIn()  {

		System.out.println("************************************{MARKETPLACE EXCHANGE IN}************************************");
		Response response= CommonServicesEndpoints.postExchangeIn(auth_token,exchangeInBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 54)
	public void testExchangeOut()  {

		System.out.println("************************************{MARKETPLACE EXCHANGE OUT}************************************");
		Response response= CommonServicesEndpoints.postExchangeOut(auth_token,exchangeOutBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 55)
	public void testDeployOperation()  {

		System.out.println("************************************{MARKETPLACE DEPLOY OPERATION}************************************");
		Response response= CommonServicesEndpoints.postDeployTransaction(auth_token, deployOperationBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 56)
	public void testClearingOperation()  {

		System.out.println("************************************{MARKETPLACE CLEARING OPERATION}************************************");
		Response response= CommonServicesEndpoints.postClearingOperation(auth_token, marketplaceClearingBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 57)
	public void testMarketplaceIndexByAddress()  {

		System.out.println("************************************{MARKETPLACE INDEX BY ADDRESS}************************************");
		Response response= CommonServicesEndpoints.postClearingOperation(auth_token, marketplaceAddressSender1);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 58)
	public void testTokenPayment()  {

		System.out.println("************************************{MARKETPLACE TOKEN PAYMENT OPERATION}************************************");
		Response response= CommonServicesEndpoints.postTokenPaymentOperation(auth_token, paymentOperationBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 58)
	public void testSetPaidStatus()  {

		System.out.println("************************************{MARKETPLACE SET PAID STATUS}************************************");
		Response response= CommonServicesEndpoints.postSetPaidStatusOperation(auth_token, setPaidOperationBody.toString());
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 59)
	public void testGetTransactionStatusById()  {

		System.out.println("************************************{MARKETPLACE GET TRANSACTION STATUS BY TRANSFER ID}************************************");
		Response response= CommonServicesEndpoints.getTransactionStatusByTransferId(auth_token, transferId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}

	@Test(priority = 60)
	public void testTransactionStatusByHash()  {

		System.out.println("************************************{MARKETPLACE GET TRANSACTION STATUS BY TRANSACTION HASH}************************************");
		Response response= CommonServicesEndpoints.getTransactionByTransactionHash(auth_token, transactionHash);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("************************************DATA OBTAINED: " + data + "************************************");
	}
	**/
}
