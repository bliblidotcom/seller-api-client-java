package com.gdn.partners.mtaapi.model.sub.product;

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
