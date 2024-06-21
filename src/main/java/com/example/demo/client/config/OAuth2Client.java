package com.example.demo.client.config;


import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class OAuth2Client
{
  @Value("${spring.security.oauth2.client.registration.integrator.client-id}")
  private String             clientId;

  @Value("${spring.security.oauth2.client.registration.integrator.client-secret}")
  private String             clientSecret;

  @Value("${spring.security.oauth2.client.registration.integrator.token-uri}")
  private String             tokenUrl;

  private final RestTemplate restTemplate;

  public OAuth2Client()
  {
    this.restTemplate = new RestTemplate();
    
    this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    this.restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
  }


  public String getAccessToken()
  {
    String authHeader = "Basic " + Base64.getEncoder()
        .encodeToString((this.clientId + ":" + this.clientSecret).getBytes());
    HttpHeaders headers = new HttpHeaders();
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
  
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.set("Authorization", authHeader);

    map.add("grant_type", "client_credentials");

    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

    try
    {
      ResponseEntity<Map> response = this.restTemplate.exchange(this.tokenUrl, HttpMethod.POST,
        entity, Map.class);
      Map<String, Object> responseBody = response.getBody();
      
      if (responseBody != null && responseBody.containsKey("access_token"))
      {
        return "Bearer " + responseBody.get("access_token").toString();
      }
      else
      {
        throw new RuntimeException("Failed to retrieve access token");
      }
    }
    catch (RestClientException e)
    {
      throw new RuntimeException("Error while retrieving access token", e);
    }
  }
}