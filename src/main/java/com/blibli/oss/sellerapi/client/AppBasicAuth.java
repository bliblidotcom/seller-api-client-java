package com.blibli.oss.sellerapi.client;

import com.blibli.oss.sellerapi.client.core.BlibliSellerBasicAuthClient;
import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.request.order.main.OrderRegularFulfillmentV2Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class AppBasicAuth {

  private static ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) {
    try {
      //declare Blibli Merchant Client
      BlibliSellerBasicAuthClient client = new BlibliSellerBasicAuthClient();
      
      /**
       * #################################################################
       * ######     GLOBAL CONFIGURATION FOR GET & POST REQUEST     ######
       * #################################################################
       */
      //Base API configuration, for any GET & POST request
      //set this as global function of your framework, you need to pass this object for every request
      ApiConfig config = new ApiConfig();
      config.setApiClientId("mta-api-clientsdk-cc80f"); // your api client id
      config.setApiClientKey("mta-api-GUnrIlso6vPpbpn3ZyWLUwILCq6OxpAVcHj4hqeeppOVdNNVKZ"); // your api client key
      config.setApiSellerKey("BFF3899A8A23C12D5AB0596450722B9F401DC058A06DE69E7BA9C4174DBD795E"); // your api seller key
      config.setSignatureKey("12345678"); // put your secret key here to activate signature flow
      config.setBusinessPartnerCode("SDC-60001"); //your business partner code/merchant code
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
      params.put("orderNo", "40000198525");
      params.put("orderItemNo", "40000262429");

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
