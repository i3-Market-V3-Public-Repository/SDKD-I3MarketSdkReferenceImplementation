/*
  Copyright 2020-2022 i3-MARKET Consortium:

  ATHENS UNIVERSITY OF ECONOMICS AND BUSINESS - RESEARCH CENTER
  ATOS SPAIN SA
  EUROPEAN DIGITAL SME ALLIANCE
  GFT ITALIA SRL
  GUARDTIME OU
  HOP UBIQUITOUS SL
  IBM RESEARCH GMBH
  IDEMIA FRANCE
  SIEMENS AKTIENGESELLSCHAFT
  SIEMENS SRL
  TELESTO TECHNOLOGIES PLIROFORIKIS KAI EPIKOINONION EPE
  UNIVERSITAT POLITECNICA DE CATALUNYA
  UNPARALLEL INNOVATION LDA

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
package com.i3market.sdk.ri.resource;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.i3m.model.backplane.*;
import com.i3market.sdk.ri.common_services.notification.services.*;
import com.i3market.sdk.ri.common_services.tokenizer.TokenizerController;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i3m.api.ApiException;
import com.i3m.model.data_access.InlineObject;
import com.i3m.model.data_access.Invoice;
import com.i3market.sdk.ri.common_services.alerts.subscriptions.CreateUserSubscription;
import com.i3market.sdk.ri.common_services.alerts.subscriptions.DeleteUserSubscription;
import com.i3market.sdk.ri.common_services.alerts.subscriptions.GetSubscriptions;
import com.i3market.sdk.ri.common_services.alerts.subscriptions.ModifyUserSubscription;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveCategoryList;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveContractParametersByOfferingId;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveListOfProvider;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByCategory;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingById;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingByProviderId;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingList;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveOfferingTemplate;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveTotalOfferingAndOfferingList;
import com.i3market.sdk.ri.common_services.data.exchange.AccountDataBlock;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.common_services.data.offering.DeleteDataProvider;
import com.i3market.sdk.ri.common_services.data.offering.DeleteOfferingById;
import com.i3market.sdk.ri.common_services.data.offering.RegisterDataProvider;
import com.i3market.sdk.ri.common_services.data.offering.UpdateOffering;
import com.i3market.sdk.ri.common_services.notification.notifications.CreateNotification;
import com.i3market.sdk.ri.common_services.notification.notifications.DeleteNotification;
import com.i3market.sdk.ri.common_services.notification.notifications.ModifyNotification;
import com.i3market.sdk.ri.common_services.notification.notifications.RetrieveNotifications;
import com.i3market.sdk.ri.common_services.purchase.BackplaneClient;
import com.i3market.sdk.ri.common_services.purchase.RequestingDataItemPurchase;
import com.i3market.sdk.ri.common_services.verifiableCredentials.VerifiableCredentials;
import com.i3market.sdk.ri.common_services.pricingManager.PricingManager;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveFederatedOfferingById;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveFederatedOfferingByCategory;
import com.i3market.sdk.ri.common_services.data.discovery.RetrieveFederatedOfferingList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//import com.i3market.sdk.ri.common_services.data.exchange.AccountDataBlock;

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
	@Path("/registration/offerings")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "Get total offering by category and providerID", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveTotalOfferingAndOfferingList(

			@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
			@QueryParam("providerId") @DefaultValue("All") String providerId,
			@QueryParam("category") @DefaultValue("All") String category,
			@QueryParam("page") @DefaultValue("0") Integer page,
			@QueryParam("size") @DefaultValue("5") Integer size,
			@QueryParam("sortBy") @DefaultValue("desc") String sortBy,
			@QueryParam("orderBy") @DefaultValue("time") String orderBy) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveTotalOfferingAndOfferingList().getTotalOfferingAndOfferingList(access_token, id_token, providerId, category, page, size, sortBy, orderBy));
					
		} catch (ProcessingException | JsonProcessingException e) {
			e.printStackTrace();
		}
		return strJson;
	}

	@GET
	@Path("/registration/categories-list")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve category list", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveCategoryList(
									   @HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,   
			                           @QueryParam("page") @DefaultValue("0") Integer page,
									   @QueryParam("size") @DefaultValue("5") Integer size,
									   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveCategoryList().getOfferingByCategory(access_token, id_token, page, size, sort));
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
	public String retrieveOfferingContractParametersByOfferingId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @PathParam("offeringId") String offeringId,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveContractParametersByOfferingId()
					.getOfferingContractsByOfferingId(access_token, id_token, offeringId, page, size, sort));
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
	public com.i3m.api.ApiResponse<SemanticEngineDataOfferingDto> registerDataOffering(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody SemanticEngineDataOffering dataOffering) throws ApiException {
		return new CreateOffering().createOffering(access_token, id_token, dataOffering);
	}


	@DELETE
	@Path("/delete-offering/{id}")
	@ApiOperation(value = "delete a data offering by offeringId", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete offering")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse deleteDataOffering(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("id") String id) throws ApiException {
		return new DeleteOfferingById().deleteOffering(access_token, id_token, id);
	}


	@GET
	@Path("/offering/template")
	@ApiOperation(value = "get a template for data offering", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to fetch offering template")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<String> getDataOfferingTemplate(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new RetrieveOfferingTemplate().getDataOfferingTemplate(access_token, id_token);
	}


	@GET
	@Path("/offering/{id}/offeringId")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve a data offering by offering id", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public String retrieveDataOfferingById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @PathParam("id") String id,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
        
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingById().getDataOfferingById(access_token, id_token, id, page, size, sort));
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
	public String retrieveAllDataOfferingsByProviderId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
													   @PathParam("id") String id,
													   @QueryParam("page") @DefaultValue("0") Integer page,
													   @QueryParam("size") @DefaultValue("5") Integer size,
													   @QueryParam("sort") List<String> sort) throws ApiException  {
		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingByProviderId().getOfferingByProviderId(access_token, id_token, id, page,size,sort));
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
	public String retrieveDataOfferingByCategory(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
												 @PathParam("category") String category,
												 @QueryParam("page") @DefaultValue("0") Integer page,
												 @QueryParam("size") @DefaultValue("5") Integer size,
												 @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingByCategory().getOfferingByCategory(access_token, id_token, category, page, size, sort));
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
	public String retrieveDataOfferingList(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveOfferingList().getDataOfferingList(access_token, id_token, page, size, sort));
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
	public String retrieveProviderIdListByCategory(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
			                               @PathParam("category") String category,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().
					writeValueAsString(new RetrieveListOfProvider().
							getProviderListByCategory(access_token, id_token, category,page, size, sort));
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
	public String retrieveAllProviders(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("page") @DefaultValue("0") Integer page,
												   @QueryParam("size") @DefaultValue("5") Integer size,
												   @QueryParam("sort") List<String> sort) throws ApiException {

		String strJson = "{}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature. ACCEPT_SINGLE_VALUE_AS_ARRAY);

		try {
			strJson = mapper.writerWithDefaultPrettyPrinter().
					writeValueAsString(new RetrieveListOfProvider().
							getAllProviders(access_token, id_token, page, size, sort));
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
	public com.i3m.api.ApiResponse<Void> registerDataProvider(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody SemanticEngineDataProvider dataProvider) throws ApiException {
		return new RegisterDataProvider().saveDataProviderInfo(access_token, id_token, dataProvider);
	}


	@DELETE
	@Path("/registration/data-provider/{providerId}")
	@ApiOperation(value = "delete an existing data provider", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete data provider")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> deleteDataProvider(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("providerId") String dataProviderId) throws ApiException {
		return new DeleteDataProvider().deleteProvider(access_token, id_token, dataProviderId);
	}
	
    @PATCH
    @Path("/update-offering")
    @ApiOperation(value = "update an offering", tags="common-services: offering")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "failed to update offering")})
	@Produces({ "application/json", "application/xml" })
    public com.i3m.api.ApiResponse updateDataOffering(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody SemanticEngineDataOfferingDto dataOffering) throws ApiException {
          return new UpdateOffering().updateOffering(access_token, id_token, dataOffering);
    }
    
    // Federated queries related methods
    
    @GET
	@Path("/federated-offering/{id}/offeringId")
	@ApiOperation(value = "retrieve a data offering by offering id using a federated query", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse retrieveFederatedDataOfferingById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @PathParam("id") String id,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		return new RetrieveFederatedOfferingById().getDataOfferingById(access_token, id_token, id, page, size, sort);
	}
    
    @GET
	@Path("/federated-offering/{category}")
	@ApiOperation(value = "retrieve a data offering by category using a federated query", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse retrieveFederatedDataOfferingByCategory(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @PathParam("category") String category,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		return new RetrieveFederatedOfferingByCategory().getOfferingByCategory(access_token, id_token, category, sort);
	}
    
    @GET
	@Path("/federated-offerings-list")
	@ApiOperation(value = "retrieve a data offering list using a federated query", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to retrieve this offering list")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse retrieveFederatedDataOfferingList(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token,
										   @QueryParam("page") @DefaultValue("0") Integer page,
										   @QueryParam("size") @DefaultValue("5") Integer size,
										   @QueryParam("sort") List<String> sort) throws ApiException {

		return new RetrieveFederatedOfferingList().getDataOfferingList(access_token, id_token, page, size, sort);
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
	public String accountDataBlock(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("bearerToken") String bearerToken, @QueryParam("data") String data, @QueryParam("block_id") String block_id, @QueryParam("block_ack") String block_ack)
	throws ApiException {
		InlineObject blockIdAck = new InlineObject();
		blockIdAck.setBlockId(block_id);
		blockIdAck.setBlockAck(block_ack);
		return new AccountDataBlock().accountDataBlock(access_token, id_token, bearerToken, blockIdAck, data);
	}

	@POST
	@Path("/get-file/{data}")
	@ApiOperation(value = "get file", tags = "common-services: exchange")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get file") })
	@Produces({ "text/plain" })
	public String accountDataFile(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("bearerToken") String bearerToken, @QueryParam("data") String data)
	throws ApiException {
		InlineObject blockIdAck = new InlineObject();
		blockIdAck.setBlockId("null");
		blockIdAck.setBlockAck("null");
		return new AccountDataBlock().getFile(access_token, id_token, bearerToken, blockIdAck, data);
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
	public Invoice createInvoice(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("bearerToken") String bearerToken, @QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate)
	throws ApiException {
		return new AccountDataBlock().createInvoice(access_token, id_token, bearerToken, fromDate, toDate);
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

	/////// Tokenizer API ///////
	@GET
	@Path("/token/operations")
	@ApiOperation(value = "Get list of operations", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid address") })
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<Object> getOperations(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("transferId") String transferId, @QueryParam("type") String type, @QueryParam("status") String status, @QueryParam("user") String user, @QueryParam("fromdate") Date fromdate, @QueryParam("todate") Date todate, @QueryParam("page") BigDecimal page, @QueryParam("page_size") BigDecimal page_size) throws ApiException {
		return new TokenizerController().getOperations(access_token, id_token, transferId, type, status, user, fromdate, todate, page, page_size);
	}

	@POST
	@Path("/token/operations/fee-payment")
	@ApiOperation(value = "Generate the fee payment transaction object", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to execute payment")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2004> payment(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody  TokenizationFeePay inlineObject) throws ApiException {
		return new TokenizerController().feePayment(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/operations/exchange-in")
	@ApiOperation(value = "Retrieve the transaction object to perform a exchange-in", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to exchange in tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2004> exchangeIn(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody  com.i3m.model.backplane.InlineObject inlineObject) throws ApiException {
		return new TokenizerController().exchangeIn(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/operations/exchange-out")
	@ApiOperation(value = "Retrieve the transaction object to perform a exchange-out", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to exchange out tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2004> exchangeOut(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody InlineObject1 inlineObject) throws ApiException {
		return new TokenizerController().exchangeOut(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/operations/clearing")
	@ApiOperation(value = "Retrieve the transaction object to start the Marketplace clearing operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to clearing tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2003> clearing(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new TokenizerController().clearingOperation(access_token, id_token);
	}

	@POST
	@Path("/token/treasury/transactions/deploy-transaction")
	@ApiOperation(value = "Deploy a signed transaction", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "deploy failed")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2009> deployTransaction(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody InlineObject4 inlineObject) throws ApiException {
		return new TokenizerController().deploySignedTransaction(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/operations/set-paid")
	@ApiOperation(value = "Generate the payment transaction object", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to set paid on transaction operation")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2003> setPaid(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody TokenizationPay inlineObject) throws ApiException {
		return new TokenizerController().setPaid(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/treasury/community-wallet")
	@ApiOperation(value = "Alter the community wallet address and the related community fee", tags="common-services: token")
	//@ApiResponses(value = {@ApiResponse(code = 500, message = "failed to add marketplace")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2006> modifyWallet(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody InlineObject2 inlineObject) throws ApiException {
		return new TokenizerController().modifyCommunityWallet(access_token, id_token, inlineObject);
	}

	@POST
	@Path("/token/treasury/marketplaces")
	@ApiOperation(value = "Register a marketplace", tags="common-services: token")
	//@ApiResponses(value = {@ApiResponse(code = 500, message = "failed to add marketplace")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<InlineResponse2006> addMarketplace(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody InlineObject3 inlineObject) throws ApiException {
		return new TokenizerController().registerMarketplace(access_token, id_token, inlineObject);
	}

	@GET
	@Path("/token/treasury/balances/{address}")
	@ApiOperation(value = "Get the balance for a specific account", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get marketplace balance with this address") })
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<InlineResponse2005> getBalanceByAddress(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("address") String address) throws ApiException {
		return new TokenizerController().getBalanceByAddress(access_token, id_token, address);
	}

	@GET
	@Path("/token/treasury/marketplaces/{address}")
	@ApiOperation(value = "Get marketplace index by marketplace address", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get marketplace index with this address") })
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<InlineResponse2007> getMarketplaceByAddress(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("address") String address) throws ApiException {
		return new TokenizerController().getMarketplaceIndex(access_token, id_token, address);
	}

	@GET
	@Path("/token/treasury/transactions/{transactionHash}")
	@ApiOperation(value = "Get the receipt of a transaction given a TransactionHash", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get transaction with this transactionHash") })
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<InlineResponse20010> getTransactionsByTransactionHash(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("transactionHash") String transactionHash) throws ApiException {
		return new TokenizerController().getReceiptByTransactionHash(access_token, id_token, transactionHash);
	}

	@GET
	@Path("/token/treasury/token-transfer/{transferId}")
	@ApiOperation(value = "Get the token transfer given a TransferId", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get transaction object with this transferId") })
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<InlineResponse2008> getTokenTransfersByTransferId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("transferId") String transferId) throws ApiException {
		return new TokenizerController().getTokenTransferByTransferId(access_token, id_token, transferId);
	}

	/////// Pricing Manager API ///////

	@GET
	@Path("/pricingManager/cost/getfee")
	@ApiOperation(value = "get i3M fee", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed") })
	@Produces({ "application/json", "application/xml" })
	public Object getFee(@HeaderParam("access_token") String access_token,
								@HeaderParam("id_token") String id_token,
								@QueryParam("price") String price) throws ApiException {
		return new PricingManager().getFee(access_token, id_token, price);
	}

	@PUT
	@Path("/pricingManager/cost/setfee")
	@ApiOperation(value = "set I3M fee", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to set the fee") })
	@Produces({ "application/json", "application/xml" })
	public Object setFee(@HeaderParam("access_token") String access_token,
							@HeaderParam("id_token") String id_token,
							@QueryParam("fee") String fee) throws ApiException {
		return new PricingManager().setFee(access_token, id_token, fee);
	}

	@GET
	@Path("/pricingManager/price/checkformulaconfiguration")
	@ApiOperation(value = "Check formula and parameters consistency", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to check the formula and parameters consistency") })
	@Produces({ "application/json", "application/xml" })
	public Object checkFormulaConfiguration(@HeaderParam("access_token") String access_token,
											   @HeaderParam("id_token") String id_token) throws ApiException {
		return new PricingManager().getCheckFormulaConfiguration(access_token, id_token);
	}

	@GET
	@Path("/pricingManager/price/getformulajsonconfiguration")
	@ApiOperation(value = "Get configuration using Json format", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get configuration using Json format") })
	@Produces({ "application/json", "application/xml" })
	public Object getFormulaJsonConfiguration(@HeaderParam("access_token") String access_token,
											  @HeaderParam("id_token") String id_token) throws ApiException {
		return new PricingManager().getFormulaJsonConfiguration(access_token, id_token);
	}

	@GET
	@Path("/pricingManager/price/getprice")
	@ApiOperation(value = "Get the price of data", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the price of item") })
	@Produces({ "application/json", "application/xml" })
	public Object getPrice(@HeaderParam("access_token") String access_token,
						   @HeaderParam("id_token") String id_token,
						   @QueryParam("parameters") String parameters) throws ApiException {

//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, String> paramsMap = null;
//		try {
//			paramsMap = mapper.readValue(parameters, Map.class);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		return new PricingManager().getPrice(access_token, id_token, parameters);
	}

	@PUT
	@Path("/pricingManager/price/setformulaconstant")
	@ApiOperation(value = "Set formula constant", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to Set formula constant") })
	@Produces({ "application/json", "application/xml" })
	public Object setFormulaConstant(@HeaderParam("access_token") String access_token,
									 @HeaderParam("id_token") String id_token,
									 @RequestBody PricingManagerFormulaConstantConfiguration parameters) throws ApiException {
		return new PricingManager().setFormulaConstant(access_token, id_token, parameters);
	}

	@PUT
	@Path("/pricingManager/price/setformulajsonconfiguration")
	@ApiOperation(value = "Set configuration using Json format", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to set the configuration") })
	@Produces({ "application/json", "application/xml" })
	public Object setFormulaJsonConfiguration(@HeaderParam("access_token") String access_token,
											  @HeaderParam("id_token") String id_token,
											  @RequestBody PricingManagerFormulaConfig parameters) throws ApiException {
		return new PricingManager().setFormulajsonConfiguration(access_token, id_token, parameters);
	}

	@PUT
	@Path("/pricingManager/price/setformulaparameter")
	@ApiOperation(value = "Set formula parameter", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to set the formula parameter") })
	@Produces({ "application/json", "application/xml" })
	public Object setFormulaParameter(@HeaderParam("access_token") String access_token,
									 @HeaderParam("id_token") String id_token,
									 @RequestBody PricingManagerFormulaParameterConfiguration parameters) throws ApiException {
		return new PricingManager().setFormulaParameter(access_token, id_token, parameters);
	}

	@PUT
	@Path("/pricingManager/price/setformulawithdefaultconfiguration")
	@ApiOperation(value = "Set formula with default values for constants and parameters", tags = "common-services: pricingManager")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to set the formula json configuration") })
	@Produces({ "application/json", "application/xml" })
	public Object setFormulaWithDefaultConfiguration(@HeaderParam("access_token") String access_token,
									  @HeaderParam("id_token") String id_token,
									  @RequestBody PricingManagerFormulaWithConfiguration parameters) throws ApiException {
		return new PricingManager().setFormulaWithDefaultConfiguration(access_token, id_token, parameters);
	}

	/////// Verifiable Credential API ///////

	@GET
	@Path("/credential/issue/{credential}/callbackUrl/{callbackUrl}")
	@ApiOperation(value = "generate a verifiable credential", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the page to generate issue a credential") })
	@Produces({ "text/html", "application/xml" })
	public Object getIssueVerifiableCredential(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("credential") String credential, @PathParam("callbackUrl") String callbackUrl) throws ApiException {
		return new VerifiableCredentials().getIssueVerifiableCredential(access_token, id_token, credential, callbackUrl);
	}

	@GET
	@Path("/credential/issue/{did}/{credential}")
	@ApiOperation(value = "generate a verifiable credential", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the page to generate issue a credential") })
	@Produces({ "application/json", "application/xml" })
	public Object getIssueVerifiableCredentialByDid(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("did") String did, @PathParam("credential") String credential) throws ApiException {
		return new VerifiableCredentials().getIssueVerifiableCredentialByDid(access_token, id_token, credential, did);
	}

	@POST
	@Path("/credential/revoke")
	@ApiOperation(value = "revoke a credential by jwt ", tags="common-services: credential")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to revoke the credential")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Object revokeVerifiableCredentialByJWT(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody com.i3m.model.backplane.InlineObject5 credential) throws ApiException {
		return new VerifiableCredentials().postRevokeCredentialByJWT(access_token, id_token, credential);
	}

	@POST
	@Path("/credential/verify")
	@ApiOperation(value = "verify a credential by jwt ", tags="common-services: credential")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to verify the credential")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Object verifyVerifiableCredentialByJWT(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody com.i3m.model.backplane.InlineObject6 credential) throws ApiException {
		return new VerifiableCredentials().postVerifyCredentialByJWT(access_token, id_token, credential);
	}

	/*
	@GET
	@Path("/credential")
	@ApiOperation(value = "get the issued credential list", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the credential list") })
	@Produces({ "application/json", "application/xml" })
	public List<String> getCredentialsList(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new VerifiableCredentials().getCredentialList(access_token, id_token);
	}
	*/

	@GET
	@Path("/issuer/subscribe")
	@ApiOperation(value = "subscribe the issuer", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to subscribe the issuer") })
	@Produces({ "application/json", "application/xml" })
	public Object getSubscribeIssuer(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new VerifiableCredentials().getSubscribeIssuer(access_token, id_token);
	}

	@GET
	@Path("/issuer/unsubscribe")
	@ApiOperation(value = "unsubscribe the issuer", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to unsubscribe the issuer") })
	@Produces({ "application/json", "application/xml" })
	public Object getUnsubscribeIssuer(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new VerifiableCredentials().getUnsubscribeIssuer(access_token, id_token);
	}

	@GET
	@Path("/issuer/verify")
	@ApiOperation(value = "verify the issuer subscription", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to verify the issuer subscription") })
	@Produces({ "application/json", "application/xml" })
	public Object getVerifyIssuerSubscription(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new VerifiableCredentials().getVerifyIssuerSubscription(access_token, id_token);
	}

	///////////////////////////// Notifications /////////////////////////////
	
	@POST
	@Path("/notification/service")
	@ApiOperation(value = "Creates a notification to send to other registered services", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to add notification")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Object> createServiceNotification(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody NotificationManagerOasServiceNotification body) throws ApiException {
		return new CreateNotification().createServiceNotification(access_token, id_token, body);
	}

	@POST
	@Path("/notification")
	@ApiOperation(value = "Creates a user notification and store it", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to add notification")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasNotification> createUserNotification(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody NotificationManagerOasUserNotification body) throws ApiException {
		return new CreateNotification().createUserNotification(access_token, id_token, body);
	}

	@DELETE
	@Path("/notification/{notification_id}")
	@ApiOperation(value = "Delete a notification by id", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasNotification> deleteNotification(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("notification_id") String notification_id) throws ApiException {
		return new DeleteNotification().deleteNotification(access_token, id_token, notification_id);
	}

	@GET
	@Path("/notification")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the stored notifications", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<NotificationManagerOasNotification>> retrieveAllNotifications(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new RetrieveNotifications().getAllNotifications(access_token, id_token);
	}

	@GET
	@Path("/notification/unread")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<NotificationManagerOasNotification>> getAllUnreadNotifications(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new RetrieveNotifications().getAllUnreadNotifications(access_token, id_token);
	}

	@GET
	@Path("/notification/user/{user_id}")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<NotificationManagerOasNotification>> retrieveNotificationsByUserId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id) throws ApiException {
		return new RetrieveNotifications().getUserNotifications(access_token, id_token, user_id);
	}

	@GET
	@Path("/notification/user/{user_id}/unread")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<NotificationManagerOasNotification>> retrieveUnreadNotificationsByUserId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id) throws ApiException {
		return new RetrieveNotifications().getUserUnreadNotifications(access_token, id_token, user_id);
	}

	@GET
	@Path("/notification/{notification_id}")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<NotificationManagerOasNotification> retrieveNotificationsByNotificationId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("notification_id") String notification_id) throws ApiException {
		return new RetrieveNotifications().getNotificationsByNotificationId(access_token, id_token, notification_id);
	}

	@PATCH
	@Path("/notification/{notification_id}/read")
	@ApiOperation(value = "Mark a notification as read", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasNotification> markAsReadNotification(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("notification_id") String notification_id) throws ApiException {
		return new ModifyNotification().markAsReadNotification(access_token, id_token, notification_id);
	}

	@PATCH
	@Path("/notification/{notification_id}/unread")
	@ApiOperation(value = "Mark a notification as unread", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasNotification> markAsUnreadNotification(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("notification_id") String notification_id) throws ApiException {
		return new ModifyNotification().markAsUnreadNotification(access_token, id_token, notification_id);
	}
	
	/////// Smart Contract Manager ///////
	
	@GET
	@Path("/contract/get-contract-template/{idOffering}")
	@ApiOperation(value = "retrieve the contract template", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the contract template") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasTemplate getContractTemplate(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("idOffering") String idOffering) throws ApiException {
		return new BackplaneClient().getTemplate(access_token, id_token, idOffering);
	}
	
	@POST
	@Path("/contract/create-data-purchase")
	@ApiOperation(value = "create a data purchase request", tags = "common-services: contract")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to generate the notification")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Object> createDataPurchase(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @QueryParam("origin_market_id") String origin_market_id, @QueryParam("consumer_did") String consumer_did, @RequestBody ScManagerOasTemplate contractualParameters) throws ApiException {
		return new RequestingDataItemPurchase().requestDataItemPurchase(access_token, id_token, origin_market_id, consumer_did, contractualParameters);
	}
	
	@GET
	@Path("/contract/get_agreement/{agreement_id}")
	@ApiOperation(value = "retrieve the agreement", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the agreement") })
	@Produces({"application/json"})
	public com.i3m.api.ApiResponse<ScManagerOasAgreementTemplate> getAgreement(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("agreement_id") Long agreement_id) throws ApiException {
		return new BackplaneClient().getAgreement(access_token, id_token, agreement_id);
	}
	
	@GET
	@Path("/contract/check_agreements_by_consumer/{consumer_id}")
	@ApiOperation(value = "retrieve the agreement by consumer", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the agreements") })
	@Produces({"application/json"})
	public com.i3m.api.ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByConsumer(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("consumer_id") String consumer_id, @PathParam("active") boolean active) throws ApiException {
		return new BackplaneClient().checkAgreementsByConsumer(access_token, id_token, consumer_id, active);
	}
	
	@GET
	@Path("/contract/check_agreements_by_provider/{provider_id}")
	@ApiOperation(value = "retrieve the agreement by consumer", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the agreements") })
	@Produces({"application/json"})
	public com.i3m.api.ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByProvider(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("provider_id") String provider_id, @PathParam("active") boolean active) throws ApiException {
		return new BackplaneClient().checkAgreementsByProvider(access_token, id_token, provider_id, active);
	}
	
	@GET
	@Path("/contract/check_agreements_by_data_offering/{provider_id}")
	@ApiOperation(value = "retrieve the agreement by consumer", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the agreements") })
	@Produces({"application/json"})
	public com.i3m.api.ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByDataOffering(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("offering_id") String offering_id) throws ApiException {
		return new BackplaneClient().checkAgreementsByDataOffering(access_token, id_token, offering_id);
	}
	
	
	@GET
	@Path("/contract/check_active_agreements")
	@ApiOperation(value = "retrieve the active agreements", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the agreements") })
	@Produces({"application/json"})
	public com.i3m.api.ApiResponse<ScManagerOasActiveAgreements> checkActiveAgreements(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new BackplaneClient().checkActiveAgreements(access_token, id_token);
	}
	
	@GET
	@Path("/contract/state/{agreement_id}")
	@ApiOperation(value = "retrieve the status", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the status") })
	@Produces({"application/json"})
	public  com.i3m.api.ApiResponse<ScManagerOasState> getState(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("agreement_id") String agreement_id) throws ApiException {
		return new BackplaneClient().getState(access_token, id_token, agreement_id);
	}

	@POST
	@Path("/contract/create_agreement_raw_transaction/{sender_address}")
	@ApiOperation(value = "create agreement", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to create agreement") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasRawTransactionTemplate createAgreement(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("sender_address") String senderAddress, @RequestBody ScManagerOasTemplate contractualParameters) throws ApiException {
		return new BackplaneClient().createAgreement(access_token, id_token, senderAddress, contractualParameters);
	}

	@POST
	@Path("/contract/deploy_signed_transaction")
	@ApiOperation(value = "deploy signed transaction", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to deploy signed transaction") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasTransactionObject deploySignedTransaction(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody ScManagerOasSignedTransaction signedTransaction) throws ApiException {
		return new BackplaneClient().deploySignedTransaction(access_token, id_token, signedTransaction);
	}

	@PUT
	@Path("/contract/sign_agreement_raw_transaction")
	@ApiOperation(value = "sign agreement", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to sign agreement") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasRawTransactionTemplate signAgreement(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody ScManagerOasSignAgreement signAgreement) throws ApiException {
		return new BackplaneClient().signAgreement(access_token, id_token, signAgreement);
	}

	@PUT
	@Path("/contract/update_agreement_raw_transaction/{agreement_id}/{sender_address}")
	@ApiOperation(value = "update agreement", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to update agreement") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasRawTransactionTemplate updateAgreement(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("agreement_id") Long agreementId, @PathParam("sender_address") String senderAddress, @RequestBody ScManagerOasTemplate contractualParameters) throws ApiException {
		return new BackplaneClient().updateAgreement(access_token, id_token, agreementId, senderAddress, contractualParameters);
	}

	@GET
	@Path("/contract/retrieve_agreements/{consumer_public_key}")
	@ApiOperation(value = "retrieve agreements", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to retrieve agreements") })
	@Produces({ "application/json", "application/xml" })
	public ScManagerOasActiveAgreements retrieveAgreements(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("consumer_public_key") String consumerPublicKey) throws ApiException {
		return new BackplaneClient().retrieveAgreements(access_token, id_token, consumerPublicKey);
	}

	/////// Alerts Subscriptions ///////
	
	@GET
	@Path("/alerts/users/subscriptions/")
	@ApiOperation(value = "Get all user's subscriptions", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<NotificationManagerOasUserSubscriptionList>> getSubscriptions(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new GetSubscriptions().getSubscriptions(access_token, id_token);
	}

	@GET
	@Path("/alerts/users/{user_id}/subscriptions/")
	@ApiOperation(value = "Get user's subscriptions by user ID", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<NotificationManagerOasSubscription>> getSubscriptionsByUserID(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id) throws ApiException {
		return new GetSubscriptions().getSubscriptionByUserID(access_token, id_token, user_id);
	}

	@GET
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}")
	@ApiOperation(value = "Get a user subscription by user ID and subscription ID", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasSubscription> getSubscriptionsByUserIDSubscriptionID(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new GetSubscriptions().getSubscriptionByUserIDSubscriptionId(access_token, id_token, user_id, subscription_id);
	}

	@POST
	@Path("/alerts/users/{user_id}/subscriptions")
	@ApiOperation(value = "Register a user to receive alerts for a category", tags="common-services: alerts")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Incomplete body"),
			@ApiResponse(code = 406, message = "Empty body"),
			@ApiResponse(code = 400, message = "Already exists subscription to category")
	})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasSubscription> createUserSubscription(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id, @RequestBody NotificationManagerOasCreateSubscription sub) throws ApiException {
		return new CreateUserSubscription().createUserSubscription(access_token, id_token, user_id, sub);
	}

	@PATCH
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}/activate")
	@ApiOperation(value = "Activate a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasSubscription> activateSubscription(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new ModifyUserSubscription().activateUserSubscription(access_token, id_token, user_id, subscription_id);
	}

	@PATCH
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}/deactivate")
	@ApiOperation(value = "Deactivate a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasSubscription> deactivateSubscription(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new ModifyUserSubscription().deactivateUserSubscription(access_token, id_token, user_id, subscription_id);
	}

	@DELETE
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}")
	@ApiOperation(value = "Delete a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasSubscription> deleteUserSubscription(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new DeleteUserSubscription().deleteUserSubscription(access_token, id_token, user_id, subscription_id);
	}
	/////// SERVICES & QUEUES FOR NOTIFICATIONS ///////
	@GET
	@Path("/services")
	@ApiOperation(value = "Get all registered services", tags="common-services: Services and Queues")
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<NotificationManagerOasService>> getServices(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token) throws ApiException {
		return new GetService().getServices(access_token, id_token);
	}

	@GET
	@Path("/services/{service_id}")
	@ApiOperation(value = "Get service by ServiceId", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasService> getServiceById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id) throws ApiException {
		return new GetService().getServiceById(access_token, id_token, service_id);
	}

	@GET
	@Path("/services/{service_id}/queues")
	@ApiOperation(value = "Get all service queues by ServiceId", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<NotificationManagerOasQueue>> getServiceQueuesByServiceId(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id, @PathParam("queue_id") String queue_id) throws ApiException {
		return new GetServiceQueue().getServiceQueuesByServiceId(access_token, id_token, service_id);
	}

	@GET
	@Path("/services/{service_id}/queues/{queue_id}")
	@ApiOperation(value = "Get service queue by ServiceId and QueueId", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasQueue> getServiceQueuesById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id, @PathParam("queue_id") String queue_id) throws ApiException {
		return new GetServiceQueue().getServiceQueueByQueueId(access_token, id_token, service_id, queue_id);
	}

	@POST
	@Path("/services")
	@ApiOperation(value = "Register new service", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Validation error")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasService> registerService(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @RequestBody NotificationManagerOasServiceInput data) throws ApiException {
		return new RegisterService().registerService(access_token, id_token, data);
	}

	@POST
	@Path("/services/{service_id}/queues")
	@ApiOperation(value = "Register new service queue", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Validation error")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasQueue> registerServiceQueue(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id, @RequestBody NotificationManagerOasQueueInput data) throws ApiException {
		return new RegisterServiceQueue().registerServiceQueue(access_token, id_token, service_id, data);
	}

	@DELETE
	@Path("/services/{service_id}")
	@ApiOperation(value = "Delete service by ServiceId and all it's queues", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasService> deleteServiceById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id) throws ApiException {
		return new DeleteService().deleteServiceById(access_token, id_token, service_id);
	}

	@DELETE
	@Path("/services/{service_id}/queues/{queue_id}")
	@ApiOperation(value = "Delete a Queue by queue_id", tags="common-services: Services and Queues")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<NotificationManagerOasQueue> deleteServiceQueueById(@HeaderParam("access_token") String access_token, @HeaderParam("id_token") String id_token, @PathParam("service_id") String service_id, @PathParam("queue_id") String queue_id) throws ApiException {
		return new DeleteServiceQueue().deleteServiceQueueByQueueId(access_token, id_token, service_id, queue_id);
	}
}