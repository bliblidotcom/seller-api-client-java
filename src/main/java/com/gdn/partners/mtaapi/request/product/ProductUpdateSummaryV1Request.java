package com.gdn.partners.mtaapi.request.product;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gdn.partners.mtaapi.model.sub.product.ProductUpdateSummaryItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductUpdateSummaryV1Request implements Serializable {
  private static final long serialVersionUID = 6359198246666327510L;
  private String merchantCode;
  private List<ProductUpdateSummaryItems> productRequests;
}
