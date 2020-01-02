package org.sustudent.cherry.services.user.security.decision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.sustudent.cherry.services.user.security.SecurityDecisionManager;
import org.sustudent.cherry.services.user.entity.SysResource;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SecurityDecisionManagerImpl.java
 * @Description SecurityDecisionManagerImpl
 * @createTime 2019/11/22/ 14:46:00
 */
@Service
public class SecurityDecisionManagerImpl implements SecurityDecisionManager {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private List<SecurityMetadataSource> securityMetadataSources;

  @Autowired
  private AccessDecisionManager accessDecisionManager;

  @Override
  public boolean decide(SysResource resource) {
    if (resource == null) {
      return true;
    }

    if(resource.getId() == null || resource.getId() == 0L) {
      return true;
    }
    return decide(null, resource);
  }

  @Override
  public boolean decide(String username, SysResource resource) {
    if (resource == null) {
      return true;
    }
    if(resource.getId() == null || resource.getId() == 0L) {
      return true;
    }
    Collection<ConfigAttribute> attributes = this.findConfigAttributes(resource);
    Authentication authentication = this.getAuthentication(username);
    try {
      accessDecisionManager.decide(authentication, resource, attributes);
    } catch (AccessDeniedException | InsufficientAuthenticationException e) {
      return false;
    }
    return true;
  }

  protected Authentication getAuthentication(String username) {
    if (username != null) {
      UserDetails user = userDetailsService.loadUserByUsername(username);
      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }
    return SecurityContextHolder.getContext().getAuthentication();
  }

  protected Collection<ConfigAttribute> findConfigAttributes(SysResource resource) {
    Collection<ConfigAttribute> attributes = new ArrayList<>();
    if (CollectionUtils.isEmpty(attributes)) {
      for (SecurityMetadataSource securityMetadataSource : securityMetadataSources) {
        if (securityMetadataSource.supports(resource.getClass())) {
          attributes = securityMetadataSource.getAttributes(resource);
        }
      }
    }
    return attributes == null ? new ArrayList<>(0) : attributes;
  }
}
