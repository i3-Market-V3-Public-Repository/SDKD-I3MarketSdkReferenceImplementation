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

import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i3m.api.ApiException;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.DataProvider;
import com.i3m.model.backplane.PingResponse;
import com.i3m.model.data_access.InlineObject;
import com.i3m.model.data_access.Invoice;
import com.i3market.sdk.ri.common_services.data.discovery.*;
//import com.i3market.sdk.ri.common_services.data.exchange.AccountDataBlock;
import com.i3market.sdk.ri.common_services.data.exchange.AccountDataBlock;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.common_services.data.offering.DeleteOfferingById;
import com.i3market.sdk.ri.common_services.data.offering.RegisterDataProvider;
import com.i3market.sdk.ri.common_services.data.offering.UpdateOffering;
import com.i3market.sdk.ri.examples.PocBackplane;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

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
		configJson.put("data_access.url", SdkRiConstants.DATA_ACCESS_ENDPOINT);
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


	@GET
	@Path("/registration/categories-list")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve category list", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveCategoryList(
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveCategoryList().getOfferingByCategory(page, size, sort));
		} catch (ProcessingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return strJson;
	}


	@GET
	@Path("/offering/contract-parameter/{offeringId}/offeringId")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve contract parameters by offeringId", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveOfferingContractParametersByOfferingId(@PathParam("offeringId") String offeringId,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveContractParametersByOfferingId()
					.getOfferingContractsByOfferingId(offeringId, page, size, sort));
		} catch (ProcessingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return strJson;
	}


	@GET
	@Path("/offering/contract-parameter/{providerId}/providerId")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve contract parameters by providerId", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveOfferingContractParametersByProviderId(@PathParam("providerId") String providerId,
																 @QueryParam("page") @DefaultValue("0") Integer page,
																 @QueryParam("size") @DefaultValue("5") Integer size,
																 @QueryParam("sort") List<String> sort) throws ApiException {
		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveContractParametersByProviderId()
					.getOfferingContractsByProviderId(providerId, page, size, sort));
		} catch (ProcessingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return strJson;
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


	@DELETE
	@Path("/delete-offering/{id}")
	@ApiOperation(value = "delete a data offering by offeringId", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete offering")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse deleteDataOffering(@PathParam("id") String id) throws ApiException {
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


	@GET
	@Path("/offering/{id}/offeringId")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve a data offering by offering id", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveDataOfferingById(@PathParam("id") String id,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
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

	}


	@GET
	@Path("/offering/{id}/providerId")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all data offerings by a data providerId", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve offerings registered by this user")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveAllDataOfferingsByProviderId(@PathParam("id") String id,
													   @QueryParam("page") @DefaultValue("0") Integer page,
													   @QueryParam("size") @DefaultValue("5") Integer size,
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
	}


	@GET
	@Path("/offering/{category}")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve data offerings by a category", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveDataOfferingByCategory(@PathParam("category") String category,
												 @QueryParam("page") @DefaultValue("0") Integer page,
												 @QueryParam("size") @DefaultValue("5") Integer size,
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

	}


	@GET
	@Path("/offering/offerings-list")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve offering list from internal database only", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveDataOfferingList(
												 @QueryParam("page") @DefaultValue("0") Integer page,
												 @QueryParam("size") @DefaultValue("5") Integer size,
												 @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingList().getDataOfferingList(page, size, sort));
		} catch (JsonProcessingException | ProcessingException e) {

			e.printStackTrace();
		}
		return strJson;

	}


	@GET
	@Path("/offering/providers/{category}/category")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve  provider list by category from internal database only", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveProviderIdListByCategory(@PathParam("category") String category,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().
					writeValueAsString(new RetrieveListOfProvider().
							getProviderListByCategory(category,page, size, sort));
		} catch (JsonProcessingException | ProcessingException e) {

			e.printStackTrace();
		}
		return strJson;

	}

	@GET
	@Path("/offering/providers-list")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve  data provider list from internal database", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveAllProviders(@QueryParam("page") @DefaultValue("0") Integer page,
												   @QueryParam("size") @DefaultValue("5") Integer size,
												   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);

		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().
					writeValueAsString(new RetrieveListOfProvider().
							getAllProviders(page, size, sort));
		} catch (JsonProcessingException | ProcessingException e) {

			e.printStackTrace();
		}
		return strJson;

	}


	@POST
	@Path("/registration/data-provider")
	@ApiOperation(value = "register a data provider", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save provider info")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> registerDataProvider(@RequestBody DataProvider dataProvider) throws ApiException {
		return new RegisterDataProvider().saveDataProviderInfo(dataProvider);
	}


    @PATCH
    @Path("/update-offering")
    @ApiOperation(value = "update an offering", tags="common-services: offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to update offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse updateDataOffering(@RequestBody DataOffering dataOffering) throws ApiException {
          return new UpdateOffering().updateOffering(dataOffering);
    }

