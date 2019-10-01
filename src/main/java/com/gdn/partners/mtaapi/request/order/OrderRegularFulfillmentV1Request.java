package com.gdn.partners.mtaapi.request.order;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gdn.partners.mtaapi.model.sub.order.CombineShippingRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderRegularFulfillmentV1Request implements Serializable {
  private static final long serialVersionUID = -749846550687894639L;
  private int type;
  private String orderNo;
  private String orderItemNo;
  private String awbNo;
  private List<CombineShippingRequest> combineShipping;
}
