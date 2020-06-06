package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WeChartUser.java
 * @Description WeChartUser
 * @createTime 2020/06/06/ 18:33:00
 */
@Data
@Entity
@Table(name = "we_chart_user")
public class WeChartUser extends BaseEntity {

  @Column(name= "sys_user_id")
  private Long sysUserId;

  @Column(name = "open_id")
  private String openId;

  @Column(name = "union_id")
  private String unionId;

  /**
   * 性别 0：未知、1：男、2：女
   */
  @Column(name = "gender")
  private Integer gender;

  /**
   * 头像地址
   */
  @Column(name = "avatar_url")
  private String avatarUrl;

  /**
   * 国家
   */
  @Column(name = "country")
  private String country;

  /**
   * 省
   */
  @Column(name = "province")
  private String province;

  /**
   * 市
   */
  @Column(name = "city")
  private String city;

  /**
   * 语言
   */
  @Column(name = "language")
  private String language;


  /**
   * 昵称
   */
  @Column(name = "nick_name")
  private String nickName;
}
