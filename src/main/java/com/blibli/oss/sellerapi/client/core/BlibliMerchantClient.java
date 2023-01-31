package com.blibli.oss.sellerapi.client.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.model.base.TokenRefresh;
import com.blibli.oss.sellerapi.client.model.base.TokenRequest;
import com.blibli.oss.sellerapi.client.util.ApiValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlibliMerchantClient {

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

  public String invokeDelete(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    return invokeRequest(Constant.HTTP_DELETE, apiUrl, params, requestBody, config);
  }
	
  public String invokeRequest(String methodType, String apiUrl, Map<String, Object> params, 
      Object requestBody, ApiConfig config) throws Exception {
    ApiValidator.validateAPIConfig(config);
    String requestId = UUID.randomUUID().toString();
    generator.setMandatoryParam(params, config, requestId);
    URL url = new URL(generator.buildReqParam(apiUrl, params));
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    generator.setMandatoryHeader(apiUrl, requestId, httpURLConnection, methodType, config, 
        requestBody);

    if (Constant.HTTP_GET.equals(methodType) || Constant.HTTP_DELETE.equals(methodType)) {
      return BlibliHttpClient.invokeHttp(httpURLConnection);
    } else {
      return BlibliHttpClient.invokeWithBody(httpURLConnection, 
          mapper.writeValueAsString(requestBody));
    }
  }

  public String requestToken(String apiUrl, TokenRequest req) throws IOException {
    ApiValidator.validateTokenRequest(req);
    URL url = new URL(apiUrl + "?channelId=" + req.getPlatformName()
        .replaceAll(" ", "")
        .toLowerCase());
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    generator.configTokenRequest(httpURLConnection, req.getTimeoutMs(), req.getApiUsername(), 
        req.getApiPwd());
    
    return BlibliHttpClient.invokeWithBody(httpURLConnection, 
        generator.buildRequestTokenBody(req));
  }

  public String refreshToken(String apiUrl, TokenRefresh req) throws IOException {
    ApiValidator.validateTokenRefresh(req);
    URL url = new URL(apiUrl + "?channelId=" + req.getPlatformName()
        .replaceAll(" ", "")
        .toLowerCase());
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    generator.configTokenRequest(httpURLConnection, req.getTimeoutMs(), req.getApiUsername(), 
        req.getApiPwd());
    
    return BlibliHttpClient.invokeWithBody(httpURLConnection, 
        generator.buildRefreshTokenBody(req));
  }
}
