/**
 * 
 */
package com.example.demo.client.dto;

/**
 * 
 */
public class UserTokenPojo
{
  private String accessToken;
  private Long   id;
  private String refreshToken;

  /**
   * @param accessToken
   * @param id
   * @param refreshToken
   */
  public UserTokenPojo(Long id, String accessToken, String refreshToken)
  {
    super();
    this.accessToken = accessToken;
    this.id = id;
    this.refreshToken = refreshToken;
  }


  /**
   * @return the accessToken
   */
  public String getAccessToken()
  {
    return accessToken;
  }


  /**
   * @return the id
   */
  public Long getId()
  {
    return id;
  }


  /**
   * @return the refreshToken
   */
  public String getRefreshToken()
  {
    return refreshToken;
  }



}
