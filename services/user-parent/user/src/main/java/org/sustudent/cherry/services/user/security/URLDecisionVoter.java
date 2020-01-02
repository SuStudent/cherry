package org.sustudent.cherry.services.user.security;

import java.util.Collection;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName URLDecisionVoter.java
 * @Description URLDecisionVoter
 * @createTime 2019/11/21/ 14:39:00
 */
public class URLDecisionVoter implements AccessDecisionVoter<Object> {

  @Override
  public boolean supports(ConfigAttribute attribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return true;
  }

  @Override
  public int vote(Authentication authentication, Object object,
      Collection<ConfigAttribute> attributes) {
    if (authentication == null) {
      return ACCESS_DENIED;
    }
    int result = ACCESS_ABSTAIN;
    Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);

    for (ConfigAttribute attribute : attributes) {
      if (this.supports(attribute)) {
        result = ACCESS_DENIED;

        for (GrantedAuthority authority : authorities) {
          if (attribute.getAttribute().equals(authority.getAuthority())) {
            return ACCESS_GRANTED;
          }
        }
      }
    }
    return result;
  }

  Collection<? extends GrantedAuthority> extractAuthorities(
      Authentication authentication) {
    return authentication.getAuthorities();
  }
}
