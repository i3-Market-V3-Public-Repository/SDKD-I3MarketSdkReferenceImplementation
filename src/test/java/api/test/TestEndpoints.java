package api.test;

import api.engine.endpoints.CommonServicesEndpoints;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Perform API Testing for the services using CRUD Design Pattern
 */
public class TestEndpoints {
	
	String auth_token = "";

	String newProviderString = "{\"providerId\":\"id123456789\",\"name\":\"provider test\",\"description\":\"this is a test\",\"organization\":[{\"organizationId\":\"organizationTest123\",\"name\":\"this organization is a test\",\"description\":\"this organization is a test plus description\",\"address\":\"this is an optional address\",\"contactPoint\":\"this is an optional contact point\"}]}";
	JSONObject newProviderBody = new JSONObject(newProviderString);

	String newOfferingString = "{\"provider\":\"id123456789\",\"owner\":\"Integration Offerings Test\",\"dataOfferingTitle\":\"offering integration test\",\"dataOfferingLabel\":\"Test data\",\"dataOfferingDescription\":\"Test data\",\"category\":\"Agriculture\",\"isActivated\":\"yes\",\"dataOfferingExpirationTime\":\"Test data\",\"contractParameters\":[{\"interestOfProvider\":\"Contract1\",\"interestDescription\":\"Contract1\",\"hasGoverningJurisdiction\":\"Contract1\",\"purpose\":\"Contract1\",\"purposeDescription\":\"Contract1\",\"hasIntendedUse\":[{\"processData\":\"true\",\"shareDataWithThirdParty\":\"true\",\"editData\":\"true\"}],\"hasLicenseGrant\":[{\"copyData\":\"true\",\"transferable\":\"true\",\"exclusiveness\":\"true\",\"revocable\":\"true\"}]}],\"hasPricingModel\":[{\"pricingModelName\":\"Test data\",\"basicPrice\":\"Test data\",\"currency\":\"Test data\",\"hasPaymentOnSubscription\":[{\"paymentOnSubscriptionName\":\"Test data\",\"paymentType\":\"Test data\",\"timeDuration\":\"Test data\",\"description\":\"Test data\",\"repeat\":\"Daily\",\"hasSubscriptionPrice\":\"Test data\",\"fromValue\":\"2021-10-04T14:20:23.162Z\",\"toValue\":\"2021-10-04T14:20:23.162Z\"}],\"hasPaymentOnPlan\":[{\"paymentOnPlanName\":\"Test data\",\"description\":\"Test data\",\"planDuration\":\"Test data\",\"hasPlanPrice\":\"Test data\"}],\"hasPaymentOnAPI\":[{\"paymentOnAPIName\":\"Test data\",\"description\":\"Test data\",\"numberOfObject\":\"Test data\",\"hasAPIPrice\":\"Test data\"}],\"hasPaymentOnUnit\":[{\"paymentOnUnitName\":\"Test data\",\"description\":\"Test data\",\"dataUnit\":\"Test data\",\"unitID\":\"Test data\",\"hasUnitPrice\":\"Test data\"}],\"hasPaymentOnSize\":[{\"paymentOnSizeName\":\"Test data\",\"description\":\"Test data\",\"dataSize\":\"Test data\",\"hasSizePrice\":\"Test data\"}],\"hasFreePrice\":[{\"hasPriceFree\":\"Test data\"}]}],\"hasDataset\":[{\"title\":\"Test data\",\"keyword\":\"Test data\",\"dataset\":\"Test data\",\"description\":\"Test data\",\"creator\":\"Test data\",\"issued\":\"2021-10-04T14:20:23.162Z\",\"modified\":\"2021-10-04T14:20:23.162Z\",\"temporal\":\"Test data\",\"language\":\"Test data\",\"spatial\":\"Test data\",\"accrualPeriodicity\":\"Test data\",\"temporalResolution\":\"Test data\",\"distribution\":[{\"title\":\"Test data\",\"distribution\":\"Test data\",\"description\":\"Test data\",\"license\":\"Test data\",\"conformsTo\":\"Test data\",\"mediaType\":\"Test data\",\"packageFormat\":\"Test data\",\"accessService\":[{\"conformsTo\":\"Test data\",\"endpointDescription\":\"Test data\",\"endpointURL\":\"Test data\",\"servesDataset\":\"Test data\",\"serviceSpecs\":\"Test data\"}]}],\"datasetInformation\":[{\"measurementType\":\"Test data\",\"measurementChannelType\":\"Test data\",\"sensorID\":\"Test data\",\"deviceID\":\"Test data\",\"cppType\":\"Test data\",\"sensorType\":\"Test data\"}],\"theme\":[\"Test data\"]}]}";
	JSONObject newOfferingBody = new JSONObject(newOfferingString);

