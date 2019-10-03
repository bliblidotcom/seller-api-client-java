package com.blibli.oss.sellerapi.client.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class TokenRequest {
  private String apiUsername;
  private String apiPwd;
  private String mtaUsername;
  private String mtaPwd;
  private String platformName;
  private Integer timeoutMs;
}
