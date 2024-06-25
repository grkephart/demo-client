/**
 * 
 */
package com.example.demo.client.service.impl;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cb.api.clients.CoinbaseFeignClient;
import com.example.demo.client.dto.DemoUserPojo;


/**
 * 
 */
@FeignClient(name = "integrator", url = "${integrator.service.url}")
public interface IntegratorClient extends CoinbaseFeignClient
{
  /**
   * @param bearerToken
   * @return
   */
  @GetMapping("tokens")
  List<DemoUserPojo> getUsers();

}
