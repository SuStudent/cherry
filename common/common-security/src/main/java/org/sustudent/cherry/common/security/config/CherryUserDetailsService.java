package org.sustudent.cherry.common.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.sustudent.cherry.common.security.wx.WxClientUserInfo;
import org.sustudent.cherry.common.security.wx.WxUser;

public abstract class CherryUserDetailsService implements UserDetailsService {

  public UserDetails loadUserByWxCode(String code, WxClientUserInfo wxClientUserInfo)
      throws UsernameNotFoundException{
    throw new RuntimeException("loadUserByWxCode 方法不支持");
  }

}
