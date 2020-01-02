package org.sustudent.cherry.services.user.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.entity.SysResourcePermission;
import org.sustudent.cherry.services.user.enums.ResourceType;
import org.sustudent.cherry.services.user.mapper.SysResourcePermissionMapper;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysResourcePermissionService.java
 * @Description SysResourcePermissionService
 * @createTime 2019/11/22/ 15:18:00
 */
@Service
public class SysResourcePermissionService extends BaseService<SysResourcePermissionMapper, SysResourcePermission> {

  public List<SysResourcePermission> findConfigAttributes(ResourceType resourceType) {
    return mapper.findConfigAttributes(resourceType);
  }
}
