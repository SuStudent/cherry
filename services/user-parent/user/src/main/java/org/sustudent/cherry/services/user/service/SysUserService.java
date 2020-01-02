package org.sustudent.cherry.services.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.services.user.cache.SysUserCache;
import org.sustudent.cherry.services.user.entity.SysUser;
import org.sustudent.cherry.services.user.mapper.SysUserMapper;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUserService.java
 * @Description SysUserService
 * @createTime 2019/10/20/ 19:02:00
 */
@Service
@Transactional
public class SysUserService extends BaseService<SysUserMapper, SysUser> {

  @Autowired
  private SysUserCache sysUserCache;

  public CherryUser findUserByUsername(String username) {
    return sysUserCache.findUserByUsername(username);
  }

  public SysUser saveUser(SysUser user) {
    saveOrUpdate(user);
    return user;
  }

  public void findPage(Page<SysUser> page, SysUser user) {
    mapper.findPage(page, user);
  }
}


