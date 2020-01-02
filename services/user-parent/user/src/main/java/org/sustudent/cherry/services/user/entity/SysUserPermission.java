package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

@Data
@Entity
@Table(name = "sys_user_permission")
public class SysUserPermission extends BaseEntity {

  /**
   * 用户ID
   */
  @Column(name = "user_id")
  private Long userId;

  /**
   * 角色ID
   */
  @Column(name = "role_id")
  private Long roleId;

  /**
   * attribute
   */
  @Transient
  private String roleAttribute;

}