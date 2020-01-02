package org.sustudent.cherry.services.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.security.utils.ContextUtils;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description UserController
 * @createTime 2019/11/19/ 16:23:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/info")
  public ResponseResult<CherryUser> info() {
    return ResponseResult.success(ContextUtils.getLoginUser());
  }
}
