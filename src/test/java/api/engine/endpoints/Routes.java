package api.engine.endpoints;

/**
 * Routes class allow the testers to design the end-points for all services that are  available
 */
public class Routes {
	
	// The security server uri
	public static String security_token_uri= "http://83.149.125.78:8080/auth/realms/i3market/protocol/openid-connect/token";
	
	// The base uri is the resource where all the common services are running.
	public static String base_uri  ="http://95.211.3.244:8181/SdkRefImpl";

	// The get,put,delete uri routes the request to respective services to read,update,delete an offering by category.
    public static String get_put_delete_offering_search_category= "/api/sdk-ri/offering/{category}";
    
 // The get routes the request to respective services to read an offering template.
    public static String get_offering_template= "/api/sdk-ri/offering/template";
}