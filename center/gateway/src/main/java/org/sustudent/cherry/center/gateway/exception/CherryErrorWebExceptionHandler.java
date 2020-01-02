package org.sustudent.cherry.center.gateway.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryErrorWebExceptionHandler.java
 * @Description CherryErrorWebExceptionHandler
 * @createTime 2019/12/13/ 13:25:00
 */
@Slf4j
public class CherryErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

  /**
   * Create a new {@code DefaultErrorWebExceptionHandler} instance.
   *
   * @param errorAttributes the error attributes
   * @param resourceProperties the resources configuration properties
   * @param errorProperties the error configuration properties
   * @param applicationContext the current application context
   */
  public CherryErrorWebExceptionHandler(
      ErrorAttributes errorAttributes,
      ResourceProperties resourceProperties,
      ErrorProperties errorProperties,
      ApplicationContext applicationContext) {
    super(errorAttributes, resourceProperties, errorProperties, applicationContext);
  }

  @Override
  protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
    boolean includeStackTrace = super.isIncludeStackTrace(request, MediaType.ALL);
    Map<String, Object> error = super.getErrorAttributes(request, includeStackTrace);
    log.error("网关调用异常：{}", error);
    return ServerResponse.status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(this.convertError(error)));
  }

  private Map<String, Object> convertError(Map<String, Object> error) {
    error.put("success", false);
    error.put("msg", error.remove("message"));
    error.remove("path");
    error.remove("error");
    error.remove("timestamp");
    return error;
  }
}
