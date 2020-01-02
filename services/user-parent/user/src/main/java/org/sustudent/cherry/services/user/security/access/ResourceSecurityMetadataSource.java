package org.sustudent.cherry.services.user.security.access;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.model.CherryConfigAttribute;
import org.sustudent.cherry.services.user.entity.SysResource;
import org.sustudent.cherry.services.user.security.access.service.ResourceMetadataSourceService;

/**
 * 系统资源元数据
 *
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName ResourceSecurityMetadataSource.java
 * @Description ResourceSecurityMetadataSource
 * @createTime 2019/11/22/ 14:50:00
 */
@Service
public class ResourceSecurityMetadataSource implements SecurityMetadataSource {

  @Autowired
  private ResourceMetadataSourceService resourceMetadataSourceService;

  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    SysResource resource = (SysResource) object;
    Collection<CherryConfigAttribute> configAttributes = resourceMetadataSourceService
        .getAttribute().get(resource.getId());

    if(configAttributes == null){
      return new ArrayList<>();
    }
    return new ArrayList<>(configAttributes);
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return new ArrayList(resourceMetadataSourceService.getAttribute().values());
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return SysResource.class.isAssignableFrom(clazz);
  }
}
