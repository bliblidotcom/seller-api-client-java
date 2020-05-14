# Seller API Client for Java

It's an initial Java project to connect to Blibli Seller API. 
Please feel free to relay your comments, suggestions or corrections through pull request.

### Setup

##### Option 1 - Import Final JAR Into Your Java Project

> You can get final JAR to import into your Java project by download it from urls below.    
> [Release note](https://github.com/bliblidotcom/seller-api-client-java/releases)

- v1.2.0-3 : https://github.com/bliblidotcom/seller-api-client-java/releases/tag/1.2.0-3
- v1.1.0-1 : https://github.com/bliblidotcom/seller-api-client-java/releases/tag/1.1.0-1

##### Option 2 - Import Maven Dependency

> You can import final JAR into your Java project using maven.   
> Add the following code in your pom.xml.

```xml
<!-- Add seller api client dependency -->
<dependency>
    <groupId>com.blibli.oss.sellerapi.client</groupId>
    <artifactId>seller-api-client</artifactId>
    <version>${seller-api-client.version}</version>
</dependency>

<!-- Set seller api client version -->
<properties>
    <seller-api-client.version>1.2.0-3</seller-api-client.version>
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

##### Option 3 - Copy Client SDK Codes Into Your Java Project

> To understand how code works, you can simply run code sample below.   
> Then you can dive into the codes and copy it as your needs.

### Sample Codes

<hr>

##### OAuth Flow

> Run App.java and it will work like the below sequence:

1. Send token request to `https://api-uata.gdn-app.com/v2/oauth/token`
2. Send refresh token request with `[1]` response to `https://api-uata.gdn-app.com/v2/oauth/token`
3. Send order detail request with access token from `[2]` to `https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail`
4. Send fulfill order request with access token from `[2]` to `https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/fulfillRegular`

##### Basic Auth Flow

> Run AppBasicAuth.java and it will work like the below sequence:

1. Send order detail request to `https://api-uata.gdn-app.com/v2/proxy/mta/api/businesspartner/v1/order/orderDetail`
2. Send fulfill order request with package id from `[1]` to `https://api-uata.gdn-app.com/v2/proxy/seller/v1/orders/regular/{packageId}/fulfill`