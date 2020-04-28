package com.blibli.oss.sellerapi.client;

import com.blibli.oss.sellerapi.client.core.BlibliMerchantClient;
import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.model.base.TokenRefresh;
import com.blibli.oss.sellerapi.client.model.base.TokenRequest;
import com.blibli.oss.sellerapi.client.request.order.main.OrderRegularFulfillmentV2Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class App {

  private static ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) {
    try {
      //declare Blibli Merchant Client
      BlibliMerchantClient client = new BlibliMerchantClient();

      /**
       * #################################################################
       * ######                 GET TOKEN SAMPLE                    ######
       * #################################################################
       */
      //configure get token required parameter
      TokenRequest reqToken = new TokenRequest(); 
      reqToken.setPlatformName("My Company Name"); //your company/platform name 
      reqToken.setTimeoutMs(15000); //your custom request timeout in millisecond 
      reqToken.setApiUsername("mta-api-toq-15126"); //your API username
      reqToken.setApiPwd("12345678"); //your API password
      reqToken.setMtaUsername("agie.external@mail.com"); //your MTA username
      reqToken.setMtaPwd("master"); //your MTA password
      
      //invoke 'Get Token API'
      String resultGetToken = client.requestToken("https://api-uata.gdn-app.com/v2/oauth/token", reqToken);
      JsonNode resultGetTokenJSON = mapper.readTree(resultGetToken);
      System.out.println(resultGetToken);


      /**
       * #################################################################
       * ######                REFRESH TOKEN SAMPLE                 ######
       * #################################################################
       */
      //configure refresh token required parameter
      TokenRefresh refresh = new TokenRefresh();
      refresh.setPlatformName("My Company Name"); //your company/platform name
      refresh.setTimeoutMs(15000); //your custom request timeout in millisecond
      refresh.setApiUsername("mta-api-toq-15126"); //your API username
      refresh.setApiPwd("12345678"); //your API password
      refresh.setRefreshToken(resultGetTokenJSON.get("refresh_token").asText()); //your refresh token value
      
      //invoke 'Refresh Token API'
      String resultRefreshToken = client.refreshToken("https://api-uata.gdn-app.com/v2/oauth/token", refresh);
      JsonNode resultRefreshTokenJSON = mapper.readTree(resultRefreshToken);
      System.out.println(resultRefreshToken);
      
      /**
       * #################################################################
       * ######     GLOBAL CONFIGURATION FOR GET & POST REQUEST     ######
       * #################################################################
       */
      //Base API configuration, for any GET & POST request
      //set this as global function of your framework, you need to pass this object for every request
      ApiConfig config = new ApiConfig();
      config.setToken(resultRefreshTokenJSON.get("access_token").asText()); //your API token
      config.setSignatureKey("tes"); //your API secret key
      config.setMtaUsername("agie.external@mail.com"); //your MTA username
      config.setBusinessPartnerCode("TOQ-15126"); //your business partner code/merchant code
      config.setPlatformName("My Company Name"); //your company/platform name
      config.setTimeoutMs(15000); //your custom timeout in millisecond
      
      /**
       * #################################################################
       * ######                 GET REQUEST SAMPLE                  ######
       * #################################################################
       */
      //set your request parameter url
      //no need to send: channelId, username, storeId, requestId, businessPartnerCode and merchantCode
      //they generated automatically by client codes
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("orderNo", "25100081147");
      params.put("orderItemNo", "25000246494");

      //your destination address for GET request
      String urlGetRequest = "https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail";
      //invoke Get Order Detail API
      String resultGetResult = client.invokeGet(urlGetRequest, params, config);
      JsonNode resultGetResultJSON = mapper.readTree(resultGetResult);
      System.out.println(resultGetResult);
      
      /**
       * #################################################################
       * ######                 POST REQUEST SAMPLE                 ######
       * #################################################################
       */
      //set your request parameter url
      //no need to send: channelId, username, storeId, requestId, businessPartnerCode and merchantCode
      //they generated automatically by client codes
      //set to empty Map if the API doesn't need parameter url
      Map<String, Object> paramsForPost = new HashMap<String, Object>();
      
      //set your request body with any object
      //but you can use client's request body in package com.gdn.mtaapi.sdk.model.request.*
      OrderRegularFulfillmentV2Request req = new OrderRegularFulfillmentV2Request();
      req.setAwbNo("123456"); //your awb no
      
      //your destination address for POST request
      String url = "https://api-uata.gdn-app.com/v2/proxy/seller/v1/orders/regular/" + 
          resultGetResultJSON.get("value").get("packageId").asText() + "/fulfill";
      //invoke Fulfill Order API
      String result = client.invokePost(url, paramsForPost, req, config);
      System.out.println(result);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
