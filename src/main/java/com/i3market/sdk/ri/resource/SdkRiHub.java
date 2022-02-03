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
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i3m.api.ApiException;
import com.i3m.model.backplane.Balances;
import com.i3m.model.backplane.ClearBalance;
import com.i3m.model.backplane.ClearingBalance;
import com.i3m.model.backplane.CreateSubscription;
import com.i3m.model.backplane.DataOffering;
import com.i3m.model.backplane.DataOfferingDto;
import com.i3m.model.backplane.DataOfferingId;
import com.i3m.model.backplane.DataProvider;
import com.i3m.model.backplane.DataProviderPayment;
import com.i3m.model.backplane.DeployTransactionToBesu;
import com.i3m.model.backplane.DeployedSignedTransaction;
import com.i3m.model.backplane.ExchangeIn;
import com.i3m.model.backplane.ExchangeMoneyForTokens;
import com.i3m.model.backplane.ExchangeOut;
import com.i3m.model.backplane.ExchangeTokensForMoney;
import com.i3m.model.backplane.InlineResponse2003;
import com.i3m.model.backplane.InlineResponse2004;
import com.i3m.model.backplane.MarkTokenAsPaid;
import com.i3m.model.backplane.MarketplaceIndex;
import com.i3m.model.backplane.Notification;
import com.i3m.model.backplane.Payment;
import com.i3m.model.backplane.RegisterMarketplace;
import com.i3m.model.backplane.RegisterMarketplace1;
import com.i3m.model.backplane.ServiceNotification;
import com.i3m.model.backplane.SetPaid;
import com.i3m.model.backplane.Subscription;
import com.i3m.model.backplane.UserNotification;
import com.i3m.model.backplane.UserSubscriptionList;
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
import com.i3market.sdk.ri.common_services.notification.CreateNotification;
import com.i3market.sdk.ri.common_services.notification.DeleteNotification;
import com.i3market.sdk.ri.common_services.notification.ModifyNotification;
import com.i3market.sdk.ri.common_services.notification.RetrieveNotifications;
import com.i3market.sdk.ri.common_services.purchase.BackplaneClient;
import com.i3market.sdk.ri.common_services.tokenizer.Token;
import com.i3market.sdk.ri.common_services.verifiableCredentials.VerifiableCredentials;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

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
			strJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new RetrieveTotalOfferingAndOfferingList().getTotalOfferingAndOfferingList(providerId, category, page, size, sortBy, orderBy));
					
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



	@POST
	@Path("/registration/data-offering")
	@ApiOperation(value = "register a data offering", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to save offering")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<DataOfferingId>> registerDataOffering(@RequestBody DataOfferingDto dataOffering) throws ApiException {
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


	@DELETE
	@Path("/registration/data-provider/{providerId}")
	@ApiOperation(value = "delete an existing data provider", tags="common-services: offering")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to delete data provider")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Void> deleteDataProvider(@PathParam("providerId") String dataProviderId) throws ApiException {
		return new DeleteDataProvider().deleteProvider(dataProviderId);
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

	/////// Tokenizer API ///////

	@POST
	@Path("/token/payment")
	@ApiOperation(value = "token payment operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to execute payment")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Payment payment(@RequestBody DataProviderPayment inlineObject5) throws ApiException {
		return new Token().payment(inlineObject5);
	}

	@POST
	@Path("/token/exchangein")
	@ApiOperation(value = "exchange in operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to exchange in tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public ExchangeIn exchangeIn(@RequestBody ExchangeMoneyForTokens inlineObject3) throws ApiException {
		return new Token().exchangeIn(inlineObject3);
	}

	@POST
	@Path("/token/exchangeout")
	@ApiOperation(value = "exchange out operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to exchange out tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public ExchangeOut exchangeOut(@RequestBody ExchangeTokensForMoney inlineObject4) throws ApiException {
		return new Token().exchangeOut(inlineObject4);
	}

	@POST
	@Path("/token/clearing")
	@ApiOperation(value = "clearing operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to clearing tokens")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public ClearingBalance clearing(@RequestBody ClearBalance inlineObject1) throws ApiException {
		return new Token().clearing(inlineObject1);
	}

	@POST
	@Path("/token/deploytransaction")
	@ApiOperation(value = "deploy operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "deploy failed")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public DeployedSignedTransaction deployTransaction(@RequestBody DeployTransactionToBesu inlineObject2) throws ApiException {
		return new Token().deployTransaction(inlineObject2);
	}

	@POST
	@Path("/token/setpaid")
	@ApiOperation(value = "set paid status of operation", tags="common-services: token")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to set paid on transaction operation")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public SetPaid setPaid(@RequestBody MarkTokenAsPaid inlineObject6) throws ApiException {
		return new Token().setPaid(inlineObject6);
	}

	@POST
	@Path("/token/marketplace")
	@ApiOperation(value = "add a new marketplace to the treasury smart contract and create a new token ", tags="common-services: token")
	//@ApiResponses(value = {@ApiResponse(code = 500, message = "failed to add marketplace")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public RegisterMarketplace1 addMarketplace(@RequestBody RegisterMarketplace inlineObject) throws ApiException {
		return new Token().createMarketplace(inlineObject);
	}

	@GET
	@Path("/token/balances/{address}")
	@ApiOperation(value = "get marketplace balance by marketplace address", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get marketplace balance with this address") })
	@Produces({ "application/json", "application/xml" })
	public Balances getBalanceByAddress(@QueryParam("address") String address) throws ApiException {
		return new Token().getBalanceByAddress(address);
	}

	@GET
	@Path("/token/marketplaces/{address}")
	@ApiOperation(value = "get marketplace index by marketplace address", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get marketplace index with this address") })
	@Produces({ "application/json", "application/xml" })
	public MarketplaceIndex getMarketplaceByAddress(@QueryParam("address") String address) throws ApiException {
		return new Token().getMarketplaceByAddress(address);
	}

	@GET
	@Path("/token/transactions/{transactionHash}")
	@ApiOperation(value = "get transaction with transaction hash", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get transaction with this transactionHash") })
	@Produces({ "application/json", "application/xml" })
	public InlineResponse2004 getTransactionsByTransactionHash(@QueryParam("transactionHash") String transactionHash) throws ApiException {
		return new Token().getTransactionsByTransactionHash(transactionHash);
	}

	@GET
	@Path("/token/token-transfer/{transferId}")
	@ApiOperation(value = "get transaction status object with transfer identifier", tags = "common-services: token")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get transaction object with this transferId") })
	@Produces({ "application/json", "application/xml" })
	public InlineResponse2003 getTokenTransfersByTransferId(@QueryParam("transferId") String transferId) throws ApiException {
		return new Token().getTokenTransfersByTransferId(transferId);
	}

	/////// Verifiable Credential API ///////

	@GET
	@Path("/credential/issue/{did}/{credential}")
	@ApiOperation(value = "generate a verifiable credential", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the page to generate issue a credential") })
	@Produces({ "text/html", "application/xml" })
	public Object getIssueVerifiableCredential(@PathParam("did") String did, @PathParam("credential") String credential) throws ApiException {
		return new VerifiableCredentials().getIssueVerifiableCredential(did, credential);
	}

	@POST
	@Path("/credential/revoke")
	@ApiOperation(value = "revoke a credential by jwt ", tags="common-services: credential")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to revoke the credential")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Object revokeVerifiableCredentialByJWT(@RequestBody com.i3m.model.backplane.InlineObject credential) throws ApiException {
		return new VerifiableCredentials().postRevokeCredentialByJWT(credential);
	}

	@POST
	@Path("/credential/verify")
	@ApiOperation(value = "verify a credential by jwt ", tags="common-services: credential")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to verify the credential")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public Object verifyVerifiableCredentialByJWT(@RequestBody com.i3m.model.backplane.InlineObject1 credential) throws ApiException {
		return new VerifiableCredentials().postVerifyCredentialByJWT(credential);
	}

	@GET
	@Path("/credential")
	@ApiOperation(value = "get the issued credential list", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the credential list") })
	@Produces({ "application/json", "application/xml" })
	public List<String> getCredentialsList() throws ApiException {
		return new VerifiableCredentials().getCredentialList();
	}

	@GET
	@Path("/issuer/subscribe")
	@ApiOperation(value = "subscribe the issuer", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to subscribe the issuer") })
	@Produces({ "application/json", "application/xml" })
	public Object getSubscribeIssuer() throws ApiException {
		return new VerifiableCredentials().getSubscribeIssuer();
	}

	@GET
	@Path("/issuer/unsubscribe")
	@ApiOperation(value = "unsubscribe the issuer", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to unsubscribe the issuer") })
	@Produces({ "application/json", "application/xml" })
	public Object getUnsubscribeIssuer() throws ApiException {
		return new VerifiableCredentials().getUnsubscribeIssuer();
	}

	@GET
	@Path("/issuer/verify")
	@ApiOperation(value = "verify the issuer subscription", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to verify the issuer subscription") })
	@Produces({ "application/json", "application/xml" })
	public Object getVerifyIssuerSubscription() throws ApiException {
		return new VerifiableCredentials().getVerifyIssuerSubscription();
	}

	///////////////////////////// Notifications /////////////////////////////
	
	@POST
	@Path("/notification/service")
	@ApiOperation(value = "Creates a notification to send to other registered services", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to add notification")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Object> createServiceNotification(@RequestBody ServiceNotification body) throws ApiException {
		return new CreateNotification().createServiceNotification(body);
	}

	@POST
	@Path("/notification")
	@ApiOperation(value = "Creates a user notification and store it", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to add notification")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Notification> createUserNotification(@RequestBody UserNotification body) throws ApiException {
		return new CreateNotification().createUserNotification(body);
	}

	@DELETE
	@Path("/notification/{notification_id}")
	@ApiOperation(value = "Delete a notification by id", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Notification> deleteNotification(@PathParam("notification_id") String notification_id) throws ApiException {
		return new DeleteNotification().deleteNotification(notification_id);
	}

	@GET
	@Path("/notification")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the stored notifications", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<Notification>> retrieveAllNotifications() throws ApiException {

		return new RetrieveNotifications().getAllNotifications();

	}

	@GET
	@Path("/notification/unread")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<Notification>> getAllUnreadNotifications() throws ApiException {

		return new RetrieveNotifications().getAllUnreadNotifications();

	}

	@GET
	@Path("/notification/user/{user_id}")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<Notification>> retrieveNotificationsByUserId(@PathParam("user_id") String user_id) throws ApiException {

		return new RetrieveNotifications().getUserNotifications(user_id);

	}

	@GET
	@Path("/notification/user/{user_id}/unread")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<List<Notification>> retrieveUnreadNotificationsByUserId(@PathParam("user_id") String user_id) throws ApiException {

		return new RetrieveNotifications().getUserUnreadNotifications(user_id);

	}

	@GET
	@Path("/notification/{notification_id}")
	//@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	@ApiOperation(value = "retrieve all the unread stored notifications for a user", tags="common-services: notification")
	//@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to search offering with this category ")})
	@Produces({ "application/json", "application/xml" })
	public com.i3m.api.ApiResponse<Notification> retrieveNotificationsByNotificationId(@PathParam("notification_id") String notification_id) throws ApiException {

		return new RetrieveNotifications().getNotificationsByNotificationId(notification_id);

	}

	@PATCH
	@Path("/notification/{notification_id}/read")
	@ApiOperation(value = "Mark a notification as read", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Notification> markAsReadNotification(@PathParam("notification_id") String notification_id) throws ApiException {
		return new ModifyNotification().markAsReadNotification(notification_id);
	}

	@PATCH
	@Path("/notification/{notification_id}/unread")
	@ApiOperation(value = "Mark a notification as unread", tags="common-services: notification")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to modify notification")})
	@Produces({ "application/json", "application/xml" })
	//@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Notification> markAsUnreadNotification(@PathParam("notification_id") String notification_id) throws ApiException {
		return new ModifyNotification().markAsUnreadNotification(notification_id);
	}
	
	/////// Smart Contract Manager ///////
	
	@GET
	@Path("/contract/get-contract-template/{idOffering}")
	@ApiOperation(value = "retrieve the contract template", tags = "common-services: contract")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the contract template") })
	@Produces({ "text/html", "application/xml" })
	public Object getContractTemplate(@HeaderParam("Authorization") String token, @PathParam("idOffering") String idOffering) throws ApiException {
		return new BackplaneClient().getTemplate(token, idOffering);
	}

	/////// Alerts Subscriptions ///////
	
	@GET
	@Path("/alerts/users/subscriptions/")
	@ApiOperation(value = "Get all user's subscriptions", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<UserSubscriptionList>> getSubscriptions() throws ApiException {
		return new GetSubscriptions().getSubscriptions();
	}

	@GET
	@Path("/alerts/users/{user_id}/subscriptions/")
	@ApiOperation(value = "Get user's subscriptions by user ID", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<List<Subscription>> getSubscriptionsByUserID(@PathParam("user_id") String user_id) throws ApiException {
		return new GetSubscriptions().getSubscriptionByUserID(user_id);
	}

	@GET
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}")
	@ApiOperation(value = "Get a user subscription by user ID and subscription ID", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Subscription> getSubscriptionsByUserIDSubscriptionID(@PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new GetSubscriptions().getSubscriptionByUserIDSubscriptionId(user_id, subscription_id);
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
	public com.i3m.api.ApiResponse<Subscription> createUserSubscription(@PathParam("user_id") String user_id, @RequestBody CreateSubscription sub) throws ApiException {
		return new CreateUserSubscription().createUserSubscription(user_id, sub);
	}

	@PATCH
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}/activate")
	@ApiOperation(value = "Activate a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Subscription> activateSubscription(@PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new ModifyUserSubscription().activateUserSubscription(user_id, subscription_id);
	}

	@PATCH
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}/deactivate")
	@ApiOperation(value = "Deactivate a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Not found")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Subscription> deactivateSubscription(@PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new ModifyUserSubscription().deactivateUserSubscription(user_id, subscription_id);
	}

	@DELETE
	@Path("/alerts/users/{user_id}/subscriptions/{subscription_id}")
	@ApiOperation(value = "Delete a subscription", tags="common-services: alerts")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Incomplete request")})
	@Produces({ "application/json", "application/xml" })
	@Consumes(MediaType.APPLICATION_JSON)
	public com.i3m.api.ApiResponse<Subscription> deleteUserSubscription(@PathParam("user_id") String user_id, @PathParam("subscription_id") String subscription_id) throws ApiException {
		return new DeleteUserSubscription().deleteUserSubscription(user_id, subscription_id);
	}

}