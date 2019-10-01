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
public class OrderBopisFulfillmentV1Request implements Serializable {
  private static final long serialVersionUID = -7196394546972358820L;
  private String orderItemId;
  private String itemSkuCode;
}
