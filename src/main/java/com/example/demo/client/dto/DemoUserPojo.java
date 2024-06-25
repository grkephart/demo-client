/**
 * 
 */
package com.example.demo.client.dto;


/**
 * 
 */
public class DemoUserPojo
{
  private String accessToken;
  private Long   id;

  private String internalId;

  private String name;
  private String refreshToken;
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
   * @return the internalId
   */
  public String getInternalId()
  {
    return internalId;
  }


  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }


  /**
   * @return the refreshToken
   */
  public String getRefreshToken()
  {
    return refreshToken;
  }


  /**
   * @param accessToken the accessToken to set
   */
  public void setAccessToken(
    String accessToken)
  {
    this.accessToken = accessToken;
  }


  /**
   * @param id the id to set
   */
  public void setId(
    Long id)
  {
    this.id = id;
  }


  /**
   * @param internalId the internalId to set
   */
  public void setInternalId(
    String internalId)
  {
    this.internalId = internalId;
  }


  /**
   * @param name the name to set
   */
  public void setName(
    String name)
  {
    this.name = name;
  }


  /**
   * @param refreshToken the refreshToken to set
   */
  public void setRefreshToken(
    String refreshToken)
  {
    this.refreshToken = refreshToken;
  }

}
