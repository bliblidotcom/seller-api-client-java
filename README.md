# Seller API Client for Java

It's an initial Java project to connect to Blibli Seller API. 
Please feel free to relay your comments, suggestions or corrections through pull request.

### Setup

<hr>

##### Maven

```xml
<!-- Add seller api client dependency -->
<dependency>
    <groupId>com.blibli.oss.sellerapi.client</groupId>
    <artifactId>seller-api-client</artifactId>
    <version>${seller-api-client.version}</version>
</dependency>

<!-- Set seller api client version -->
<properties>
    <seller-api-client.version>...</seller-api-client.version>
</properties>

<!-- add blibli bintray repository --> 
<repositories>
    <repository>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <id>bintray-bliblidotcom-maven</id>
      <name>bintray</name>
      <url>https://dl.bintray.com/bliblidotcom/maven</url>
    </repository>
</repositories>
```

##### JAR

> [Release note](https://github.com/bliblidotcom/seller-api-client-java/releases)

- v1.2.0-1 : https://github.com/bliblidotcom/seller-api-client-java/releases/tag/1.2.0-1
- v1.1.0-1 : https://github.com/bliblidotcom/seller-api-client-java/releases/tag/1.1.0-1

### Code Sample

<hr>

##### OAuth Flow

> Run App.java

1. Send token request to `https://api-uata.gdn-app.com/v2/oauth/token`
2. Send refresh token request with `[1]` response to `https://api-uata.gdn-app.com/v2/oauth/token`
3. Send order detail request with access token from `[2]` to `https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail`
4. Send fulfill order request with package id from `[3]` to `https://api-uata.gdn-app.com/v2/proxy/seller/v1/orders/regular/{packageId}/fulfill`

##### Basic Auth Flow

> Run AppBasicAuth.java

1. Send order detail request to `https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail`
2. Send fulfill order request with package id from `[1]` to `https://api-uata.gdn-app.com/v2/proxy/seller/v1/orders/regular/{packageId}/fulfill`