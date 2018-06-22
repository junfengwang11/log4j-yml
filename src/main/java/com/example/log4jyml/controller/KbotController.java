package com.example.log4jyml.controller;

import org.springframework.web.client.RestTemplate;

public class KbotController {
  public static void main(String[] args) {
    //
      RestTemplate restTemplate =new RestTemplate();
    for (int i = 0; i < 10000; i++) {
          //
          restTemplate.getForObject("http://localhost:8080/cust/check/18520174777",Object.class);
      }
  }
}
