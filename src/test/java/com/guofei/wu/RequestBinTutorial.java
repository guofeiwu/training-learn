package com.guofei.wu;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;


public class RequestBinTutorial {
  public static void main(String[] args) {
    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod("http://requestbin.net/r/1ifp7uo1");
    try {
      int statusCode = client.executeMethod(method);
      byte[] responseBody = method.getResponseBody();
      System.out.println(new String(responseBody));
    } catch (Exception e) {
      System.err.println("Fatal error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
  }
}