/**
 * Chi commented out from here to avoid issue with  the "non-repudiable-protocol" library
 */
//

	@POST
	@Path("/get-block/{data}")
	@ApiOperation(value = "get data block", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get data block") })
	@Produces({ "application/json", "application/xml" })
	public String accountDataBlock(@QueryParam("bearerToken") String bearerToken, @QueryParam("data") String data, @QueryParam("block_id") String block_id, @QueryParam("block_ack") String block_ack)
	throws ApiException {
		InlineObject blockIdAck = new InlineObject();
		blockIdAck.setBlockId(block_id);
		blockIdAck.setBlockAck(block_ack);
		return new AccountDataBlock().accountDataBlock(bearerToken, blockIdAck, data);
	}

	@POST
	@Path("/get-file/{data}")
	@ApiOperation(value = "get file", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get file") })
	@Produces({ "text/plain" })
	public String accountDataFile(@QueryParam("bearerToken") String bearerToken, @QueryParam("data") String data)
	throws ApiException {
		InlineObject blockIdAck = new InlineObject();
		blockIdAck.setBlockId("null");
		blockIdAck.setBlockAck("null");
		return new AccountDataBlock().getFile(bearerToken, blockIdAck, data);
	}

	@POST
	@Path("/decrypt")
	@ApiOperation(value = "decrypt cipherblock", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to decrypt cipherblock") })
	@Produces({ "text/plain" })
	public byte[] decrypt(@QueryParam("cipherblock") String cipherblock, @QueryParam("jwk") String jwk)
	throws ApiException {
		JSONObject jwk_toJson = new JSONObject(jwk);
		return new AccountDataBlock().decryptCipherblock(cipherblock, jwk_toJson);
	}

	@POST
	@Path("/create-invoice")
	@ApiOperation(value = "create invoice", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get invoice") })
	@Produces({ "application/json", "application/xml" })
	public Invoice createInvoice(@QueryParam("bearerToken") String bearerToken, @QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate)
	throws ApiException {
		return new AccountDataBlock().createInvoice(bearerToken, fromDate, toDate);
	}

	@GET
	@Path("/get-jwk")
	@ApiOperation(value = "get jwk", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get jwk") })
	@Produces({ "application/json", "application/xml" })
	public String getJwk()
	throws ApiException {
		return new AccountDataBlock().getJwk();
	}

	@DELETE
	@Path("/delete-file")
	@ApiOperation(value = "delete file", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to delete file") })
	@Produces({ "text/plain" })
	public String deleteFile(@QueryParam("data") String data)
	throws ApiException {
		return new AccountDataBlock().deleteFile(data);
	}

	@POST
	@Path("/download-file")
	@ApiOperation(value = "download file", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to download file")})
	@Produces({ "application/octet-stream" })
	public FileInputStream downloadFile(@QueryParam("data") String data)
	throws ApiException {
		return new AccountDataBlock().downloadFile(data);
	}

	@DELETE
	@Path("/alerts/{user_id}/subscriptions/{subscription_id}")
	@ApiOperation(value = "Delete a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	//@ApiResponses(value = {@ApiResponse(code = 406, message = "Empty body")})
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "Already exists subscription to category")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> deleteUserSubscription(@QueryParam("user_id") String user_id, @QueryParam("subscription_id") String subscription_id) throws ApiException {
		return new DeleteUserSubscription().deleteUserSubscription(user_id, subscription_id);
	}
}
