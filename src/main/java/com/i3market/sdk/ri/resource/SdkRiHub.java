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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.i3m.api.ApiException;
import com.i3m.model.backplane.*;
import com.i3m.model.data_access.InlineObject;
import com.i3m.model.data_access.Invoice;
import com.i3market.sdk.ri.common_services.data.discovery.*;
import com.i3market.sdk.ri.common_services.data.exchange.AccountDataBlock;
import com.i3market.sdk.ri.common_services.data.offering.CreateOffering;
import com.i3market.sdk.ri.common_services.data.offering.DeleteOfferingById;
import com.i3market.sdk.ri.common_services.data.offering.RegisterDataProvider;
import com.i3market.sdk.ri.common_services.data.offering.UpdateOffering;
import com.i3market.sdk.ri.common_services.notification.ModifyNotification;
import com.i3market.sdk.ri.common_services.tokenizer.Token;
import com.i3market.sdk.ri.common_services.verifiableCredentials.VerifiableCredentials;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.util.List;
import java.util.logging.Logger;

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
	@ApiResponses(value = {@ApiResponse(code = 400, message = "failed to add marketplace")})
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
	@Path("/credential/issue/{credential}")
	@ApiOperation(value = "generate a verifiable credential", tags = "common-services: credential")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "failed to get the page to generate issue a credential") })
	@Produces({ "text/html", "application/xml" })
	public Object getIssueVerifiableCredential(@PathParam("credential") String credential) throws ApiException {
		return new VerifiableCredentials().getIssueVerifiableCredential(credential);
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

	/////////////////////////////
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
}