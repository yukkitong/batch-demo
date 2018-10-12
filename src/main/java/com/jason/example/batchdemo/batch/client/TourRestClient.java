package com.jason.example.batchdemo.batch.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TourRestClient {

  private final Logger logger = LoggerFactory.getLogger(TourRestClient.class);

  @Value("${tourapi.base}")
  private String baseUrl;

  @Value("${tourapi.key}")
  private String tourApiKey;

  private Client client = ClientBuilder.newClient();

  public String fetchAreaBasedListCount() {
    WebTarget webTarget = client.target(baseUrl).path("areaBasedList")
      .queryParam("ServiceKey", tourApiKey)
      .queryParam("MobileOS", "ETC")
      .queryParam("MobileApp", "TestApp")
      .queryParam("listYN", "N");
    return webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
  }
  
  public String fetchAreaBasedList(int pageNo) {
    return fetchAreaBasedList(30, pageNo);
  }

  public String fetchAreaBasedList(int numOfRows, int pageNo) {
    WebTarget webTarget = client.target(baseUrl).path("areaBasedList")
      .queryParam("ServiceKey", tourApiKey)
      .queryParam("MobileOS", "ETC")
      .queryParam("MobileApp", "TestApp")
      .queryParam("arrange", "C")
      .queryParam("numOfRows", String.valueOf(numOfRows))
      .queryParam("pageNo", String.valueOf(pageNo));
    return webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
  }
}