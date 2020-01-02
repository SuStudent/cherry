package org.sustudent.cherry.services.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.web.BaseController;
import org.sustudent.cherry.services.user.entity.SysResource;
import org.sustudent.cherry.services.user.entity.SysRole;
import org.sustudent.cherry.services.user.enums.ResourceType;
import org.sustudent.cherry.services.user.service.SysResourceService;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysResourceController.java
 * @Description SysResourceController
 * @createTime 2019/11/22/ 15:56:00
 */
@RestController
@RequestMapping("/sysResource")
public class SysResourceController extends BaseController {

  @Autowired
  private SysResourceService resourceService;

  @GetMapping("/findCurrentUserResource")
  public ResponseResult<List<SysResource>> findCurrentUserResource(){
    return ResponseResult.success(resourceService.findCurrentUserResource());
  }
}