	String updateOfferingString = "{\"dataOfferingId\":\"id123456789_dataoffering1\",\"provider\":\"id123456789\",\"owner\":\"Integration Offerings Test\",\"dataOfferingTitle\":\"offering integration test\",\"dataOfferingLabel\":\"Test data\",\"dataOfferingDescription\":\"Test data\",\"category\":\"Agriculture\",\"isActivated\":\"yes\",\"dataOfferingExpirationTime\":\"Test data\",\"contractParameters\":[{\"interestOfProvider\":\"Contract1\",\"interestDescription\":\"Contract1\",\"hasGoverningJurisdiction\":\"Contract1\",\"purpose\":\"Contract1\",\"purposeDescription\":\"Contract1\",\"hasIntendedUse\":[{\"processData\":\"true\",\"shareDataWithThirdParty\":\"true\",\"editData\":\"true\"}],\"hasLicenseGrant\":[{\"copyData\":\"true\",\"transferable\":\"true\",\"exclusiveness\":\"true\",\"revocable\":\"true\"}]}],\"hasPricingModel\":[{\"pricingModelName\":\"Test data\",\"basicPrice\":\"Test data\",\"currency\":\"Test data\",\"hasPaymentOnSubscription\":[{\"paymentOnSubscriptionName\":\"Test data\",\"paymentType\":\"Test data\",\"timeDuration\":\"Test data\",\"description\":\"Test data\",\"repeat\":\"Daily\",\"hasSubscriptionPrice\":\"Test data\",\"fromValue\":\"2021-10-04T14:20:23.162Z\",\"toValue\":\"2021-10-04T14:20:23.162Z\"}],\"hasPaymentOnPlan\":[{\"paymentOnPlanName\":\"Test data\",\"description\":\"Test data\",\"planDuration\":\"Test data\",\"hasPlanPrice\":\"Test data\"}],\"hasPaymentOnAPI\":[{\"paymentOnAPIName\":\"Test data\",\"description\":\"Test data\",\"numberOfObject\":\"Test data\",\"hasAPIPrice\":\"Test data\"}],\"hasPaymentOnUnit\":[{\"paymentOnUnitName\":\"Test data\",\"description\":\"Test data\",\"dataUnit\":\"Test data\",\"unitID\":\"Test data\",\"hasUnitPrice\":\"Test data\"}],\"hasPaymentOnSize\":[{\"paymentOnSizeName\":\"Test data\",\"description\":\"Test data\",\"dataSize\":\"Test data\",\"hasSizePrice\":\"Test data\"}],\"hasFreePrice\":[{\"hasPriceFree\":\"Test data\"}]}],\"hasDataset\":[{\"title\":\"Test data\",\"keyword\":\"Test data\",\"dataset\":\"Test data\",\"description\":\"Test data\",\"creator\":\"Test data\",\"issued\":\"2021-10-04T14:20:23.162Z\",\"modified\":\"2021-10-04T14:20:23.162Z\",\"temporal\":\"Test data\",\"language\":\"Test data\",\"spatial\":\"Test data\",\"accrualPeriodicity\":\"Test data\",\"temporalResolution\":\"Test data\",\"distribution\":[{\"title\":\"Test data\",\"distribution\":\"Test data\",\"description\":\"Test data\",\"license\":\"Test data\",\"conformsTo\":\"Test data\",\"mediaType\":\"Test data\",\"packageFormat\":\"Test data\",\"accessService\":[{\"conformsTo\":\"Test data\",\"endpointDescription\":\"Test data\",\"endpointURL\":\"Test data\",\"servesDataset\":\"Test data\",\"serviceSpecs\":\"Test data\"}]}],\"datasetInformation\":[{\"measurementType\":\"Test data\",\"measurementChannelType\":\"Test data\",\"sensorID\":\"Test data\",\"deviceID\":\"Test data\",\"cppType\":\"Test data\",\"sensorType\":\"Test data\"}],\"theme\":[\"Test data\"]}]}";
	JSONObject updateOfferingBody = new JSONObject(updateOfferingString);

