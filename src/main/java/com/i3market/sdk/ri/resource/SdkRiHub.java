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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i3m.api.ApiException;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.DataProvider;
import com.i3m.model.backplane.RegistrationOfferingDTO;
import com.i3market.sdk.ri.common_services.alerts.subscriptions.ActivateUserSubscription;
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
            value = "Get SDK-RI configuration info ", tags="common-services: management")
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
	@ApiOperation(value = "register a data provider", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save provider info")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> registerDataProvider(@RequestBody DataProvider dataProvider) throws ApiException {
		return new RegisterDataProvider().saveDataProviderInfo(dataProvider);
	}

	@POST
	@Path("/registration/data-offering")
	@ApiOperation(value = "register a data offering", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save offering")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> registerDataOffering(@RequestBody DataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(dataOffering);
	}


     @GET
     @Path("/offering/{id}/offeringId")
     @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
     @ApiOperation(value = "retrieve a data offering by id", tags="common-services: offering")
     @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	 @Produces({ "application/json", "application/xml" })
     public String retrieveDataOfferingById(@QueryParam("offering_id") String id,
																							   @QueryParam("page") @DefaultValue("0") Integer page,
																							   @QueryParam("size") @DefaultValue("10") Integer size,
																							   @QueryParam("sort") List<String> sort) throws ApiException {
			 										
    	 String strJson = "{}";
 		 ObjectMapper mapper = new ObjectMapper();
 		 mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
 		 try {
 			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingById().getDataOfferingById(id, page, size, sort));
 		 } catch (ProcessingException | JsonProcessingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		 }
 		 return strJson;
    	 //return new RetrieveOfferingById().getDataOfferingById(id, page, size, sort);
     }

     @GET
     @Path("/offering/{id}/providerId")
     @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
     @ApiOperation(value = "retrieve all data offerings registered with data provider id", tags="common-services: offering")
     @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve offerings registered by this user")})
	 @Produces({ "application/json", "application/xml" })
     public String retrieveAllDataOfferingsByProviderId(@QueryParam("provider_id") String id,
																										@QueryParam("page") @DefaultValue("0") Integer page,
																										@QueryParam("size") @DefaultValue("10") Integer size,
																										@QueryParam("sort") List<String> sort) throws ApiException  {
    	 String strJson = "{}";
 		 ObjectMapper mapper = new ObjectMapper();
 		 mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
 		 try {
 			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingByProviderId().getOfferingByProviderId(id, page,size,sort));
 		 } catch (ProcessingException | JsonProcessingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		 } 
 		 return strJson; 																								
 	     //	 return new RetrieveOfferingByProviderId().getOfferingByProviderId(id, page,size,sort);
	}

	@GET
    @Path("/offering/{category}")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @ApiOperation(value = "retrieve a data offering by category", tags="common-services: offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
    public String retrieveDataOfferingByCategory(@QueryParam("category") String category,
													 @QueryParam("page") @DefaultValue("0") Integer page,
													 @QueryParam("size") @DefaultValue("10") Integer size,
		 											 @QueryParam("sort") List<String> sort) throws ApiException {
		
		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingByCategory().getOfferingByCategory(category, page, size, sort));
		} catch (JsonProcessingException | ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strJson;
		//return mapper.readValue(new RetrieveOfferingByCategory().getOfferingByCategory(category, page, size, sort).toString(), List.class);																								
		//return new RetrieveOfferingByCategory().getOfferingByCategory(category, page, size, sort);
    }

    @PATCH
    @Path("/update-offering")
    @ApiOperation(value = "update an offering", tags="common-services: offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to update offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse updateDataOffering(@RequestBody DataOffering dataOffering) throws ApiException {
          return new UpdateOffering().updateOffering(dataOffering);
    }


    @DELETE
    @Path("/delete-offering/{id}")
    @ApiOperation(value = "delete a data offering by id", tags="common-services: offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse deleteDataOffering(@QueryParam("offering_id") String id) throws ApiException {
                return new DeleteOfferingById().deleteOffering(id);
    }

	@GET
	@Path("/offering/template")
	@ApiOperation(value = "get a template for data offering", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to fetch offering template")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<String> getDataOfferingTemplate() throws ApiException {
		return new RetrieveOfferingTemplate().getDataOfferingTemplate();
	}
	@POST
	@Path("/alerts/{user_id}/subscriptions/{subscription_id}/activate")
	@ApiOperation(value = "Deactivate a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	//@ApiResponses(value = {@ApiResponse(code = 406, message = "Empty body")})
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "Already exists subscription to category")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> deactivateSubscription(@QueryParam("user_id") String user_id, @QueryParam("subscription_id") String subscription_id) throws ApiException {
		return new ActivateUserSubscription().activateUserSubscription(user_id, subscription_id);
	}

}
