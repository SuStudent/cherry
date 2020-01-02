package org.sustudent.cherry.services.user.security.decision;

import java.util.Collection;
import java.util.List;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.sustudent.cherry.common.security.config.CherryAccessDecisionManager;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryAccessDecisionManagerImpl.java
 * @Description CherryAccessDecisionManagerImpl
 * @createTime 2019/11/22/ 13:35:00
 */
public class CherryAccessDecisionManagerImpl extends CherryAccessDecisionManager {

  public CherryAccessDecisionManagerImpl(
      List<AccessDecisionVoter<?>> decisionVoters) {
    super(decisionVoters);
  }

  @Override
  public void decide(Authentication authentication, Object object,
      Collection<ConfigAttribute> configAttributes)
      throws AccessDeniedException, InsufficientAuthenticationException {
    int deny = 0;

    for (AccessDecisionVoter voter : getDecisionVoters()) {
      int result = voter.vote(authentication, object, configAttributes);

      if (logger.isDebugEnabled()) {
        logger.debug("Voter: " + voter + ", returned: " + result);
      }

      switch (result) {
        case AccessDecisionVoter.ACCESS_GRANTED:
          return;

        case AccessDecisionVoter.ACCESS_DENIED:
          deny++;

          break;

        default:
          break;
      }
    }

    if (deny > 0) {
      throw new AccessDeniedException(messages.getMessage(
          "AbstractAccessDecisionManager.accessDenied", "Access is denied"));
    }

    // To get this far, every AccessDecisionVoter abstained
    checkAllowIfAllAbstainDecisions();
  }
}
