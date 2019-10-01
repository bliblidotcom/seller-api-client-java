package com.gdn.partners.mtaapi.request.order;

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
public class OrderPartialFulfillmentV1Request implements Serializable {
  private static final long serialVersionUID = -6260868260771286471L;
  private String orderNo;
  private String orderItemNo;
  private int completeQuantity;
  private String reason;
}
