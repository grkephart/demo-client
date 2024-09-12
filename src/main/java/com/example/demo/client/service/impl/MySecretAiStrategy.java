/**
 * 
 */
package com.example.demo.client.service.impl;


import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.api.models.orders.CoinbaseNewOrder;
import com.cb.api.models.orders.CreateOrderResponse;
import com.cb.api.models.orders.Side;
import com.cb.api.models.products.ProductResponse;
import com.cb.api.service.CoinbaseAuthService;
import com.cb.api.service.OrderBuilder;
import com.cb.api.service.impl.OrderBuilderImpl;


/**
 * @author gary_kephart
 *
 */
@Service
public class MySecretAiStrategy implements InvestmentStrategy
{
  @Autowired
  private IntegratorClient    client;

  @Autowired
  private CoinbaseAuthService coinbaseAuthService;

  /**
   * @param authorization
   */
  private void createOrder(
    String authorization)
  {
    OrderBuilder orderBuilder = new OrderBuilderImpl();
    String clientOrderId = "AI Order " + Instant.now();
    Side side = Side.BUY;
    String productId = "BTC-USD";
    String baseSize = null;
    String quoteSize = "1.00"; // one dollar
    CoinbaseNewOrder order = orderBuilder.buildMarketOrder(clientOrderId, productId, side, baseSize,
      quoteSize);

    CreateOrderResponse response = this.client.createOrder(authorization, order);
  }


  public void execute()
  {
    String authorization = coinbaseAuthService.getAuthorization();

    getProducts(authorization);
    createOrder(authorization);
  }


  /**
   * @param authorization
   */
  private void getProducts(
    String authorization)
  {
    ProductResponse products = this.client.getProducts(authorization, null, null, null, null, null,
      null, null);

    System.out.println("#products=" + products.getNumProducts());
  }
}
