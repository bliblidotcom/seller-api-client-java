package com.blibli.oss.sellerapi.client.util;

import org.apache.commons.lang3.StringUtils;

import com.blibli.oss.sellerapi.client.core.Constant;
import com.blibli.oss.sellerapi.client.model.base.ApiConfig;
import com.blibli.oss.sellerapi.client.model.base.TokenRefresh;
import com.blibli.oss.sellerapi.client.model.base.TokenRequest;

public class ApiValidator {
  
  public static void validateTokenRequest(TokenRequest req) {
    ApiValidator.validate(req.getPlatformName(), "platform name/channelId");
    ApiValidator.validate(req.getApiUsername(), "API username");
    ApiValidator.validate(req.getApiPwd(), "API password");
    ApiValidator.validate(req.getMtaUsername(), "MTA username");
    ApiValidator.validate(req.getMtaPwd(), "MTA password");
  }
  
  public static void validateTokenRefresh(TokenRefresh req) {
    ApiValidator.validate(req.getPlatformName(), "platform name/channelId");
    ApiValidator.validate(req.getApiUsername(), "API username");
    ApiValidator.validate(req.getApiPwd(), "API password");
  }
  
  public static void validateAPIConfig(ApiConfig config) {
    ApiValidator.validate(config.getToken(), "API client token");
    ApiValidator.validate(config.getSecretKey(), "API secret key");
    ApiValidator.validate(config.getMtaUsername(), "MTA username");
    ApiValidator.validate(config.getBusinessPartnerCode(), "business partner code ");
    ApiValidator.validate(config.getPlatformName(), "platform name/channelId");
    if (config.getTimeoutMs() == null) {
      config.setTimeoutMs(Constant.DEFAULT_TIMEOUT);
    }
  }
  
  public static void validate(String value, String title) {
    if (StringUtils.isBlank(value)) {
      throw new IllegalArgumentException("Input of " + title + " is blank");
    }
  }
  
}
