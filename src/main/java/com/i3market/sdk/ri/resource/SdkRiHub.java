/*
 * Copyright 2019 Atos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.i3market.sdk.ri.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.i3m.api.ApiException;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.DataProvider;
import com.i3m.model.backplane.RegistrationOfferingDTO;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByCategory;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingById;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByProviderId;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingTemplate;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.common_services.data.offering.DeleteOfferingById;
import com.i3market.sdk.ri.common_services.data.offering.RegisterDataProvider;
import com.i3market.sdk.ri.common_services.data.offering.UpdateOffering;
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
import org.springframework.web.bind.annotation.RequestBody;

@Path("sdk-ri")
@Api(value = "/", tags = "common-services")
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
	
//	@GET
//	@Path("/example/backplane/pingBackplane")
//	@ApiOperation(
//            value = "Ping backplane instance using PingController ")
//    @ApiResponses(value={                    
//                    @ApiResponse(code = 401, message = "Unauthorized"
//                    )
//            }) 
//	@Produces({ "application/json", "application/xml" })
//	public PingResponse runBackplaneExample(@QueryParam("back_token") String jwt
//				 
//			) throws Exception {
//		
//		return new PocBackplane().ping(jwt);
//	}
//	
//	@GET
//	@Path("/example/oidc/getOidcWTKS")
//	@ApiOperation(
//            value = "Get OIDC Web tokek key set from OidcCoreController ")
//    @ApiResponses(value={                    
//                    @ApiResponse(code = 401, message = "Unauthorized"
//                    )
//            }) 
//	@Produces({ "application/json", "application/xml" })
//	public com.i3m.api.ApiResponse<JWKSet> runOidcExample(@QueryParam("back_token") String jwt
//				 
//			) throws Exception {
//		
//		return new PocOIDC().getJWKS(jwt);
//	}

	@POST
	@Path("/registration/data-provider")
	@ApiOperation(value = "register a data provider")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save provider info")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> registerDataProvider(@RequestBody DataProvider dataProvider) throws ApiException {
		return new RegisterDataProvider().saveDataProviderInfo(dataProvider);
	}

	@POST
	@Path("/registration/data-offering")
	@ApiOperation(value = "register a data offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save offering")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> registerDataOffering(@RequestBody DataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(dataOffering);
	}


     @GET
     @Path("/offering/{id}/offeringId")
     @ApiOperation(value = "retrieve a data offering by id")
     @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	 @Produces({ "application/json", "application/xml" })
     public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveDataOfferingById(@QueryParam("offering_id") String id,
																							   @QueryParam("page") @DefaultValue("0") Integer page,
																							   @QueryParam("size") @DefaultValue("10") Integer size,
																							   @QueryParam("sort") List<String> sort) throws ApiException {
        return new RetrieveOfferingById().getDataOfferingById(id, page, size, sort);
     }

     @GET
     @Path("/offering/{id}/providerId")
     @ApiOperation(value = "retrieve all data offerings registered with data provider id")
     @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve offerings registered by this user")})
	 @Produces({ "application/json", "application/xml" })
     public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveAllDataOfferingsByProviderId(@QueryParam("provider_id") String id,
																										@QueryParam("page") @DefaultValue("0") Integer page,
																										@QueryParam("size") @DefaultValue("10") Integer size,
																										@QueryParam("sort") List<String> sort) throws ApiException {

        return new RetrieveOfferingByProviderId().getOfferingByProviderId(id, page,size,sort);
	}

	@GET
    @Path("/offering/{category}")
    @ApiOperation(value = "retrieve a data offering by category")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse<List<RegistrationOfferingDTO>> retrieveDataOfferingByCategory(@QueryParam("category") String category,
																										 @QueryParam("page") @DefaultValue("0") Integer page,
																										 @QueryParam("size") @DefaultValue("10") Integer size,
																										 @QueryParam("sort") List<String> sort) throws ApiException {
    	 return new RetrieveOfferingByCategory().getOfferingByCategory(category, page, size, sort);
    }

    @PATCH
    @Path("/update-offering")
    @ApiOperation(value = "update an offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to update offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse updateDataOffering(@RequestBody DataOffering dataOffering) throws ApiException {
          return new UpdateOffering().updateOffering(dataOffering);
    }


    @DELETE
    @Path("/delete-offering/{id}")
    @ApiOperation(value = "delete a data offering by id")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse deleteDataOffering(@QueryParam("offering_id") String id) throws ApiException {
                return new DeleteOfferingById().deleteOffering(id);
    }

	@GET
	@Path("/offering/template")
	@ApiOperation(value = "get a template for data offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to fetch offering template")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<String> getDataOfferingTemplate() throws ApiException {
		return new RetrieveOfferingTemplate().getDataOfferingTemplate();
	}

}
