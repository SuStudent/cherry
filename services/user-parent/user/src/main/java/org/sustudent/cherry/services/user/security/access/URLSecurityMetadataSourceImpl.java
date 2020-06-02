package org.sustudent.cherry.services.user.security.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.common.security.config.URLSecurityMetadataSource;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName URLSecurityMetadataSourceImpl.java
 * @Description URLSecurityMetadataSourceImpl
 * @createTime 2019/11/21/ 14:25:00
 */
@Component
public class URLSecurityMetadataSourceImpl implements URLSecurityMetadataSource {

  private Map<String, Collection<ConfigAttribute>> ConfigAttributeMap;


  @Override
  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
    // 获取当前访问url
    String url = ((FilterInvocation) object).getRequestUrl();
    url = StringUtils.substringBefore(url, "?");
    if (ConfigAttributeMap == null) {
      getAllConfigAttributes();
    }

    return ConfigAttributeMap.get(url);
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    ConfigAttributeMap = new HashMap<>();
    ConfigAttributeMap.put("/sysUser/test01",
        org.springframework.security.access.SecurityConfig.createList("test01"));
    ConfigAttributeMap.put("/sysUser/test02",
        org.springframework.security.access.SecurityConfig.createList("test02"));

    Collection<ConfigAttribute> result = new ArrayList<>();
    for (Collection<ConfigAttribute> value : ConfigAttributeMap.values()) {
      result.addAll(value);
    }
    return result;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return FilterInvocation.class.isAssignableFrom(clazz);
  }
}
