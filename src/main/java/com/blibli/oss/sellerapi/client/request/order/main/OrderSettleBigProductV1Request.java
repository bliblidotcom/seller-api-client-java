package com.blibli.oss.sellerapi.client.request.order.main;

import java.io.Serializable;
import java.util.Date;

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
public class OrderSettleBigProductV1Request implements Serializable {
  private static final long serialVersionUID = -6175822149301864244L;
  private int type;  
  private String orderNo;
  private String orderItemNo;
  private Date deliveredDate;
  private String recipientName;
  private String recipientStatus;
}
