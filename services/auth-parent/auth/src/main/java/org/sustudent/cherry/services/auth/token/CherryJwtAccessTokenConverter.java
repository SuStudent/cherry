package org.sustudent.cherry.services.auth.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.sustudent.cherry.common.security.model.CherryUser;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryJwtAccessTokenConverter.java
 * @Description CherryJwtAccessTokenConverter
 * @createTime 2019/10/22/ 10:27:00
 */
public class CherryJwtAccessTokenConverter extends JwtAccessTokenConverter {

  private final String jwt_user_key = "user_key";

  /**
   * 生成token
   */
  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
//    DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
//    CherryUser cherryUser = ((CherryUser) authentication.getPrincipal());
//    // 将用户信息添加到token额外信息中
//    defaultOAuth2AccessToken.getAdditionalInformation().put(jwt_user_key, cherryUser);

    return super.enhance(accessToken, authentication);
  }

  @Override
  public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map) {
//    OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
//    convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
    return super.extractAccessToken(value,map);
  }

  private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
    accessToken.getAdditionalInformation()
        .put(jwt_user_key, convertUserData(map.get(jwt_user_key)));

  }

  private CherryUser convertUserData(Object map) {
    ObjectMapper om = new ObjectMapper();
    String json = null;
    CherryUser user = null;
    try {
      json = om.writeValueAsString(map);
      user = om.readValue(json, CherryUser.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return user;
  }

  public static void main(String[] args) {
    Map<String,Object> a = Maps.newHashMap();

    a.put("a","a");
    a.put("b","b");
    a.put("c","c");

    a.values();
  }
}
