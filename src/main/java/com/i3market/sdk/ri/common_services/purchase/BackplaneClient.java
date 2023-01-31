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

package com.i3market.sdk.ri.common_services.purchase;

import java.util.List;
import java.util.Map;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.api.backplane.AgreementApi;
import com.i3m.api.backplane.ConflictResolverServiceApi;
import com.i3m.api.backplane.ExplicitUserConsentApi;
import com.i3m.api.backplane.RatingServiceApi;
import com.i3m.model.backplane.ConflictResolverServiceDisputeInput;
import com.i3m.model.backplane.ConflictResolverServiceSignedResolution;
import com.i3m.model.backplane.ConflictResolverServiceVerificationInput;
import com.i3m.model.backplane.RatingAgreementsResponse;
import com.i3m.model.backplane.RatingEditRatingBody;
import com.i3m.model.backplane.RatingQuestionnaire;
import com.i3m.model.backplane.RatingRating;
import com.i3m.model.backplane.RatingRatingBody;
import com.i3m.model.backplane.RatingRatingResponse;
import com.i3m.model.backplane.RatingRatingsResponse;
import com.i3m.model.backplane.RatingRespondBody;
import com.i3m.model.backplane.RatingTotalRatingResponse;
import com.i3m.model.backplane.ScManagerOasActiveAgreements;
import com.i3m.model.backplane.ScManagerOasAgreementTemplate;
import com.i3m.model.backplane.ScManagerOasChoosePenalty;
import com.i3m.model.backplane.ScManagerOasConsent;
import com.i3m.model.backplane.ScManagerOasConsentStatus;
import com.i3m.model.backplane.ScManagerOasEnforcePenalty;
import com.i3m.model.backplane.ScManagerOasPricingModelTemplate;
import com.i3m.model.backplane.ScManagerOasPublicKeysArray;
import com.i3m.model.backplane.ScManagerOasRawTransactionTemplate;
import com.i3m.model.backplane.ScManagerOasRevokeConsent;
import com.i3m.model.backplane.ScManagerOasSignedResolutionScm;
import com.i3m.model.backplane.ScManagerOasSignedTransaction;
import com.i3m.model.backplane.ScManagerOasState;
import com.i3m.model.backplane.ScManagerOasTemplate;
import com.i3m.model.backplane.ScManagerOasTerminate;
import com.i3m.model.backplane.ScManagerOasTransactionObject;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

public class BackplaneClient {
	
	public ApiResponse<ScManagerOasTemplate> getTemplate (String access_token, String id_token, String idTemplate) throws ApiException {
		handleAuthentication(access_token, id_token);
		
		AgreementApi controller = new AgreementApi();
		return controller.getTemplateByOfferingIdWithHttpInfo(idTemplate);
	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> createAgreement (String access_token, String id_token, String senderAddress, ScManagerOasTemplate template) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.postCreateAgreementRawTransactionBySenderAddressWithHttpInfo(template, senderAddress);
	}

	public ApiResponse<ScManagerOasTransactionObject> deploySignedTransaction (String access_token, String id_token, ScManagerOasSignedTransaction signedTransaction) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.postDeploySignedTransactionWithHttpInfo(signedTransaction);
	}

//	public ApiResponse<ScManagerOasRawTransactionTemplate> signAgreement (String access_token, String id_token, ScManagerOasSignAgreement signAgreement) throws ApiException {
//		handleAuthentication(access_token, id_token);
//
//		AgreementApi controller = new AgreementApi();
//		return controller.putSignAgreementRawTransactionWithHttpInfo(signAgreement);
//	}

