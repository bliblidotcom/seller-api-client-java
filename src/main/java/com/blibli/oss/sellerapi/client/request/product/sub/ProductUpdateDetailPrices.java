package com.blibli.oss.sellerapi.client.request.product.sub;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDetailPrices {
  private String channelId;
  private Double price;
  private Double salePrice;
  private Double discountAmount;
  private Date discountStartDate;
  private Date discountEndDate;
  private String promotionName;
  
}
