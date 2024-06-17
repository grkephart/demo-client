/**
 * 
 */
package com.example.demo.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.api.clients.CoinbaseFeignClient;
import com.cb.api.dto.ProductResponse;

/**
 * @author gary_kephart
 *
 */
@Service
public class MySecretAiStrategy
{
  @Autowired
  CoinbaseFeignClient client;
  
  
  public void execute()
  {
    String authorization = OAuth2Client.getAccessToken();
    
    ProductResponse products = this.client.getProducts(authorization, null, null, null, null, null, null, null);
    
    System.out.println("#products=" + products.getNumProducts());
  }
}