//	public ScManagerOasRawTransactionTemplate updateAgreement (String access_token, String id_token, Long agreementId, String senderAddress, ScManagerOasTemplate template) throws ApiException {
//		handleAuthentication(access_token, id_token);
//
//		AgreementApi controller = new AgreementApi();
//		return controller.putUpdateAgreementRawTransactionByAgreementIdBySenderAddress(template, agreementId, senderAddress);
//	}

	public ApiResponse<ScManagerOasActiveAgreements> retrieveAgreements (String access_token, String id_token, String consumerPublicKey) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getRetrieveAgreementsByConsumerPublicKeyWithHttpInfo(consumerPublicKey);
	}

	public ApiResponse<ScManagerOasAgreementTemplate> getAgreement (String access_token, String id_token, Long agreement_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getGetAgreementByAgreementIdWithHttpInfo(agreement_id);
	}

	public ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByConsumer(String access_token,
			String id_token, ScManagerOasPublicKeysArray consumer_public_keys, boolean active) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getCheckAgreementsByConsumerByConsumerPublicKeysByActiveWithHttpInfo(consumer_public_keys, active);
	}
	
	public ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByDataOffering(String access_token, String id_token,
			String data_offering_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getCheckAgreementsByDataOfferingByOfferingIdWithHttpInfo(data_offering_id);
	}

	public ApiResponse<ScManagerOasActiveAgreements> checkAgreementsByProvider(String access_token,
			String id_token, ScManagerOasPublicKeysArray provider_public_keys, boolean active) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getCheckAgreementsByProviderByProviderPublicKeysByActiveWithHttpInfo(provider_public_keys, active);
	}

	public ApiResponse<ScManagerOasActiveAgreements> checkActiveAgreements(String access_token,
			String id_token) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getCheckActiveAgreementsWithHttpInfo();
	}

	public ApiResponse<ScManagerOasState> getState(String access_token,
			String id_token, String agreement_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getGetStateByAgreementIdWithHttpInfo(Long.valueOf(agreement_id));
	}

	private void handleAuthentication (String access_token, String id_token) {
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;

		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();

		// Set basePath to http request
		defaultClient.setBasePath(basePath);

		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);

		//Add token as headers
		defaultClient.addDefaultHeader("access_token", access_token);
		defaultClient.addDefaultHeader("id_token", id_token);

		// Setup authentications (JWT).
		// String jwt = "Bearer " + access_token;

		Map<String, Authentication> authentications = defaultClient.getAuthentications();
		HttpBearerAuth jwt = new HttpBearerAuth(null);
		jwt.setBearerToken(id_token);

		HttpBearerAuth jwtAccess = new HttpBearerAuth(null);
		jwtAccess.setBearerToken(access_token);

		// System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("jwt", jwt);
		authentications.put("jwtAccess", jwtAccess);
	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> terminateAgreement(String access_token, String id_token,
			ScManagerOasTerminate terminateAgreement) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.putTerminateWithHttpInfo(terminateAgreement);
	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> evaluateSignedResolution(String access_token, String id_token,
			ScManagerOasSignedResolutionScm signedResolution) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.postEvaluateSignedResolutionWithHttpInfo(signedResolution);
	}

	public ApiResponse<ScManagerOasPricingModelTemplate> getPricingModelByAgreementId(String access_token, String id_token,
			Long agreement_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.getGetPricingModelByAgreementIdWithHttpInfo(agreement_id);
	}

	public ApiResponse<ConflictResolverServiceSignedResolution> dispute(String access_token, String id_token,
			ConflictResolverServiceDisputeInput disputeInput) throws ApiException {
		handleAuthentication(access_token, id_token);

		ConflictResolverServiceApi controller = new ConflictResolverServiceApi();
		return controller.postDisputeWithHttpInfo(disputeInput);
	}

	public ApiResponse<ConflictResolverServiceSignedResolution> verification(String access_token, String id_token,
			ConflictResolverServiceVerificationInput verificationInput) throws ApiException {
		handleAuthentication(access_token, id_token);

		ConflictResolverServiceApi controller = new ConflictResolverServiceApi();
		return controller.postVerificationWithHttpInfo(verificationInput);
	}

