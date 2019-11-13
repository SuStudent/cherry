package org.sustudent.cherry.services.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.model.CherryUser;
import org.sustudent.cherry.common.core.utils.ContextUtils;
import org.sustudent.cherry.services.user.api.entity.SysUser;
import org.sustudent.cherry.services.user.service.SysUserService;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUserController.java
 * @Description SysUserController
 * @createTime 2019/10/20/ 18:59:00
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

  @Autowired
  private SysUserService sysUserService;


  @GetMapping("/findUserByUsername")
  public ResponseResult<SysUser> findUserByUsername(@RequestParam String username){
    return ResponseResult.success(sysUserService.findUserByUsername(username));
  }

  @GetMapping("/test")
  public ResponseResult<SysUser> test(HttpServletRequest httpServletRequest,
      OAuth2Authentication authentication){
    return ResponseResult.success((SysUser)authentication.getPrincipal());
  }

  @GetMapping("/select")
  public ResponseResult<Page<SysUser>> select(@RequestParam int pageNo){
    Page<SysUser> page = new Page<>(10,pageNo);
    sysUserService.findPage(page);
    return ResponseResult.success(page);
  }

  @PostMapping("save")
  public ResponseResult save(@RequestParam SysUser user){
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    String str = encoder.encode("123456");
    for (int i = 0; i < 50; i++) {
      SysUser user1 = new SysUser();
      user1.setUsername("test" + i);
      user1.setRealName("test" + i);
      user1.setPassword(str);
      sysUserService.saveOrUpdate(user1);
    }

    return ResponseResult.success();
  }
}
