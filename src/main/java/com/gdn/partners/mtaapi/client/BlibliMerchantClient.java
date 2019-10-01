package com.gdn.partners.mtaapi.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdn.partners.mtaapi.model.base.ApiConfig;
import com.gdn.partners.mtaapi.model.base.TokenRefresh;
import com.gdn.partners.mtaapi.model.base.TokenRequest;
import com.gdn.partners.mtaapi.util.ApiValidator;

public class BlibliMerchantClient {

  private SignatureGenerator generator = new SignatureGenerator();
  private static ObjectMapper mapper = new ObjectMapper();

  public String invokeGet(String apiUrl, Map<String, Object> params, ApiConfig config)
      throws Exception {
    ApiValidator.validateAPIConfig(config);
    String requestId = UUID.randomUUID().toString();

    generator.setMandatoryParam(params, config, requestId);
    URL url = new URL(generator.buildReqParam(apiUrl, params));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    generator.setMandatoryHeader(apiUrl, requestId, con, Constant.HTTP_GET, config, null);

    return this.invokeHttp(con);
  }

  public String invokePost(String apiUrl, Map<String, Object> params, Object requestBody,
      ApiConfig config) throws Exception {
    ApiValidator.validateAPIConfig(config);
    String requestId = UUID.randomUUID().toString();

    generator.setMandatoryParam(params, config, requestId);
    URL url = new URL(generator.buildReqParam(apiUrl, params));
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    generator.setMandatoryHeader(apiUrl, requestId, con, Constant.HTTP_POST, config, requestBody);

    return this.invokeWithBody(con, mapper.writeValueAsString(requestBody));
  }

  public String requestToken(String apiUrl, TokenRequest req) throws IOException {
    ApiValidator.validateTokenRequest(req);
    URL url = new URL(apiUrl + "?channelId=" + req.getPlatformName().replaceAll(" ", "").toLowerCase());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    generator.configTokenRequest(con, req.getTimeoutMs(), req.getApiUsername(), req.getApiPwd());
    
    return this.invokeWithBody(con, generator.buildRequestTokenBody(req));
  }

  public String refreshToken(String apiUrl, TokenRefresh req) throws IOException {
    ApiValidator.validateTokenRefresh(req);
    URL url = new URL(apiUrl + "?channelId=" + req.getPlatformName().replaceAll(" ", "").toLowerCase());
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    generator.configTokenRequest(con, req.getTimeoutMs(), req.getApiUsername(), req.getApiPwd());
    
    return this.invokeWithBody(con, generator.buildRefreshTokenBody(req));
  }

  private String invokeWithBody(HttpURLConnection con, String body) throws IOException {
    con.setDoOutput(true);
    try(DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
    	out.writeBytes(body);
    }
    return this.invokeHttp(con);
  }

  private String invokeHttp(HttpURLConnection con) throws IOException {
    InputStream inputStream = null;
    if (con.getResponseCode() > 299) {
    	inputStream = con.getErrorStream();
    } else {
    	inputStream = con.getInputStream();
    }
    
    StringBuffer response = new StringBuffer();
	try (Reader streamReader = new InputStreamReader(inputStream);
			BufferedReader in = new BufferedReader(streamReader);) {
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
	}
    return response.toString();
  }

}