//	public ApiResponse<ScManagerOasRawTransactionTemplate> requestTermination(String access_token, String id_token,
//			ScManagerOasRequestTermination requestTermination) throws ApiException {
//		handleAuthentication(access_token, id_token);
//
//		AgreementApi controller = new AgreementApi();
//		return controller.putRequestTerminationWithHttpInfo(requestTermination);
//	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> enforcePenalty(String access_token, String id_token,
			ScManagerOasEnforcePenalty enforcePenalty) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.putEnforcePenaltyWithHttpInfo(enforcePenalty);
	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> revokeConsent(String access_token, String id_token,
			ScManagerOasRevokeConsent revokeConsent) throws ApiException {
		handleAuthentication(access_token, id_token);

		ExplicitUserConsentApi controller = new ExplicitUserConsentApi();
		return controller.putRevokeConsentWithHttpInfo(revokeConsent);
	}

	public ApiResponse<ScManagerOasConsentStatus> checkConsentStatus(String access_token, String id_token,
			String dataOfferingId, String consentSubject) throws ApiException {
		handleAuthentication(access_token, id_token);

		ExplicitUserConsentApi controller = new ExplicitUserConsentApi();
		return controller.getCheckConsentStatusByDataOfferingIdWithHttpInfo(dataOfferingId, consentSubject);
	}

	public ApiResponse<ScManagerOasChoosePenalty> proposePenalty(String access_token, String id_token,
			ScManagerOasChoosePenalty proposePenalty) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.postProposePenaltyWithHttpInfo(proposePenalty);
	}

	public ApiResponse<ScManagerOasTransactionObject> deployConsentSignedTransaction(String access_token,
			String id_token, ScManagerOasSignedTransaction deployConsentSignedTransaction) throws ApiException {
		handleAuthentication(access_token, id_token);

		AgreementApi controller = new AgreementApi();
		return controller.postDeploySignedTransactionWithHttpInfo(deployConsentSignedTransaction);
	}

	public ApiResponse<ScManagerOasRawTransactionTemplate> giveConsent(String access_token, String id_token,
			ScManagerOasConsent giveConsent) throws ApiException {
		handleAuthentication(access_token, id_token);

		ExplicitUserConsentApi controller = new ExplicitUserConsentApi();
		return controller.postGiveConsentWithHttpInfo(giveConsent);
	}

	public ApiResponse<Boolean> getApiAgreementsByIdIsRated(String access_token,
			String id_token, String agreement_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiAgreementsByIdIsRatedWithHttpInfo(agreement_id);
	}

	public ApiResponse<RatingRatingResponse> getApiAgreementsByIdRating(String access_token, String id_token, String agreement_id) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiAgreementsByIdRatingWithHttpInfo(agreement_id);
	}

	public  ApiResponse<RatingAgreementsResponse> getApiConsumersByPkAgreements(String access_token, String id_token,
			String publicKey) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiConsumersByPkAgreementsWithHttpInfo(publicKey);
	}

	public  ApiResponse<RatingRatingsResponse> getApiConsumersByDidRatings(String access_token, String id_token,
			String did) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiConsumersByDidRatingsWithHttpInfo(did);
	}

	public ApiResponse<RatingAgreementsResponse> getApiProvidersByPkAgreements(String access_token, String id_token,
			String pk) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiProvidersByPkAgreementsWithHttpInfo(pk);
	}

	public  ApiResponse<RatingRatingsResponse> getApiProvidersByDidRatings(String access_token, String id_token,
			String did) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiProvidersByDidRatingsWithHttpInfo(did);
	}

	public  ApiResponse<RatingTotalRatingResponse> getApiProvidersByDidTotalRating(String access_token, String id_token,
			String did) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiProvidersByDidTotalRatingWithHttpInfo(did);
	}

	public ApiResponse<RatingQuestionnaire> getApiQuestions(String access_token, String id_token) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiQuestionsWithHttpInfo();
	}

	public  ApiResponse<RatingRatingsResponse> getApiRatings(String access_token, String id_token) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiRatingsWithHttpInfo();
	}

	public  ApiResponse<RatingRatingResponse> getApiRatingsById(String access_token, String id_token, String id) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.getApiRatingsByIdWithHttpInfo(id);
	}

	public  ApiResponse<RatingRatingResponse> postApiRatingsByIdRespond(String access_token, String id_token,
			String id, RatingRespondBody ratingRespond) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.postApiRatingsByIdRespondWithHttpInfo(ratingRespond, id);
	}

	public  ApiResponse<RatingRatingResponse> putApiRatingsById(String access_token, String id_token, String id,
			RatingEditRatingBody newRating) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.putApiRatingsByIdWithHttpInfo(newRating, id);
	}

	public ApiResponse<RatingRatingResponse> deleteApiRatingsById(String access_token, String id_token, String id) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.deleteApiRatingsByIdWithHttpInfo(id);
	}

	public ApiResponse<RatingRatingResponse> postApiRatings(String access_token, String id_token, String id,
			RatingRatingBody rating) throws ApiException {
		handleAuthentication(access_token, id_token);

		RatingServiceApi controller = new RatingServiceApi();
		return controller.postApiRatingsWithHttpInfo(rating);
	}

}