	@BeforeTest
	public void beforeTest()  {
	
		System.out.println("*****************GETTING AUTH TOKEN UNDER TEST**********************************");
		Response response= CommonServicesEndpoints.getAuthToken();
		auth_token = response.then().log().body().statusCode(200).extract().path("access_token");
		System.out.println("***************** TOKEN OBTAINED: " + auth_token + " ****************************");
	}
	
	@Test(priority = 1)
	public void testOfferingTemplate()  {
	
		System.out.println("*************************{OFFERING TEMPLATE}************************************");
	    Response response= CommonServicesEndpoints.getOfferingTemplate(auth_token);
		String data = response.then().log().body().statusCode(200).extract().path("data");
	    System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 2)
	public void testProviderCreation()  {

		System.out.println("*************************{PROVIDER CREATION}************************************");

		Response response= CommonServicesEndpoints.postDataProvider(auth_token, newProviderBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 3)
	public void testOfferingCreation()  {

		System.out.println("*************************{OFFERING CREATION}************************************");

		Response response= CommonServicesEndpoints.postDataOffering(auth_token, newOfferingBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 4)
	public void testUpdateOffering()  {

		System.out.println("*************************{OFFERING UPDATE}************************************");

		Response response= CommonServicesEndpoints.updateDataOffering(auth_token, updateOfferingBody.toString());
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 5)
	public void testCategoriesList()  {

		System.out.println("*************************{CATEGORIES LISTING}************************************");
		Response response= CommonServicesEndpoints.searchCatergoriesList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 6)
	public void testOfferingSearchByCategory()  {
	
		System.out.println("*************************{OFFERING SEARCH BY CATEGORY}************************************");
	    Response response= CommonServicesEndpoints.searchOfferingByCategory(auth_token,"Agriculture");
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 7)
	public void testSearchOfferingByProviderId()  {

		System.out.println("*************************{OFFERING SEARCH BY PROVIDERID}************************************");
		String providerId = "id123456789";

		Response response= CommonServicesEndpoints.searchOfferingByProviderId(auth_token, providerId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 8)
	public void testSearchOfferingByOfferingId()  {

		System.out.println("*************************{OFFERING SEARCH BY OFFERINGID}************************************");
		String offeringId = "id123456789_dataoffering1";

		Response response= CommonServicesEndpoints.searchOfferingByOfferingId(auth_token, offeringId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 9)
	public void testProvidersByCategory()  {

		System.out.println("*************************{OFFERING SEARCH BY CATEGORY}************************************");
		String category = "Agriculture";

		Response response= CommonServicesEndpoints.searchOfferingByCategory(auth_token,category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 10)
	public void testProvidersByCategories()  {

		System.out.println("*************************{PROVIDERS LIST}************************************");
		String category = "Agriculture";
		Response response= CommonServicesEndpoints.getProvidersByCategory(auth_token, category);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 11)
	public void testProvidersList()  {

		System.out.println("*************************{PROVIDERS LIST}************************************");
		Response response= CommonServicesEndpoints.getProvidersList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 12)
	public void testOfferingsList()  {

		System.out.println("*************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 13)
	public void testContractByProviderId()  {

		System.out.println("*************************{CONTRACT BY PROVIDERID}************************************");
		String providerId = "id123456789";

		Response response= CommonServicesEndpoints.getContractByProviderId(auth_token, providerId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 14)
	public void testContractByOfferingId()  {

		System.out.println("*************************{CONTRACT BY OFFERINGID}************************************");
		String offeringId = "id123456789_dataoffering1";

		Response response= CommonServicesEndpoints.getContractByOfferingId(auth_token, offeringId);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

	@Test(priority = 15)
	public void testDeleteOffering()  {

		System.out.println("*************************{OFFERING DELETE}************************************");
		String offeringId = "id123456789_dataoffering1";

		Response response= CommonServicesEndpoints.deleteOffering(auth_token, offeringId);
		response.then().log().body().statusCode(200);
		System.out.println("******************************************************************************************");
	}

	@Test(priority = 16)
	public void testGetOfferingList()  {

		System.out.println("*************************{OFFERINGS LIST}************************************");
		Response response= CommonServicesEndpoints.getOfferingsList(auth_token);
		Object data = response.then().log().body().statusCode(200).extract().path("data").toString();
		System.out.println("*********************DATA OBTAINED: " + data + "*******************************************");
	}

}
