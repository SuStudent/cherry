package org.sustudent.cherry.common.security.wx;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxAppTokenGranter.java
 * @Description WxAppTokenGranter
 * @createTime 2020/06/06/ 16:06:00
 */
public class WxAppTokenGranter extends AbstractTokenGranter {

  private static final String GRANT_TYPE = "wx_miniapp";

  private final AuthenticationManager authenticationManager;

  private final ObjectMapper objectMapper = new ObjectMapper();

  {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public WxAppTokenGranter(
      AuthenticationManager authenticationManager,
      AuthorizationServerTokenServices tokenServices,
      ClientDetailsService clientDetailsService,
      OAuth2RequestFactory requestFactory) {
    this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
  }

  protected WxAppTokenGranter(AuthenticationManager authenticationManager,
      AuthorizationServerTokenServices tokenServices,
      ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
      String grantType) {
    super(tokenServices, clientDetailsService, requestFactory, grantType);
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
      TokenRequest tokenRequest) {

    Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
    String code = parameters.get("code");
    if (StringUtils.isBlank(code)) {
      throw new BadCredentialsException("微信小程序code参数缺失。");
    }

    Authentication userAuth = new WxAuthenticationToken(code);
    ((WxAuthenticationToken) userAuth).setWxClientUserInfo(getWxClientUserInfo());
    ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
    try {
      userAuth = authenticationManager.authenticate(userAuth);
    } catch (AccountStatusException | BadCredentialsException ase) {
      throw new InvalidGrantException(ase.getMessage());
    }
    if (userAuth == null || !userAuth.isAuthenticated()) {
      throw new InvalidGrantException("微信小程序认证失败: " + code);
    }
    OAuth2Request storedOAuth2Request = getRequestFactory()
        .createOAuth2Request(client, tokenRequest);
    return new OAuth2Authentication(storedOAuth2Request, userAuth);
  }

  private WxClientUserInfo getWxClientUserInfo() {
    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    assert sra != null;
    HttpServletRequest request = sra.getRequest();
    try (InputStream is = request.getInputStream()) {
      return objectMapper.readValue(is, WxClientUserInfo.class);
    } catch (Exception e) {
      throw new InvalidGrantException("request 解析用户数据失败 " + e.getMessage());
    }
  }
}
