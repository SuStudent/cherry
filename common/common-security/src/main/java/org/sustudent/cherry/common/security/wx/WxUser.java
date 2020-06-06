package org.sustudent.cherry.common.security.wx;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxUser.java
 * @Description WxUser
 * @createTime 2020/06/06/ 15:24:00
 */
@Data
public class WxUser implements Serializable {

  private String openId;

  private String unionId;

  /**
   * 性别 0：未知、1：男、2：女
   */
  private Integer gender;

  /**
   * 头像地址
   */
  private String avatarUrl;

  /**
   * 国家
   */
  private String country;

  /**
   * 省
   */
  private String province;

  /**
   * 市
   */
  private String city;

  /**
   * 语言
   */
  private String language;


  /**
   * 昵称
   */
  private String nickName;
}
