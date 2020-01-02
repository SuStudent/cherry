package org.sustudent.cherry.services.user.service;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.cache.SysResourceCache;
import org.sustudent.cherry.services.user.entity.SysResource;
import org.sustudent.cherry.services.user.mapper.SysResourceMapper;
import org.sustudent.cherry.services.user.security.SecurityDecisionManager;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysResourceService.java
 * @Description SysResourceService
 * @createTime 2019/11/22/ 16:00:00
 */
@Service
public class SysResourceService extends BaseService<SysResourceMapper, SysResource> {

  @Autowired
  private SysResourceCache sysResourceCache;

  @Autowired
  private SecurityDecisionManager securityDecisionManager;


  public List<SysResource> findCurrentUserResource() {
    List<SysResource> resources = sysResourceCache.findAllSysResource();
    Iterator<SysResource> resourceIterator = resources.iterator();
    while (resourceIterator.hasNext()) {
      SysResource resource = resourceIterator.next();
      if (!securityDecisionManager.decide(resource)) {
        resourceIterator.remove();
      }
    }
    return resources;
  }
}
