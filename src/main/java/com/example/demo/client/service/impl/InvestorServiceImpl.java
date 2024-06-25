/**
 * 
 */
package com.example.demo.client.service.impl;


import java.security.AccessControlException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.client.RestClientResponseException;

import com.example.demo.client.dto.UserTokenPojo;


/**
 * 
 */
public class InvestorServiceImpl
{
  private static final String INVESTOR_CLIENT = "investor";
  @Autowired
  private IntegratorClient    integratorClient;
  /**
   * @return
   * @throws ClientRegistrationException
   * @throws RestClientResponseException
   */
  public List<UserTokenPojo> retrieveUserTokens()
  {
    SecurityContext context = SecurityContextHolder.getContext();

    if (context != null)
    {
      OAuth2AuthorizedClient authorizedClient = (OAuth2AuthorizedClient)context.getAuthentication();

      if (authorizedClient == null
          || !authorizedClient.getClientRegistration().getClientId().equals(INVESTOR_CLIENT))
      {
        throw new AccessControlException("Unauthorized Investor access");
      }

      String accessToken = authorizedClient.getAccessToken().getTokenValue(); // Optional

       List<UserTokenPojo> response = integratorClient.getUserTokens(accessToken);

      return response;
    }
    else
      throw new AccessControlException("Unauthorized Investor access");

  }
}