package com.example.log4jyml.controller;

import com.example.log4jyml.util.LogUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class IndexController {
  @GetMapping("/")
  public String helloworld() throws Exception {
    Logger log = LogUtils.getExceptionLogger();
    Logger log2 = LogUtils.getDBLogger();
    log.error("getExceptionLogger===日志测试");
    /*Logger log1 = LogUtils.getBussinessLogger();
    log1.info("getBussinessLogger===日志测试");*/
    log2.debug("getDBLogger===日志测试");
    return "helloworld";
  }
}
