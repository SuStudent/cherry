package org.sustudent.cherry.services.user.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.sustudent.cherry.common.security.config.CherryAccessDecisionManager;
import org.sustudent.cherry.services.user.security.decision.CherryAccessDecisionManagerImpl;
import org.sustudent.cherry.services.user.security.URLDecisionVoter;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AccessDecisionManagerConfig.java
 * @Description AccessDecisionManagerConfig
 * @createTime 2019/11/22/ 13:41:00
 */
@Configuration
public class AccessDecisionManagerConfig {

  @Bean
  public CherryAccessDecisionManager accessDecisionManager() {
    List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<>();
    decisionVoters.add(new URLDecisionVoter());
    CherryAccessDecisionManager accessDecisionManager = new CherryAccessDecisionManagerImpl(
        decisionVoters);
    accessDecisionManager.setAllowIfAllAbstainDecisions(true);
    return accessDecisionManager;
  }
}
