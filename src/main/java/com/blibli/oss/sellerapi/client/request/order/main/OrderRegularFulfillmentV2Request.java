package com.blibli.oss.sellerapi.client.request.order.main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRegularFulfillmentV2Request implements Serializable {
  private static final long serialVersionUID = -749846550687894639L;
  private String awbNo;
}
