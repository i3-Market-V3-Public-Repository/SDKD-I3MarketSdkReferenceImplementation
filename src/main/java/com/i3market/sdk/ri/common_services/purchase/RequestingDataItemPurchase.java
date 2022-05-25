package com.i3market.sdk.ri.common_services.purchase;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.HeaderParam;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.auth.Authentication;
import com.i3m.api.auth.HttpBearerAuth;
import com.i3m.model.backplane.DataOfferingDto;
import com.i3m.model.backplane.Notification;
import com.i3m.model.backplane.ServiceNotification;
import com.i3m.model.backplane.ServiceNotification.ReceiverIdEnum;
import com.i3m.model.backplane.Template;
import com.i3m.model.backplane.TemplateDataOfferingDescription;
import com.i3m.api.backplane.NotificationsApi;
import com.i3m.api.backplane.RegistrationOfferingApi;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;


public class RequestingDataItemPurchase {
	
	
	private static Logger LOGGER = Logger.getLogger(RequestingDataItemPurchase.class.getName());
		
	
	public ApiResponse<Object> requestDataItemPurchase (String access_token, String id_token, String bearerToken, String originMarketId, Template template) throws ApiException{
		// Include here the logic under the service
		String basePath = SdkRiConstants.BACKPLANE_ENDPOINT;
		
		// Get default client from Configuration
		ApiClient defaultClient = Configuration.getDefaultApiClient();
	    
		// Set basePath to http request
		defaultClient.setBasePath(basePath);
		
		// Avoiding default server conf based on localhost url
		defaultClient.setServerIndex(null);
		
        //Add token as headers
		defaultClient.addDefaultHeader("access_token", access_token);
		defaultClient.addDefaultHeader("id_token", access_token);

	    // Setup authentications (JWT).
		//String jwt = "Bearer " + bearerToken;
		
		
	    // Setup authentications (JWT).	
		Map<String, Authentication> authentications = defaultClient.getAuthentications();
		HttpBearerAuth jwt = new HttpBearerAuth(null);
		jwt.setBearerToken(id_token);
		
		HttpBearerAuth jwtAccess = new HttpBearerAuth(null);
		jwtAccess.setBearerToken(access_token);
		
		// System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
		authentications.put("jwt", jwt);
		authentications.put("jwtAccess ", jwtAccess);
		
//		Map<String, Authentication> authentications = defaultClient.getAuthentications();
//		HttpBearerAuth bearerAuth = new HttpBearerAuth(null);
//		bearerAuth.setBearerToken(jwt);
//		System.out.println("The bearer token is: " + bearerAuth.getBearerToken());
//		System.out.println("The BACKPLANE_ENDPOINT basePath: " + basePath);
//		authentications.put("bearerAuth", bearerAuth);
	    
		
		// Get the market_id
		List<String> sort = null;
		RegistrationOfferingApi registrationOfferingApi = new RegistrationOfferingApi();
	    DataOfferingDto offering = registrationOfferingApi.getRegisteredOfferingUsingGET(template.getDataOfferingDescription().getDataOfferingId(), 0, 5, sort);
		
	    // Fill the elements of the message
		HashMap<String, Object> message = new HashMap<String, Object>();
		message.put ("marketId", offering.getMarketId());
		message.put ("contractualParameters", template);
		message.put ("originMarketId", originMarketId);
		
        // Fill the elements of notification
		ServiceNotification sNot = new ServiceNotification();
        sNot.setReceiverId(ReceiverIdEnum.AGREEMENT_PENDING);
        sNot.setMessage(message);
		
        // Send the notification
        NotificationsApi notificationsApi = new NotificationsApi();
        ApiResponse<Object> res = notificationsApi.postApiV1NotificationServiceWithHttpInfo(sNot);
		
		System.out.println("notification: " + sNot);
		System.out.println("end: " + res.getData().toString());
		return res;

	}


}

