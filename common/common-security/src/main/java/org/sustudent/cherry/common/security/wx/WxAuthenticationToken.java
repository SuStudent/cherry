package org.sustudent.cherry.common.security.wx;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxAuthenticationToken.java
 * @Description WxAuthenticationToken
 * @createTime 2020/06/06/ 14:55:00
 */
public class WxAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  // ~ Instance fields
  // ================================================================================================

  private final Object principal;

  private WxClientUserInfo wxClientUserInfo;


  public WxAuthenticationToken(Object principal) {
    super(null);
    this.principal = principal;
    setAuthenticated(false);
  }

  public WxAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    super.setAuthenticated(true); // must use super, as we override
  }

  // ~ Methods
  // ========================================================================================================

  public Object getCredentials() {
    return null;
  }

  public Object getPrincipal() {
    return this.principal;
  }

  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    if (isAuthenticated) {
      throw new IllegalArgumentException(
          "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }

    super.setAuthenticated(false);
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
  }

  public WxClientUserInfo getWxClientUserInfo() {
    return wxClientUserInfo;
  }

  public void setWxClientUserInfo(
      WxClientUserInfo wxClientUserInfo) {
    this.wxClientUserInfo = wxClientUserInfo;
  }
}
