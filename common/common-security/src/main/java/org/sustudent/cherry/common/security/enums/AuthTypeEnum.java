package org.sustudent.cherry.common.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 第三方登录类型
 */
@Getter
@AllArgsConstructor
public enum AuthTypeEnum {

  WX_MINIAPP("wx_miniapp", "微信登录"),

  PASSWORD("PASSWORD", "密码登录");
  
  /**
   * AuthType类型
   */
  private String authType;

  /**
   * 描述
   */
  private String description;

}
