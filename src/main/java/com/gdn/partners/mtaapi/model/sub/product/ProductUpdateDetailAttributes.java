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
public class ProductUpdateDetailAttributes {
  private String attributeCode;
  private String attributeType;
  private List<String> values;
  private boolean skuValue;
  private String attributeName;
  private String itemSku;
}
