# i3m-sdk-reference-implementation

SDK reference implementation that includes common services.

WEB REOURCE: com.i3market.sdk.ri.resource.SdkRiHub

## Setup

Clone the repository, and download the dependencies:

```shell script
git@gitlab.com:i3-market/code/sdk/i3m-sdk-reference-implementation.git
```

## Keycloak Setup
default config file is palced in /src/main/wabapp/WEB-INF/keycloak.json

{
  "realm": "i3market",
  "auth-server-url": "http://83.149.125.78:8080/auth/",
  "ssl-required": "none",
  "resource": "SDK-RI_Client",
  "credentials": {
    "secret": "703e0db9-a646-4f1d-bdc6-2b3fe20db08a"
  },
  "use-resource-role-mappings": true,
  "confidential-port": 0
} 

To provide your keycloak setup you should updated providing a realm, auth-server-url, resource and credentials.

## Running the SDK-RI with docker

You can use docker to run the SDK-RI.  
To do so, follow the same setup instructions as above.

Then, just build and run using:

```shell
docker build --no-cache -t i3m/i3market-sdk-ri:latest .
docker push i3m/i3market-sdk-ri:latest
docker run --name sdk-ri -p 8181:8080 i3m/i3market-sdk-ri
```

SDK-RI container is built over a jetty image and deploy the SdkREfIMpl war file into jetty. 

Finally just go to http:/$deploy_host/SdkRefImpl for accesing to SDK-RI REST API.


# Contributing to the SDK-RI project

- The developer includes the service (as a get method) in the SdkRiHUB. i.e:

```	
	@GET
	@Path("/requestDataItemPurchase")
	@ApiOperation(value = "")
    @ApiResponses(value={@ApiResponse(code = 401, message = "Unauthorized")}) 
	@Produces({ "application/json", "application/xml" })
	public void requestDataItemPurchase() throws Exception {
		RequestingDataItemPurchase.requestDataItemPurchase();
	}
```
	
- The developer includes the logic behind the service in classes (each per service) in the corresponding module


# Configuring and using SDK-RI

- The marketplace will have all the common services exposed in a sdk-ri/ endpoint
- Each marketplace-end user, which pursues making use of the SDK-RI, should configure the SDK-RI by means of:
   - Pointing to the Backplane endpoint(s) hosted in a concrete i3-Market node (i.e: Backplane API node1, OpenID Connect Provider API node1, Verifying Credential service API node1)
   - pointing to the Wallet endpoint hosted locally.
_ This configuration should be defined in the SDK-RI properties file placed at "src/resources/sdk_ri_config.properties"
	
- An example of setup could be the following:
	
	- backplane.url =  http://95.211.3.244:3000/
	- oidc.url =  https://identity1.i3-market.eu/
	- verifiable_credentials.url = https://identity1.i3-market.eu/vc/api-spec/ui/


