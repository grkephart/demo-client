/**
 * 
 */
package com.example.demo.client.service.impl;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.cb.api.models.orders.CoinbaseNewOrder;
import com.cb.api.models.orders.CreateOrderResponse;
import com.cb.api.models.products.ProductResponse;
import com.example.demo.client.dto.DemoUserPojo;


/**
 * 
 */
@FeignClient(name = "integrator", url = "${integrator.service.url}")
public interface IntegratorClient
{

  /**
   * Create an order with a specified product_id (asset-pair), side (buy/sell), etc.
   */
  @PostMapping("orders")
  CreateOrderResponse createOrder(
    @RequestHeader("Authorization")
    String authorization,
    CoinbaseNewOrder order);


  /**
   * Get a list of the available currency pairs for trading.
   * 
   * @param limit A limit describing how many products to return.
   * @param offset Number of products to offset before returning.
   * @param productType Type of products to return
   * @param productIds List of product IDs to return.
   * @param contractExpiryType
   * @param expiringContractStatus
   * @return
   */
  @GetMapping("products")
  ProductResponse getProducts(
    @RequestHeader("Authorization")
    String authorization,
    @RequestParam("limit")
    Integer limit,
    @RequestParam("starting_after")
    String startingAfter,
    @RequestParam("offset")
    Integer offset,
    @RequestParam("product_type")
    String productType,
    @RequestParam("product_ids")
    String[] productIds,
    @RequestParam("contract_expiry_type")
    String contractExpiryType,
    @RequestParam("expiring_contract_status")
    String expiringContractStatus);

  /**
   * @param bearerToken
   * @return
   */
  @GetMapping("tokens")
  List<DemoUserPojo> getUsers();

}
