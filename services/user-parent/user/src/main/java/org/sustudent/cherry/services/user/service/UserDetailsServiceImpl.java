package org.sustudent.cherry.services.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.config.CherryUserDetailsService;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName UserDetailsServiceImpl.java
 * @Description UserDetailsServiceImpl
 * @createTime 2019/11/11/ 16:16:00
 */
@Service
public class UserDetailsServiceImpl extends CherryUserDetailsService {

  @Autowired
  private SysUserService sysUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return sysUserService.findUserByUsername(username);
  }
}
