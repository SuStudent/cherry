package org.sustudent.cherry.services.user.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sustudent.cherry.common.core.constant.ServiceContants;
import org.sustudent.cherry.common.core.model.CherryUser;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.services.user.api.entity.SysUser;

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
  ResponseResult<SysUser> findUserByUsername(@RequestParam String username);



  @GetMapping("/sysUser/test")
  ResponseResult<String> test();
}
