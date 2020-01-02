package org.sustudent.cherry.services.user.security.access.provider;

import java.util.Collection;
import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.sustudent.cherry.common.security.model.CherryConfigAttribute;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName ResourceMetadataSourceProvider.java
 * @Description ResourceMetadataSourceProvider
 * @createTime 2019/11/22/ 15:03:00
 */
public interface ResourceMetadataSourceProvider {

  Map<Long, Collection<CherryConfigAttribute>> provide();
}
