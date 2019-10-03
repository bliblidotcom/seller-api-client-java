package com.blibli.oss.sellerapi.client.request.product.main;

import java.io.Serializable;
import java.util.List;

import com.blibli.oss.sellerapi.client.request.product.sub.ProductUpdateDetails;
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
public class ProductUpdateDetailV1Request implements Serializable {
  private static final long serialVersionUID = 9194349305741579535L;
  private String merchantCode;
  private List<ProductUpdateDetails> productDetailRequests;

}
