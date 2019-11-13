package org.sustudent.cherry.services.auth.controller;


import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.utils.ContextUtils;
import org.sustudent.cherry.services.user.api.feign.UserServiceClient;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserServiceClient userServiceClient;

  @GetMapping(value = "/details")
  public ResponseResult userDetails(Principal principal, HttpServletRequest request){
    ResponseResult<String> result = userServiceClient.test();

    System.out.println(result.getData());


    return ResponseResult.success(ContextUtils.getLoginUser());
  }


  @GetMapping(value = "/demo")
  public ResponseResult demo(){
    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return ResponseResult.success(ContextUtils.getLoginUser());
  }
}
