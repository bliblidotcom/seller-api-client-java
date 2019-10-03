package com.blibli.oss.sellerapi.client.request.product.sub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDetailImages {
  private boolean mainImage;
  private int sequence;
  private String locationPath;
}
