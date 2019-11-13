package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.api.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {

  List<SysUser> findPage(@Param("page") Page<SysUser> page, @Param("user") SysUser user);
}
