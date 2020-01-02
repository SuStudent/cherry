package org.sustudent.cherry.services.user.security;

import org.sustudent.cherry.services.user.entity.SysResource;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SecurityDecisionManager.java
 * @Description SecurityDecisionManager
 * @createTime 2019/11/22/ 14:44:00
 */
public interface SecurityDecisionManager {

  /**
   * 决策给定的资源，当前登陆用户是否有权限
   *
   * @param resource 资源
   * @return 结果。true为有权限，否则没有
   */
  boolean decide(SysResource resource);

  /**
   * 决策给定的资源，给定用户是否有权限
   *
   * @param username 用户名
   * @param resource 资源
   * @return 结果。true为有权限，否则没有
   */
  boolean decide(String username, SysResource resource);
}
