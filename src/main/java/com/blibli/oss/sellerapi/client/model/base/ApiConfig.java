package com.blibli.oss.sellerapi.client.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiConfig {
  private String token;
  private String secretKey;
  private String mtaUsername;
  private String businessPartnerCode;
  private String platformName;
  private Integer timeoutMs;
}
