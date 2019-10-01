package com.gdn.partners.mtaapi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.gdn.partners.mtaapi.client.BlibliMerchantClient;
import com.gdn.partners.mtaapi.model.base.ApiConfig;
import com.gdn.partners.mtaapi.model.base.TokenRefresh;
import com.gdn.partners.mtaapi.model.base.TokenRequest;
import com.gdn.partners.mtaapi.model.sub.order.CombineShippingRequest;
import com.gdn.partners.mtaapi.request.order.OrderRegularFulfillmentV1Request;

public class App {
  public static void main(String[] args) {
    try {
      // declare Blibli Merchant Client
      BlibliMerchantClient client = new BlibliMerchantClient();

      /**
       * ################################################################# ###### GET TOKEN SAMPLE
       * ###### #################################################################
       */
      // configure get token required parameter
      TokenRequest reqToken = new TokenRequest();
      reqToken.setPlatformName("My Company Name"); // your company/platform name
      reqToken.setTimeoutMs(15000); // your custom request timeout in millisecond
      reqToken.setApiUsername("mta-api-toq-15126"); // your API username
      reqToken.setApiPwd("12345678"); // your API password
      reqToken.setMtaUsername("agie.external@mail.com"); // your MTA username
      reqToken.setMtaPwd("master"); // your MTA password

      // invoke 'Get Token API'
      String resultGetToken =
          client.requestToken("https://api-perf.gdn-app.com/v2/oauth/token", reqToken);
      System.out.println(resultGetToken);

      /**
       * ################################################################# ###### REFRESH TOKEN
       * SAMPLE ###### #################################################################
       */
      // configure refresh token required parameter
      TokenRefresh refresh = new TokenRefresh();
      refresh.setPlatformName("My Company Name"); // your company/platform name
      refresh.setTimeoutMs(15000); // your custom request timeout in millisecond
      refresh.setApiUsername("mta-api-toq-15126"); // your API username
      refresh.setApiPwd("12345678"); // your API password
      refresh.setRefreshToken("8cbfe880-9343-409e-be15-049ac6413b95"); // your refresh token value

      // invoke 'Refresh Token API'
      String resultRefreshToken =
          client.refreshToken("https://api-perf.gdn-app.com/v2/oauth/token", refresh);
      System.out.println(resultRefreshToken);

      /**
       * ################################################################# ###### GLOBAL
       * CONFIGURATION FOR GET & POST REQUEST ######
       * #################################################################
       */
      // Base API configuration, for any GET & POST request
      // set this as global function of your framework, you need to pass this object
      // for every request
      ApiConfig config = new ApiConfig();
      config.setToken("86bcb0aa-c493-434c-8dc5-086b3a710ad8"); // your API token
      config.setSecretKey("secret"); // your API secret key
      config.setMtaUsername("agie.external@mail.com"); // your MTA username
      config.setBusinessPartnerCode("TEB-24219"); // your business partner code/merchant code
      config.setPlatformName("My Company Name"); // your company/platform name
      config.setTimeoutMs(15000); // your custom timeout in millisecond

      /**
       * ################################################################# ###### GET REQUEST SAMPLE
       * ###### #################################################################
       */
      // set your request parameter url
      // no need to send: channelId, username, storeId, requestId, businessPartnerCode
      // and merchantCode
      // they generated automatically by client codes
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("orderNo", "25100026490");
      params.put("orderItemNo", "25000179189");

      // your destination address for GET request
      String urlGetRequest =
          "https://api-perf.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail";
      // invoke Get Order Detail API
      String resultGetResult = client.invokeGet(urlGetRequest, params, config);
      System.out.println(resultGetResult);

      /**
       * ################################################################# ###### POST REQUEST
       * SAMPLE ###### #################################################################
       */
      // set your request parameter url
      // no need to send: channelId, username, storeId, requestId, businessPartnerCode
      // and merchantCode
      // they generated automatically by client codes
      // set to empty Map if the API doesn't need parameter url
      Map<String, Object> paramsForPost = new HashMap<String, Object>();

      // set your request body with any object
      // but you can use client's request body in package
      // com.gdn.mtaapi.sdk.model.request.*
      OrderRegularFulfillmentV1Request req = new OrderRegularFulfillmentV1Request();
      req.setType(1);
      req.setOrderNo("25100026490");
      req.setOrderItemNo("25000179189");
      CombineShippingRequest combineShipping = new CombineShippingRequest();
      combineShipping.setOrderNo("25100026490");
      combineShipping.setOrderItemNo("25000179189");
      req.setCombineShipping(Arrays.asList(combineShipping));

      // your destination address for POST request
      String url =
          "https://api-perf.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/fulfillRegular";
      // invoke Fulfill Order API
      String result = client.invokePost(url, paramsForPost, req, config);
      System.out.println(result);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
