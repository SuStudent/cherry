package org.sustudent.cherry.common.core.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sustudent.cherry.common.core.model.ResponseResult;

/**
 * 异常拦截器
 *
 * @author yiyi.su
 * @date 2019年4月29日
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseResult<?> exception(Exception ex) {
    ex.printStackTrace();
    return ResponseResult.error("服务器异常:" + ex.getMessage());
  }

  @ExceptionHandler(CommonException.class)
  public ResponseResult<?> commonException(CommonException ex) {
    ex.printStackTrace();
    return ResponseResult.error(ex.getMessage());
  }
}
