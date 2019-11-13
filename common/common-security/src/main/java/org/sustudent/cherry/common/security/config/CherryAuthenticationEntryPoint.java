package org.sustudent.cherry.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryAuthenticationEntryPoint.java
 * @Description CherryAuthenticationEntryPoint
 * @createTime 2019/10/21/ 14:34:00
 */
@Component
public class CherryAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    response.setContentType("application/json;charset=utf-8");
    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("msg", "请登录。");
    body.put("status", HttpStatus.UNAUTHORIZED.value());

    PrintWriter pw = response.getWriter();
    pw.write(new ObjectMapper().writeValueAsString(body));
    pw.flush();
    pw.close();

  }
}
