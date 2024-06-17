package com.example.demo.client.config;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class OAuth2Client
{
  @Value("${http://localhost:8080/demo/oauth/token}")
  private static String tokenUrl;
  @Value("${client-id}")
  private static String clientId;
  @Value("${client-secret}")
  private static String clientSecret;

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public static String getAccessToken()
  {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth(clientId, clientSecret);

    Map<String, String> params = new HashMap<>();
    params.put("grant_type", "client_credentials");

    HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

    ResponseEntity<Map> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity,
      Map.class);
    Map<String, String> responseBody = response.getBody();

    return responseBody.get("access_token");
  }
}