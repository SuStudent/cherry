package org.sustudent.cherry.services.auth.conf;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.JsonUtils;
import io.micrometer.core.instrument.util.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.security.config.CherryUserDetailsService;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.security.wx.WxClientUserInfo;
import org.sustudent.cherry.common.security.wx.WxUser;
import org.sustudent.cherry.services.user.api.feign.UserServiceClient;

@Slf4j
@Service
public class UserDetailServiceImpl extends CherryUserDetailsService {

  @Autowired
  private UserServiceClient userClientService;

  @Autowired
  private WxMaService wxService;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userClientService.findUserByUsername(username).getData();
  }

  @Override
  public UserDetails loadUserByWxCode(String code, WxClientUserInfo wxClientUserInfo)
      throws UsernameNotFoundException {
    try {
      WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
      WxUser wxUser = wxClientUserInfo.getRawData();
      wxUser.setOpenId(session.getOpenid());
      wxUser.setUnionId(session.getUnionid());
      validateUnionId(wxUser, wxClientUserInfo, session.getSessionKey());

      log.info("微信用户 {}", wxUser);
      ResponseResult<CherryUser> cherryUser = userClientService.findWxUserAndSave(wxUser);
      if (!cherryUser.getSuccess()) {
        throw new BadCredentialsException(cherryUser.getMsg());
      }
      return cherryUser.getData();
    } catch (WxErrorException e) {
      log.error("{}", e);
      throw new BadCredentialsException("微信接口异常：" + e.getMessage());
    }
  }

  private void validateUnionId(WxUser wxUser, WxClientUserInfo wxClientUserInfo,
      String sessionKey) {
    if (StringUtils.isNotBlank(wxUser.getUnionId())) {
      return;
    }
    WxMaUserInfo userInfo = wxService.getUserService()
        .getUserInfo(sessionKey, wxClientUserInfo.getEncryptedData(), wxClientUserInfo.getIv());
    wxUser.setUnionId(userInfo.getUnionId());
  }
}
