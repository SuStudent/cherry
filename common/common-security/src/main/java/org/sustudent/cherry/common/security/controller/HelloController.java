package org.sustudent.cherry.common.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName HelloController.java
 * @Description HelloController
 * @createTime 2020/06/02/ 11:42:00
 */
@RestController
@RequestMapping("hello")
public class HelloController {

  @GetMapping("")
  public String hello() {
    return "hello Auth";
  }
}
