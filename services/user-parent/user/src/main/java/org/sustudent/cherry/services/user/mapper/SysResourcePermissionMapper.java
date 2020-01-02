package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.entity.SysResourcePermission;
import org.sustudent.cherry.services.user.enums.ResourceType;

public interface SysResourcePermissionMapper extends BaseMapper<SysResourcePermission> {

  List<SysResourcePermission> findConfigAttributes(
      @Param("resourceType") ResourceType resourceType);
}
