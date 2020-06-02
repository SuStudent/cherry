package org.sustudent.cherry.common.security.config;

import java.util.List;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;

/**
 * 授权决策
 *
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryAccessDecisionManager.java
 * @Description CherryAccessDecisionManager
 * @createTime 2019/11/22/ 13:29:00
 */
public abstract class CherryAccessDecisionManager extends AbstractAccessDecisionManager {


  protected CherryAccessDecisionManager(
      List<AccessDecisionVoter<?>> decisionVoters) {
    super(decisionVoters);
  }
}
