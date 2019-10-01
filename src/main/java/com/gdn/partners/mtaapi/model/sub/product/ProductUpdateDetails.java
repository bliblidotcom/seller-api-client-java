package com.gdn.partners.mtaapi.model.sub.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDetails {
  private String productSku;
  private String productCode;
  private String businessPartnerCode;
  private Boolean synchronize;
  private String productName;
  private Integer productType;
  private String categoryCode;
  private String categoryName;
  private String categoryHierarchy;
  private String brand;
  private String description;
  private String specificationDetail;
  private String uniqueSellingPoint;
  private String productStory;
  private List<ProductUpdateDetailItems> items;
  private List<ProductUpdateDetailAttributes> attributes;
  private List<ProductUpdateDetailImages> images;
  private String url;
  private Boolean installationRequired;
  
}
