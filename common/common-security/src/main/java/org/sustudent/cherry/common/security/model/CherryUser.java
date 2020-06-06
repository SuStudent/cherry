package org.sustudent.cherry.common.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.sustudent.cherry.common.security.enums.AuthTypeEnum;


public class CherryUser implements UserDetails {

  /**
   * 主键
   */
  private Long id;

  /**
   * 用户登录名
   */
  private String username;

  /**
   * 用户真实姓名
   */
  private String realName;

  /**
   * 密码
   */
  private String password;

  /**
   * 手机号
   */
  private String mobile;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 启用（0：未启用；1：已启用）
   */
  private Boolean enabled;

  /**
   * 账号未过期（0：已过期；1：未过期）
   */
  private Boolean accountNonExpired;

  /**
   * 账号未锁定（0：已锁定；1：未锁定）
   */
  private Boolean accountNonLocked;

  /**
   * 图片
   */
  private String photo;

  /**
   * 认证类型
   */
  private AuthTypeEnum authType;

  /**
   * 拥有的权限
   */
  private Set<CherryGrantedAuthority> authorities;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
  public Set<CherryGrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<CherryGrantedAuthority> authorities) {
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

  public AuthTypeEnum getAuthType() {
    return authType;
  }

  public void setAuthType(AuthTypeEnum authType) {
    this.authType = authType;
  }
}
