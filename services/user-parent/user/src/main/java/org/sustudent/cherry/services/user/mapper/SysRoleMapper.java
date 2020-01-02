package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {

  List<SysRole> findPage(@Param("page") Page<SysRole> page, @Param("role") SysRole role);

  boolean checkField(SysRole role);
}
