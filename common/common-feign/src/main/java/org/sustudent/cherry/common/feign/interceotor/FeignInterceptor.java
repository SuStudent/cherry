package org.sustudent.cherry.common.feign.interceotor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignInterceptor implements RequestInterceptor {

  private static final String TOKEN_HEADER = "Authorization";


  @Override
  public void apply(RequestTemplate requestTemplate) {
    HttpServletRequest request = getHttpServletRequest();
    if (request != null) {
      getHeaders(request).forEach((k,v) -> {
        if(k.equalsIgnoreCase(TOKEN_HEADER)) {
          requestTemplate.header(TOKEN_HEADER, v);
        }
      });


    }
  }

  private HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

  }

  private Map<String, String> getHeaders(HttpServletRequest request) {
    Map<String, String> map = new LinkedHashMap<>();
    Enumeration<String> enumeration = request.getHeaderNames();
    while (enumeration.hasMoreElements()) {
      String key = enumeration.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }
    return map;
  }
}
