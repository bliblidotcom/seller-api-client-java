package com.blibli.oss.sellerapi.client.core;

import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.util.ApiValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

public class BlibliSellerBasicAuthClient {

  private static ObjectMapper mapper = new ObjectMapper();

  private SignatureGenerator generator = new SignatureGenerator();

  public String invokeGet(String apiUrl, Map<String, Object> params, ApiConfig config)
      throws Exception {
    return invokeRequest(Constant.HTTP_GET, apiUrl, params, null, config);
  }

  public String invokePost(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    return invokeRequest(Constant.HTTP_POST, apiUrl, params, requestBody, config);
  }

  public String invokePut(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    return invokeRequest(Constant.HTTP_PUT, apiUrl, params, requestBody, config);
  }

  public String invokePatch(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    return invokeRequest(Constant.HTTP_PATCH, apiUrl, params, requestBody, config);
  }

  public String invokeDelete(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    return invokeRequest(Constant.HTTP_DELETE, apiUrl, params, requestBody, config);
  }

  public String invokeRequest(String methodType, String apiUrl, Map<String, Object> params,
      Object requestBody, ApiConfig config) throws Exception {
    ApiValidator.validateAPIConfigBasicAuth(config);
    String requestId = UUID.randomUUID().toString();
    generator.setMandatoryParam(params, config, requestId);
    URL url = new URL(generator.buildReqParam(apiUrl, params));
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    generator.setMandatoryHeaderBasicAuth(apiUrl, requestId, httpURLConnection, methodType, config, 
        requestBody);

    if (Constant.HTTP_GET.equals(methodType) || Constant.HTTP_DELETE.equals(methodType)) {
      return BlibliHttpClient.invokeHttp(httpURLConnection);
    } else {
      return BlibliHttpClient.invokeWithBody(httpURLConnection,
          mapper.writeValueAsString(requestBody));
    }
  }
}
