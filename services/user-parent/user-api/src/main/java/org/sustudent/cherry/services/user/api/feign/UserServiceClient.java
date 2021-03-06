package org.sustudent.cherry.services.user.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.sustudent.cherry.common.core.constant.ServiceContants;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.security.wx.WxUser;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName UserServiceClient.java
 * @Description UserServiceClient
 * @createTime 2019/10/20/ 18:32:00
 */
@FeignClient(value = ServiceContants.USER_SERVICE)
public interface UserServiceClient {


  @GetMapping("/sysUser/findUserByUsername")
  ResponseResult<CherryUser> findUserByUsername(@RequestParam String username);

  @PostMapping("/sysUser/findWxUserAndSave")
  ResponseResult<CherryUser> findWxUserAndSave(@RequestBody WxUser wxUser);

  @GetMapping("/sysUser/findUserById")
  ResponseResult<CherryUser> findUserById(@RequestBody Long userId);
}
