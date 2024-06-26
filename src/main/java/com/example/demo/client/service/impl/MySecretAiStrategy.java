/**
 * 
 */
package com.example.demo.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.api.dto.ProductResponse;
import com.cb.api.service.CoinbaseAuthService;

/**
 * @author gary_kephart
 *
 */
@Service
public class MySecretAiStrategy implements InvestmentStrategy
{
  @Autowired
  private IntegratorClient client;
  
  @Autowired
  private CoinbaseAuthService coinbaseAuthService;
  
  
  public void execute()
  {
    String authorization = coinbaseAuthService.getAuthorization();
    
    ProductResponse products = this.client.getProducts(authorization, null, null, null, null, null, null, null);
    
    System.out.println("#products=" + products.getNumProducts());
  }
}
