package org.sustudent.cherry.services.auth.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxProperties.java
 * @Description WxProperties
 * @createTime 2020/06/06/ 17:45:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxProperties {

  /**
   * 设置微信小程序的appid
   */
  private String appid;

  /**
   * 设置微信小程序的Secret
   */
  private String secret;
}
