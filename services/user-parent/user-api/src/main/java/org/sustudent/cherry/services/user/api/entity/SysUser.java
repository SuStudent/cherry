package org.sustudent.cherry.services.user.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.sustudent.cherry.common.core.model.CherryUser;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUser.java
 * @Description SysUser
 * @createTime 2019/10/20/ 18:49:00
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends CherryUser {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

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
  @JsonIgnore
  private Collection<? extends GrantedAuthority> authorities;

  public SysUser() {
    super();
  }

  public SysUser(Long id, String username, String realName) {
    this.id = id;
    this.username = username;
    this.realName = realName;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  public void setAccountNonExpired(Boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }


  public void setAccountNonLocked(Boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return BooleanUtils.toBooleanDefaultIfNull(accountNonExpired, false);
  }

  @Override
  public boolean isAccountNonLocked() {
    return BooleanUtils.toBooleanDefaultIfNull(accountNonLocked, false);
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return BooleanUtils.toBooleanDefaultIfNull(enabled, false);
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}
