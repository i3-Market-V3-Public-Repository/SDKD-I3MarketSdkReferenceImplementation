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
				when().get(Routes.get_contract_parameters_by_offeringid, id);
		return response;
	}

	public static Response getContractByProviderId(String token, String id){
		RestAssured.baseURI= Routes.base_uri;
		Response response=RestAssured.
				given().header("Authorization", "Bearer " + token).
				when().get(Routes.get_contract_parameters_by_providerid, id);
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
}
