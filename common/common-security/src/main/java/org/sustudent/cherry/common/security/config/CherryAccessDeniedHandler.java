package org.sustudent.cherry.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryAccessDeniedHandler.java
 * @Description CherryAccessDeniedHandler
 * @createTime 2019/10/21/ 14:34:00
 */
public class CherryAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setContentType("application/json;charset=UTF-8");
    Map<String, Object> body = new HashMap<>();
    body.put("success", false);
    body.put("msg", "没有权限访问。");
    body.put("status", HttpStatus.FORBIDDEN.value());
    PrintWriter pw = response.getWriter();
    pw.write(new ObjectMapper().writeValueAsString(body));
    pw.flush();
    pw.close();

  }

}
