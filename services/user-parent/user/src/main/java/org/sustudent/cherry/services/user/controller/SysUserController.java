package org.sustudent.cherry.services.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.web.BaseController;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.services.user.entity.SysUser;
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
public class SysUserController extends BaseController {

  @Autowired
  private SysUserService sysUserService;


  @GetMapping("/findUserByUsername")
  public ResponseResult<CherryUser> findUserByUsername(@RequestParam String username) {
    return ResponseResult.success(sysUserService.findUserByUsername(username));
  }

  @GetMapping("/test01")
  public ResponseResult<String> test01() {
    return ResponseResult.success("test01");
  }


  @GetMapping("/test02")
  public ResponseResult<String> test02() {
    return ResponseResult.success("test02");
  }

  @PostMapping("/save")
  public ResponseResult save(@RequestBody SysUser user){
    return success(sysUserService.saveUser(user));
  }

  @GetMapping("/findPage")
  public ResponseResult findPage(SysUser user, HttpServletRequest request){
    Page<SysUser> page = getPage(request,SysUser.class);
    sysUserService.findPage(page,user);
    return success(page);
  }

  @PostMapping("/delete")
  public ResponseResult delete(@RequestBody SysUser user){
    sysUserService.delete(user);
    return success();
  }
}
