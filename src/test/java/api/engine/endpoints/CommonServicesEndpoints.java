package api.engine.endpoints;

import java.util.ArrayList;

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
	
	public static Response getOfferingTemplate(String token) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("Authorization", "Bearer " + token).		
	    	when().get(Routes.get_offering_template);
        return response;
	}
	
	public static Response searchOfferingByCategory(String category, String token) {
	    RestAssured.baseURI= Routes.base_uri;
	    Response response=RestAssured.
	    	given().header("Authorization", "Bearer " + token).		
	    	pathParam("category", category).
	    	when().get(Routes.get_put_delete_offering_search_category);
        return response;
	}

}
