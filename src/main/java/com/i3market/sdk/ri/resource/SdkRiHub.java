package com.i3market.sdk.ri.resource;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.ws.rs.*;

import com.i3m.api.ApiException;
import com.i3m.model.backplane.DataOffering;
import com.i3market.sdk.ri.common_services.data.discovery.CreateOffering;
import org.json.JSONObject;

import com.i3m.model.backplane.HelloResponse;
import com.i3m.model.backplane.PingResponse;
import com.i3m.model.oidc.JWKSet;
import com.i3m.model.oidc.OpenIDProviderMetadata;
import com.i3market.sdk.ri.examples.PocBackplane;
import com.i3market.sdk.ri.examples.PocOIDC;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("sdk-ri")
@Api(value = "/")
@Produces({ "application/json", "application/xml" })
public class SdkRiHub {
	
	
	private Logger LOGGER = Logger.getLogger(SdkRiHub.class.getName());

	//#####################################################################
	//	API REST METHODS
	//#####################################################################
	
	@GET
	@Path("/getConfigurationInfo")
	@ApiOperation(
            value = "Get SDK-RI configuration info ")
    @ApiResponses(value={                    
                    @ApiResponse(code = 401, message = "Unauthorized"
                    )
            }) 
	@Produces({ "application/json", "application/xml" })
	public String getConfigurationInfo(
				 
			) throws Exception {
		
		JSONObject configJson=new JSONObject();    
		configJson.put("backplane_instance", SdkRiConstants.BACKPLANE_ENDPOINT);    
		configJson.put("oidc_isntance", SdkRiConstants.OIDC_ENDPOINT);    
		configJson.put("vc_instance", SdkRiConstants.VC_ENDPOINT); 
	
		return configJson.toString();
	}
	
	@GET
	@Path("/example/backplane/pingBackplane")
	@ApiOperation(
            value = "Ping backplane instance using PingController ")
    @ApiResponses(value={                    
                    @ApiResponse(code = 401, message = "Unauthorized"
                    )
            }) 
	@Produces({ "application/json", "application/xml" })
	public PingResponse runBackplaneExample(@QueryParam("back_token") String jwt
				 
			) throws Exception {
		
		return new PocBackplane().ping(jwt);
	}
	
	@GET
	@Path("/example/oidc/getOidcWTKS")
	@ApiOperation(
            value = "Get OIDC Web tokek key set from OidcCoreController ")
    @ApiResponses(value={                    
                    @ApiResponse(code = 401, message = "Unauthorized"
                    )
            }) 
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<JWKSet> runOidcExample(@QueryParam("back_token") String jwt
				 
			) throws Exception {
		
		return new PocOIDC().getJWKS(jwt);
	}


	@POST
	@Path("/data-offering")
	@ApiOperation(value = "register a data offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save offering")})
	public com.i3m.api.ApiResponse<Void> dataOffering(DataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(dataOffering);
	}
}
