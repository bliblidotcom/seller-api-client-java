package com.blibli.oss.sellerapi.client.request.product.main;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.blibli.oss.sellerapi.client.request.product.sub.ProductCreateItems;
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
public class ProductCreateV2Request implements Serializable {
  private static final long serialVersionUID = -7753080349788419689L;
  private String requestId;
  private String username;
  private String bpCode;
  private String storeId;
  
  private String productCode;
  private String name;
  private String brand;
  private String url;
  private String categoryCode;
  
  private int productType;
  private String pickupPointCode;
  private double length;
  private double width;
  private double height;
  private double weight;
  
  private byte[] description;
  private byte[] uniqueSellingPoint;
  private byte[] productStory;
  
  private Map<String, String> productNonDefiningAttributes;
  private Map<String, List<String>> productDefiningAttributes;
  private List<ProductCreateItems> productItems;

  private List<String> images;
  private Map<String, String> imageMap;
}
