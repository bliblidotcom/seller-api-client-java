package com.gdn.partners.mtaapi.request.product;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductFetchV2Request implements Serializable {
  private static final long serialVersionUID = -5610900256928638039L;
  private String gdnSku;
  private List<String> merchantSkus;
  private String productName;
  private String categoryCode;
  private String pickupPointCode;
  private boolean displayable;
  private boolean buyable;
  private int page;
  private int size;
  
}
