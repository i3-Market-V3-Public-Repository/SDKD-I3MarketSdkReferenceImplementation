# i3m-sdk-reference-implementation

SDK reference implementation that includes common services.

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
