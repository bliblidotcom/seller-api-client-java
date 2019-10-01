package com.gdn.partners.mtaapi.model.sub.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class CombineShippingRequest {
  private String orderNo;
  private String orderItemNo;
}
