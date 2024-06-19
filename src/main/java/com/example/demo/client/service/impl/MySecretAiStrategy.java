/**
 * 
 */
package com.example.demo.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.api.dto.ProductResponse;
import com.example.demo.client.config.OAuth2Client;

/**
 * @author gary_kephart
 *
 */
@Service
public class MySecretAiStrategy implements InvestmentStrategy
{
  @Autowired
  private IntegratorClient client;
  
  
  public void execute()
  {
    String authorization = OAuth2Client.getAccessToken();
    
    ProductResponse products = this.client.getProducts(authorization, null, null, null, null, null, null, null);
    
    System.out.println("#products=" + products.getNumProducts());
  }
}
