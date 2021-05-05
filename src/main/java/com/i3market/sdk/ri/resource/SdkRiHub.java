package com.i3market.sdk.ri.resource;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.json.JSONObject;

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
	
}
