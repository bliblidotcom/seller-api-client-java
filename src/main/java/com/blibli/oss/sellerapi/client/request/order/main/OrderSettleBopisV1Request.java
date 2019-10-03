package com.blibli.oss.sellerapi.client.request.order.main;

import java.io.Serializable;

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
public class OrderSettleBopisV1Request implements Serializable {
  private static final long serialVersionUID = -6532551911314138115L;
  private int type;  
  private String orderNo;
  private String orderItemNo;
  private String settlementCode;
}
