package com.blibli.oss.sellerapi.client.request.product.sub;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDetailItems {
  private String itemSku;
  private String skuCode;
  private String merchantSku;
  private String upcCode;
  private String itemName;
  private double length;
  private double width;
  private double height;
  private double weight;
  private double shippingWeight;
  private int dangerousGoodsLevel;
  private Boolean lateFulfillment;
  private String pickupPointCode;
  private String pickupPointName;
  private int deltaStock;
  private Boolean synchronizeStock;
  private Boolean off2OnActiveFlag;
  private List<ProductUpdateDetailPrices> prices;
  private List<ProductUpdateDetailViewConfigs> viewConfigs;
  private List<ProductUpdateDetailImages> images;
  
}
