package org.sustudent.cherry.services.pipi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.web.BaseController;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName DynamicRefreshConfigController.java
 * @Description DynamicRefreshConfigController
 * @createTime 2020/08/24/ 11:22:00
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class DynamicRefreshConfigController extends BaseController {

  @Value("${test_config:}")
  private String testConfig;

  @GetMapping("/test")
  public ResponseResult<String> test() {
    return success(testConfig);
  }
}
