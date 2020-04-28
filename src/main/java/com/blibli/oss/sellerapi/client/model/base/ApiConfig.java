package com.blibli.oss.sellerapi.client.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiConfig {

  private String token;
  private String signatureKey;
  private String mtaUsername;
  private String businessPartnerCode;
  private String platformName;

  private String apiClientId;
  private String apiClientKey;
  private String apiSellerKey;

  private Integer timeoutMs;
}
