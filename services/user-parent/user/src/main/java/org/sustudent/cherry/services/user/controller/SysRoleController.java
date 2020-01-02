package org.sustudent.cherry.services.user.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.web.BaseController;
import org.sustudent.cherry.services.user.entity.SysRole;
import org.sustudent.cherry.services.user.service.SysRoleService;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysRoleController.java
 * @Description SysRoleController
 * @createTime 2019/12/17/ 11:54:00
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

  @Autowired
  private SysRoleService sysRoleService;

  @GetMapping("/findPage")
  public ResponseResult findPage(HttpServletRequest request,SysRole sysRole) {
    Page<SysRole> page = getPage(request, SysRole.class);
    sysRoleService.findPage(page,sysRole);
    return ResponseResult.success(page);
  }

  @GetMapping("/checkField")
  public ResponseResult checkField(SysRole role){
    return success(sysRoleService.checkField(role));
  }

  @PostMapping("/save")
  public ResponseResult save(@RequestBody SysRole role){
    return success(sysRoleService.save(role));
  }

  @PostMapping("/delete")
  public ResponseResult delete(@RequestBody SysRole role){
    sysRoleService.delete(role);
    return success();
  }
}
