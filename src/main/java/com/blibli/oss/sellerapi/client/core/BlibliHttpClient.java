package com.blibli.oss.sellerapi.client.core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class BlibliHttpClient {

  public static String invokeWithBody(HttpURLConnection httpURLConnection, String body) 
      throws IOException {
    httpURLConnection.setDoOutput(true);

    try(DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream())) {
      out.writeBytes(body);
    }

    return invokeHttp(httpURLConnection);
  }

  public static String invokeHttp(HttpURLConnection httpURLConnection) throws IOException {
    InputStream inputStream = getInputStream(httpURLConnection);
    StringBuilder response = new StringBuilder();

    try (Reader streamReader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(streamReader)) {
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
    }

    return response.toString();
  }
  
  private static InputStream getInputStream(HttpURLConnection httpURLConnection) 
      throws IOException {
    return httpURLConnection.getResponseCode() / 100 == 2 ?
        httpURLConnection.getInputStream() :
        httpURLConnection.getErrorStream();
  }
}
