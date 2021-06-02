package com.i3market.sdk.ri.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;

import com.i3m.api.ApiException;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.RegistrationOfferingDTO;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByCategory;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingById;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByProviderId;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingTemplate;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.common_services.data.offering.DeleteOfferingById;
import org.json.JSONObject;

import com.i3m.model.backplane.PingResponse;
import com.i3m.model.oidc.JWKSet;
import com.i3market.sdk.ri.examples.PocBackplane;
import com.i3market.sdk.ri.examples.PocOIDC;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
	public com.i3m.api.ApiResponse<Void> registerDataOffering(DataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(dataOffering);
	}

	@GET
	@Path("/offering/{id}/offeringId")
	@ApiOperation(value = "retrieve a data offering by id")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveDataOfferingById(@PathVariable String id, @RequestParam("page") int page,
																						   @RequestParam("size") int size,
																						   @RequestParam("sort") String sort) throws ApiException {
		return new RetrieveOfferingById().getDataOfferingById(id, page, size, sort);
	}

	@GET
	@Path("/offering/{id}/providerId")
	@ApiOperation(value = "retrieve all data offerings registered with data provider id")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve offerings registered by this user")})
	public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveAllDataOfferingsByProviderId(@PathVariable String id, @RequestParam("page") int page,
																		  @RequestParam("size") int size,
																		  @RequestParam("sort") String sort) throws ApiException {

		return new RetrieveOfferingByProviderId().getOfferingByProviderId(id, page,size,sort);
	}

	@GET
	@Path("/offering/{category}")
	@ApiOperation(value = "retrieve a data offering by category")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveDataOfferingByCategory(@PathVariable String category, @RequestParam("page") int page,
																		@RequestParam("size") int size,
																		@RequestParam("sort") String sort) throws ApiException {
		return new RetrieveOfferingByCategory().getOfferingByCategory(category, page, size, sort);
	}

	@PATCH
	@Path("/update-offering")
	@ApiOperation(value = "update an offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to update offering")})
	public com.i3m.api.ApiResponse updateDataOffering(DataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(dataOffering);
	}


	@DELETE
	@Path("/delete-offering/{id}")
	@ApiOperation(value = "delete a data offering by id")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete offering")})
	public com.i3m.api.ApiResponse deleteDataOffering(@PathVariable String id) throws ApiException {
		return new DeleteOfferingById().deleteOffering(id);
	}

	@GET
	@Path("/offering/template")
	@ApiOperation(value = "get a template for data offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to fetch offering template")})
	public String getDataOfferingTemplate() throws ApiException {
		return new RetrieveOfferingTemplate().getDataOfferingTemplate();
	}

}
