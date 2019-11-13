package org.sustudent.cherry.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.sustudent.cherry.common.security.serializer.CherryOauthExceptionSerializer;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName 自定义Oauth认证异常
 * @Description CherryOauthException
 * @createTime 2019/10/21/ 13:28:00
 */
@Data
@JsonSerialize(using = CherryOauthExceptionSerializer.class)
public class CherryOauthException extends OAuth2Exception {

  /**
   * 错误代码
   */
  private int code;

  public CherryOauthException(String msg, int code) {
    super(msg);
    this.code = code;
  }
}
