package org.sustudent.cherry.common.security.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.common.security.model.CherryUser;

@Component
public class ContextUtils implements ApplicationContextAware {

  // Spring应用上下文环境
  private static ApplicationContext applicationContext;

  // 虚拟的登录用户，线程私有
  private static final ThreadLocal<CherryUser> LOCAL_USER = new ThreadLocal<CherryUser>();

  public static void mockLoginUser(CherryUser cherryUser) {
    LOCAL_USER.set(cherryUser);
  }

  public static void removeMockLoginUser(CherryUser cherryUser) {
    LOCAL_USER.remove();
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    ContextUtils.applicationContext = applicationContext;
  }

  public static Object getBean(String name) {
    return applicationContext.getBean(name);
  }

  public static CherryUser getLoginUser() {
    CherryUser user = LOCAL_USER.get();
    if (user != null) {
      return user;
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof CherryUser) {
      return (CherryUser) authentication.getPrincipal();
    }
    return null;
  }

  public static String getLoginUsername() {
    CherryUser user = getLoginUser();
    if (user != null) {
      return user.getUsername();
    }
    return null;
  }

  public static Long getLoginUserId() {
    CherryUser user = getLoginUser();
    if (user != null) {
      return user.getId();
    }
    return null;
  }
}
