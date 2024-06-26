/**
 * 
 */
package com.example.demo.client.service.impl;


import java.security.AccessControlException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientResponseException;

import com.cb.api.service.CoinbaseAuthService;
import com.example.demo.client.dto.DemoUserPojo;


/**
 * 
 */
public class InvestorServiceImpl
{

  @Autowired
  private CoinbaseAuthService coinbaseAuthService;
  @Autowired
  private IntegratorClient    integratorClient;
  /**
   * @return
   * @throws ClientRegistrationException
   * @throws RestClientResponseException
   */
  public List<DemoUserPojo> retrieveUserTokens()
  {
    String authorization = coinbaseAuthService.getAuthorization();

    if (authorization != null)
    {
      List<DemoUserPojo> response = integratorClient.getUsers();

      return response;
    }
    else
      throw new AccessControlException("Unauthorized Investor access");

  }
}
