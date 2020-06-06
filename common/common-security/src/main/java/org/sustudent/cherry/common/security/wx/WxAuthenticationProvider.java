package org.sustudent.cherry.common.security.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.config.CherryUserDetailsService;
import org.sustudent.cherry.common.security.utils.ContextUtils;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxAuthenticationProvider.java
 * @Description WxAuthenticationProvider
 * @createTime 2020/06/06/ 15:00:00
 */
@Slf4j
@Service
public class WxAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private CherryUserDetailsService cherryUserDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    WxAuthenticationToken wxAuthenticationToken = (WxAuthenticationToken) authentication;
    String code = wxAuthenticationToken.getPrincipal().toString();

    UserDetails userDetails = cherryUserDetailsService
        .loadUserByWxCode(code, wxAuthenticationToken.getWxClientUserInfo());

    if (userDetails == null) {
      log.debug("Authentication failed: no credentials provided");
      throw new BadCredentialsException("code 无效");
    }
    WxAuthenticationToken authenticationToken = new WxAuthenticationToken(userDetails,
        userDetails.getAuthorities());
    authenticationToken.setDetails(wxAuthenticationToken.getDetails());
    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (WxAuthenticationToken.class.isAssignableFrom(authentication));
  }
}
