/**
 * 
 */
package com.example.demo.security.config.oauth2.client;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

/**
 * 
 */
public enum CustomOAuth2Provider implements OAuth2Provider {
  INTEGRATOR;
  
  private static final String DEFAULT_REDIRECT_URL                = "{baseUrl}/{action}/oauth2/code/{registrationId}";
  private static final String INTEGRATOR_AUTHORIZATION_URI        = "https://localhost:8888/demo/oauth2/auth";
  private static final String INTEGRATOR_TOKEN_URI                = "https://localhost:8888/demo/oauth2/token";
  private static final String INTEGRATOR_USER_INFO_URI            = "https://localhost:8888/demo/v2/user";
  private static final String INTEGRATOR_USER_NAME_ATTRIBUTE_NAME = "name";
  private static final String INTEGRATOR_CLIENT_NAME              = "Coinbase";

  /**
   *
  * @param registrationId
   */
  @Override
  public Builder getBuilder(
    String registrationId)
  {
    String redirectUri = DEFAULT_REDIRECT_URL.replace("{action}", "login");
    ClientRegistration.Builder builder = getBuilder(registrationId,
      ClientAuthenticationMethod.CLIENT_SECRET_BASIC, redirectUri)//
          .authorizationUri(INTEGRATOR_AUTHORIZATION_URI)//
          .tokenUri(INTEGRATOR_TOKEN_URI)//
          .userInfoUri(INTEGRATOR_USER_INFO_URI)//
          .userNameAttributeName(INTEGRATOR_USER_NAME_ATTRIBUTE_NAME)//
          .clientName(INTEGRATOR_CLIENT_NAME);
    
    return builder;
  }

  /**
   * @param registrationId
   * @param method
   * @param redirectUri
   * @return
   */
  protected final ClientRegistration.Builder getBuilder(
    String registrationId,
    ClientAuthenticationMethod method,
    String redirectUri)
  {
    ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId)//
        .clientAuthenticationMethod(method)//
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)//
        .redirectUri(redirectUri);

    return builder;
  }

}
