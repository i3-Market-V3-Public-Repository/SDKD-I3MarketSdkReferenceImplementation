package api.engine.endpoints;

/**
 * Routes class allow the testers to design the end-points for all services that are  available
 */
public class Routes {
	
	// The security server uri
	public static String security_token_uri= "http://83.149.125.78:8080/auth/realms/i3market/protocol/openid-connect/token";
	
	// The base uri is the resource where all the common services are running.
	public static String base_uri  ="http://95.211.3.244:8182/SdkRefImpl";

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

}