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
package com.i3market.sdk.ri.common_services.notification;

import com.i3m.api.ApiClient;
import com.i3m.api.ApiException;
import com.i3m.api.ApiResponse;
import com.i3m.api.Configuration;
import com.i3m.api.backplane.NotificationsApi;
import com.i3m.model.backplane.NotificationManagerOasNotification;
import com.i3market.sdk.ri.execution_patterns.SdkRiConstants;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

public class RetrieveNotifications {

    public ApiResponse<List<NotificationManagerOasNotification>> getAllNotifications(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationWithHttpInfo();

    }

    public ApiResponse<List<NotificationManagerOasNotification>> getAllUnreadNotifications(String access_token, String id_token) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUnreadWithHttpInfo();

    }

    public ApiResponse<List<NotificationManagerOasNotification>> getUserNotifications(String access_token, String id_token, String user_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUserByUserIdWithHttpInfo(user_id);

    }

    public ApiResponse<List<NotificationManagerOasNotification>> getUserUnreadNotifications(String access_token, String id_token, String user_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationUserByUserIdUnreadWithHttpInfo(user_id);

    }

    public ApiResponse<NotificationManagerOasNotification> getNotificationsByNotificationId(String access_token, String id_token, String notification_id) throws ApiException {

        String backPlanePath = SdkRiConstants.BACKPLANE_ENDPOINT;
        
        ApiClient apiClient = Configuration.getDefaultApiClient();

        apiClient.setBasePath(backPlanePath);

        apiClient.setServerIndex(null);
        
        //Add token as headers
        apiClient.addDefaultHeader("access_token", access_token);
        apiClient.addDefaultHeader("id_token", access_token);

        NotificationsApi notificationsApi = new NotificationsApi();

        return  notificationsApi.getApiV1NotificationByNotificationIdWithHttpInfo(notification_id);

    }

}

