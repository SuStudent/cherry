package org.sustudent.cherry.services.user.security.access.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.model.CherryConfigAttribute;
import org.sustudent.cherry.services.user.security.access.provider.ResourceMetadataSourceProvider;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName ResourceSecurityMetadataSourceService.java
 * @Description ResourceSecurityMetadataSourceService
 * @createTime 2019/11/22/ 15:01:00
 */
@Service
public class ResourceMetadataSourceService {

  @Autowired
  private List<ResourceMetadataSourceProvider> providers;

  public Map<Long, Collection<CherryConfigAttribute>> getAttribute() {
    Map<Long, Collection<CherryConfigAttribute>> result = new HashMap<>();

    for (ResourceMetadataSourceProvider provider : providers) {
      Map<Long, Collection<CherryConfigAttribute>> attributes = provider.provide();
      if (!MapUtils.isEmpty(attributes)) {
        result = Stream.concat(result.entrySet().stream(), attributes.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (val1, val2) -> {
              val1.addAll(val2);
              return val1;
            }));
      }
    }
    return result;
  }
}
