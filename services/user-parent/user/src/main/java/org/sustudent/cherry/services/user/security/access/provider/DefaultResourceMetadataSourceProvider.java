package org.sustudent.cherry.services.user.security.access.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.model.CherryConfigAttribute;
import org.sustudent.cherry.services.user.entity.SysResourcePermission;
import org.sustudent.cherry.services.user.service.SysResourcePermissionService;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName DefaultResourceMetadataSourceProvider.java
 * @Description DefaultResourceMetadataSourceProvider
 * @createTime 2019/11/22/ 15:05:00
 */
@Service
public class DefaultResourceMetadataSourceProvider implements ResourceMetadataSourceProvider {

  @Autowired
  private SysResourcePermissionService resourcePermissionService;

  @Override
  public Map<Long, Collection<CherryConfigAttribute>> provide() {
    List<SysResourcePermission> resourcePermissions = resourcePermissionService
        .findConfigAttributes(null);

    // list -> map id相同 value相加
    Map<Long, Collection<CherryConfigAttribute>> result =
        resourcePermissions.stream().collect(
            Collectors.toMap(SysResourcePermission::getResourceId, v -> {
              Collection<CherryConfigAttribute> values = new HashSet<CherryConfigAttribute>();
              CherryConfigAttribute cherryConfigAttribute = new CherryConfigAttribute();
              cherryConfigAttribute.setAttribute(v.getRoleAttribute());
              values.add(cherryConfigAttribute);
              return values;
            }, (val1, val2) -> {
              val1.addAll(val2);
              return val1;
            }));

    return result;
  }
}
