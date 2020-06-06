package org.sustudent.cherry.services.auth.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxConfig.java
 * @Description WxConfig
 * @createTime 2020/06/06/ 17:46:00
 */
@Configuration
public class WxConfig {

  @Autowired
  private WxProperties wxProperties;

  @Bean
  public WxMaService wxMaService() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    config.setAppid(wxProperties.getAppid());
    config.setSecret(wxProperties.getSecret());
    WxMaService service = new WxMaServiceImpl();
    service.setWxMaConfig(config);
    return service;
  }
}
