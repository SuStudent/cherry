package org.sustudent.cherry.services.user.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.core.exception.CommonException;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.entity.SysRole;
import org.sustudent.cherry.services.user.entity.SysUser;
import org.sustudent.cherry.services.user.mapper.SysRoleMapper;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysRoleService.java
 * @Description SysUserService
 * @createTime 2019/10/20/ 19:02:00
 */
@Service
public class SysRoleService extends BaseService<SysRoleMapper, SysRole> {

  public void findPage(Page<SysRole> page, SysRole sysRole) {
    mapper.findPage(page, sysRole);
  }

  public boolean checkField(SysRole role) {
    return mapper.checkField(role);
  }

  private boolean checkName(Long id, String name) {
    SysRole role = new SysRole();
    role.setId(id);
    role.setName(name);
    return checkField(role);
  }

  private boolean checkAttribute(Long id, String attribute) {
    SysRole role = new SysRole();
    role.setId(id);
    role.setAttribute(attribute);
    return checkField(role);
  }

  public SysRole save(SysRole role) {
    String name = role.getName();
    String attribute = role.getAttribute();
    if (StringUtils.isAnyBlank(name, attribute)) {
      throw new CommonException("名称和属性不能为空。");
    }
    if (!checkName(role.getId(), name)) {
      throw new CommonException("名称已存在。");
    }
    if (!checkAttribute(role.getId(), attribute)) {
      throw new CommonException("名称已存在。");
    }
    saveOrUpdate(role);
    return role;
  }


}
