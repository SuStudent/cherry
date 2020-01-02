package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.entity.SysUserPermission;

public interface SysUserPermissionMapper extends BaseMapper<SysUserPermission> {


  List<SysUserPermission> findUserPermission(Long userId);
}
