package com.blibli.oss.sellerapi.client.request.order.main;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderBigProductFulfillmentV1Request implements Serializable {
  private static final long serialVersionUID = -6662044214054960900L;
  private String orderItemId;
  private String itemSkuCode;
  private String settlementCode;
  private boolean isDeliveredByMerchant;
  private String merchantCourierName;
  private String merchantCourierType;
  private Date merchantDeliveryDateStart;
  private Date merchantDeliveryDateEnd;
  private boolean isInstallationRequired;
  private Date merchantInstallationDateStart;
  private Date merchantInstallationDateEnd;
  private String merchantInstallationOfficer;
  private String merchantInstallationMobile;
  private String merchantInstallationNote;
}
