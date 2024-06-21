package com.example.demo.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.config.oauth2.client.CustomOAuth2Provider;
import com.example.demo.security.oauth2.client.registration.Oauth2SecurityConfiguration;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends Oauth2SecurityConfiguration
{
  protected static final String INTEGRATOR_CLIENT = "integrator";

  @Bean
  SecurityFilterChain securityFilterChain(
    HttpSecurity http) throws Exception
  {
    http.authorizeHttpRequests(authorizeRequests -> authorizeRequests//
        .anyRequest().authenticated())//
        .oauth2Login(oauth2Login -> oauth2Login.authorizationEndpoint(
          authorizationEndpoint -> authorizationEndpoint.authorizationRequestResolver(
            customAuthorizationRequestResolver(clientRegistrationRepository()))));
    return http.build();
  }


  @Bean
  public OAuth2AuthorizationRequestResolver customAuthorizationRequestResolver(
    ClientRegistrationRepository clientRegistrationRepository)
  {
    DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(
        clientRegistrationRepository, "/oauth2/authorization");
    
    resolver
        .setAuthorizationRequestCustomizer(customizer -> customizer.additionalParameters(params -> {
          // Customize additional parameters if needed
        }));
    
    return resolver;
  }


  @Override
  protected ClientRegistration getCustomClientRegistration(
    String client,
    String clientId,
    String clientSecret,
    String[] clientScopes)
  {
    if (client.equals(INTEGRATOR_CLIENT))
    {
      return CustomOAuth2Provider.INTEGRATOR.getBuilder(clientId)//
          .clientId(clientId)//
          .clientSecret(clientSecret)//
          .scope(clientScopes)//
          .build();
    }
    else
      return null;
  }

}
