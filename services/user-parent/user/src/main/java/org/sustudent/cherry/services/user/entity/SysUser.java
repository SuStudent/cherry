package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUser.java
 * @Description SysUser
 * @createTime 2019/10/20/ 18:49:00
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

  /**
   * 用户登录名
   */
  @Column(name = "username")
  private String username;

  /**
   * 用户真实姓名
   */
  @Column(name = "real_name")
  private String realName;

  /**
   * 密码
   */
  @Column(name = "password")
  private String password;

  /**
   * 手机号
   */
  @Column(name = "mobile")
  private String mobile;

  /**
   * 邮箱
   */
  @Column(name = "email")
  private String email;

  /**
   * 启用（0：未启用；1：已启用）
   */
  @Column(name = "enabled")
  private Boolean enabled;

  /**
   * 账号未过期（0：已过期；1：未过期）
   */
  @Column(name = "account_non_expired")
  private Boolean accountNonExpired;

  /**
   * 账号未锁定（0：已锁定；1：未锁定）
   */
  @Column(name = "account_non_locked")
  private Boolean accountNonLocked;

  @Column(name = "photo")
  private String photo;

  @Transient
  private String name;
}
