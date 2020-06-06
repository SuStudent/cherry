package org.sustudent.cherry.services.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.common.security.enums.AuthTypeEnum;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.security.wx.WxUser;
import org.sustudent.cherry.services.user.cache.SysUserCache;
import org.sustudent.cherry.services.user.entity.SysUser;
import org.sustudent.cherry.services.user.entity.WeChartUser;
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

  @Autowired
  private WeChartUserService weChartUserService;

  public CherryUser findUserByUsername(String username) {
    return sysUserCache.findUserByUsername(username);
  }

  @CacheEvict(value = "sysUser", key = "'findUserByUsername::' + #user.username")
  public SysUser saveUser(SysUser user) {
    saveOrUpdate(user);
    return user;
  }

  public void findPage(Page<SysUser> page, SysUser user) {
    mapper.findPage(page, user);
  }

  public CherryUser findWxUserAndSave(WxUser wxUser) {
    WeChartUser weChartUser = weChartUserService
        .getByOpenIdAndUnionId(wxUser.getOpenId(), wxUser.getUnionId());

    if (weChartUser != null) {
      SysUser sysUser = super.getById(weChartUser.getSysUserId());
      return sysUserCache.buildUser(sysUser);
    }
    SysUser sysUser = saveFromWxUser(wxUser);
    weChartUserService.saveFromWxUser(wxUser, sysUser);
    CherryUser cherryUser = sysUserCache.buildUser(sysUser);
    cherryUser.setAuthType(AuthTypeEnum.WX_MINIAPP);
    return cherryUser;
  }

  public SysUser saveFromWxUser(WxUser wxUser) {
    SysUser user = new SysUser();
    user.setAccountNonExpired(true);
    user.setAccountNonLocked(true);
    user.setEnabled(true);
    user.setUsername(wxUser.getOpenId());
    user.setRealName(wxUser.getNickName());
    user.setPhoto(wxUser.getAvatarUrl());
    return saveUser(user);
  }
}


