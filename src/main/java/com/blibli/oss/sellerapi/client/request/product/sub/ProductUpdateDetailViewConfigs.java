package com.blibli.oss.sellerapi.client.request.product.sub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDetailViewConfigs {
  private String channelId;
  private Boolean display;
  private Boolean buyable;
}
