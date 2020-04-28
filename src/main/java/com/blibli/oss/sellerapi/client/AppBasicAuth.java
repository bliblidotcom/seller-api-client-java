package com.blibli.oss.sellerapi.client;

import com.blibli.oss.sellerapi.client.core.BlibliSellerBasicAuthClient;
import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.request.order.main.OrderRegularFulfillmentV1Request;
import com.blibli.oss.sellerapi.client.request.order.sub.CombineShippingRequest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class AppBasicAuth {

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
      config.setApiClientKey("mta-api-ySvFBOwPHTTBhccx89y2QxORSyFEesT55H2ws95fbPs8fsNV9y"); // your api client key
      config.setApiSellerKey("495930D13E51161331FB6423B048FB759B39E1573F90673F94558D727C04E917"); // your api seller key
      config.setSignatureKey("secret"); // put your secret key here to activate signature flow
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
      //params.put("orderNo", "40000198525");
      //params.put("orderItemNo", "40000262429");

      //your destination address for GET request
      String urlGetRequest = "https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail";
      //invoke Get Order Detail API
      String resultGetResult = client.invokeGet(urlGetRequest, params, config);
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
      OrderRegularFulfillmentV1Request req = new OrderRegularFulfillmentV1Request();
      req.setType(1);
      req.setOrderNo("40000198525");
      req.setOrderItemNo("40000262429");
      CombineShippingRequest combineShipping = new CombineShippingRequest();
      combineShipping.setOrderNo("40000198522");
      combineShipping.setOrderItemNo("40000262426");
      req.setCombineShipping(Collections.singletonList(combineShipping));
      
      //your destination address for POST request
      String url = "https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/fulfillRegular";
      //invoke Fulfill Order API
      String result = client.invokePost(url, paramsForPost, req, config);
      System.out.println(result);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
