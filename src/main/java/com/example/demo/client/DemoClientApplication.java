package com.example.demo.client;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.client.service.impl.MySecretAiStrategy;


@SpringBootApplication
public class DemoClientApplication
{
  @Autowired
  private MySecretAiStrategy mySecretAiStrategy;

  /**
   * @param args
   */
  public static void main(
    String[] args)
  {
    SpringApplication.run(DemoClientApplication.class, args);
  }


  /**
   * 
   */
  @PostConstruct
  public void executeStrategies()
  {
    this.mySecretAiStrategy.execute();
  }
}
