/**
 * 
 */
package com.example.demo.security.config.oauth2.client;


import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;


/**
 * 
 */
public interface OAuth2Provider
{
  Builder getBuilder(
    String registrationId);
}
