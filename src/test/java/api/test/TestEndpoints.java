package api.test;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import api.engine.endpoints.CommonServicesEndpoints;
import io.restassured.response.Response;

/**
 * Perform API Testing for the services using CRUD Design Pattern
 */
public class TestEndpoints {
	
	String auth_token = "";
	
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
	    response.then().log().body().statusCode(200);
	    System.out.println("******************************************************************************************");
	}
	
	@Test(priority = 2)
	public void testOfferingSearchByCategory()  {
	
		System.out.println("*************************{OFFERING SEARCH BY CATEGORY}************************************");
	    Response response= CommonServicesEndpoints.searchOfferingByCategory("iot", auth_token);
	    response.then().log().body().statusCode(200);
	    System.out.println("******************************************************************************************");
	}

}
