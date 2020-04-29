package com.blibli.oss.sellerapi.client.core;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.model.base.TokenRefresh;
import com.blibli.oss.sellerapi.client.model.base.TokenRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import sun.misc.BASE64Encoder;

public class SignatureGenerator {

  private final String HMACSHA256 = "HmacSHA256";
  private final String UTF8 = "UTF-8";
  private static ObjectMapper mapper = new ObjectMapper();

  public void setMandatoryParam(Map<String, Object> params, ApiConfig config, String requestId) {
    if(params != null) {
      params.put("channelId", config.getPlatformName()
          .replaceAll(" ", "-")
          .toLowerCase());
      params.put("username", config.getMtaUsername());
      params.put("storeId", Constant.BLIBLI_STORE_ID);
      params.putIfAbsent("requestId", requestId);
      params.put("businessPartnerCode", config.getBusinessPartnerCode());
      params.put("merchantCode", config.getBusinessPartnerCode());
      params.put("storeCode", config.getBusinessPartnerCode());
    }
  }

  public void setMandatoryHeader(String apiUrl, String requestId, HttpURLConnection con,
      String methodType, ApiConfig config, Object requestBody)
      throws ProtocolException {
    Date date = new Date();
    String rawSignature = this.generateRawSignature(date, methodType, apiUrl, requestBody);
    String signature = this.generateSignature(config.getSignatureKey(), rawSignature);

    con.setRequestMethod(methodType);
    con.setConnectTimeout(config.getTimeoutMs());
    con.setReadTimeout(config.getTimeoutMs());

    con.setRequestProperty("Authorization", "bearer " + config.getToken());
    con.setRequestProperty("x-blibli-mta-authorization",
        "BMA " + config.getMtaUsername() + ":" + signature);
    con.setRequestProperty("x-blibli-mta-date-milis", String.valueOf(date.getTime()));
    con.setRequestProperty("Content-Type", Constant.APPLICATION_JSON);
    con.setRequestProperty("Accept", Constant.APPLICATION_JSON);
    con.setRequestProperty("requestId", requestId);
    con.setRequestProperty("sessionId", requestId);
    con.setRequestProperty("username", config.getMtaUsername());
  }

  public void setMandatoryHeaderBasicAuth(String apiUrl, String requestId, HttpURLConnection con,
      String methodType, ApiConfig config, Object requestBody) {
    if (config.getSignatureKey() != null) {
      Date date = new Date();
      String rawSignature = this.generateRawSignature(date, methodType, apiUrl, requestBody);
      String signature = this.generateSignature(config.getSignatureKey(), rawSignature);

      con.setRequestProperty("Signature", signature);
      con.setRequestProperty("Signature-Time", String.valueOf(date.getTime()));
    }

    String encodedValue = generateBasicAuthValue(config.getApiClientId(), config.getApiClientKey());
    con.setRequestProperty("Authorization", "Basic " + encodedValue);
    con.setRequestProperty("Content-Type", Constant.APPLICATION_JSON);
    con.setRequestProperty("Accept", Constant.APPLICATION_JSON);
    con.setRequestProperty("Api-Seller-Key", config.getApiSellerKey());
  }

  public String buildReqParam(String url, Map<String, Object> params) {
    StringBuilder sb = new StringBuilder(url);
    sb.append("?");
    for (Map.Entry<String, Object> entry : params.entrySet()) {
      sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
    }
    return sb.toString();
  }

  public String buildRequestTokenBody(TokenRequest req) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    sb.append("&grant_type=").append(URLEncoder.encode("password", "UTF-8"));
    sb.append("&username=").append(URLEncoder.encode(req.getMtaUsername(), "UTF-8"));
    sb.append("&password=").append(URLEncoder.encode(req.getMtaPwd(), "UTF-8"));
    return sb.toString();
  }
  
  public String buildRefreshTokenBody(TokenRefresh req) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    sb.append("&grant_type=").append(URLEncoder.encode("refresh_token", "UTF-8"));
    sb.append("&refresh_token=").append(URLEncoder.encode(req.getRefreshToken(), "UTF-8"));
    sb.append("&clientId=").append(URLEncoder.encode(req.getApiUsername(), "UTF-8"));
    return sb.toString();
  }

  public void configTokenRequest(HttpURLConnection con, Integer timeout, String username,
      String pwd) throws ProtocolException {
    con.setRequestMethod(Constant.HTTP_POST);

    if (timeout == null) {
      timeout = Constant.DEFAULT_TIMEOUT;
    }
    con.setConnectTimeout(timeout);
    con.setReadTimeout(timeout);

    String encodedAuthorization = generateBasicAuthValue(username, pwd);
    con.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
    con.setRequestProperty("Accept", Constant.APPLICATION_JSON);
  }

  private String generateRawSignature(Date date, String methodType, String url, Object body) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz YYYY");
    dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

    String md5Body = "";
    if (body != null) {
      md5Body = this.generateMd5(body);
    }

    String contentType = "";
    if (methodType.equals(Constant.HTTP_GET)) {
      contentType = "";
    } else {
      contentType = Constant.APPLICATION_JSON;
    }

    StringBuilder result = new StringBuilder();
    result.append(methodType).append("\n").append(md5Body).append("\n")
        .append(contentType).append("\n").append(dateFormat.format(date)).append("\n")
        .append(this.getUrlMetadata(url));
    return result.toString();
  }

  private String generateSignature(String secret, String data) {
    try {
      SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(this.UTF8), this.HMACSHA256);
      Mac mac = Mac.getInstance(this.HMACSHA256);
      mac.init(signingKey);
      byte[] rawHmac = mac.doFinal(data.getBytes(this.UTF8));
      return Base64.encodeBase64String(rawHmac);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to generate API signature " + e.getMessage(), e);
    }
  }

  private String generateMd5(Object data) throws RuntimeException {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(mapper.writeValueAsString(data).getBytes());
      byte[] digest = md.digest();
      return DatatypeConverter.printHexBinary(digest).toLowerCase();
    } catch (Exception e) {
      throw new RuntimeException("Error when generating MD5 of body request " + e.getMessage(), e);
    }
  }

  private String getUrlMetadata(String url) {
    try {
      String[] urls = url.split("/proxy");
      url = urls[1];
      if (url.contains("/mta")) {
        url = url.replaceFirst("/mta", "/mtaapi");
      }
      return url;
    } catch (Exception e) {
      throw new IllegalArgumentException("Your API pattern is wrong " + url, e);
    }
  }

  private String generateBasicAuthValue(String username, String password) {
    BASE64Encoder enc = new BASE64Encoder();
    String authValue = username + ":" + password;
    return enc.encode(authValue.getBytes()).replaceAll("\\s", "");
  }
}
