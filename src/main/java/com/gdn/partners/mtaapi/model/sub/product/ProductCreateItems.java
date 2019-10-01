package com.gdn.partners.mtaapi.model.sub.product;

import java.util.List;
import java.util.TreeMap;

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
public class ProductCreateItems {
  private String upcCode;
  private String merchantSku;
  private double price;
  private double salePrice;
  private int stock;
  private int minimumStock;
  private boolean displayable;
  private boolean buyable;
  private List<String> images;
  private TreeMap<String, String> attributesMap;
  private int dangerousGoodsLevel;
}
