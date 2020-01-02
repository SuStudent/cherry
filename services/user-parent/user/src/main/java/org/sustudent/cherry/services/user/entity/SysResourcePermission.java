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
 * @ClassName SysResourcePermission.java
 * @Description SysResourcePermission
 * @createTime 2019/11/22/ 15:06:00
 */
@Data
@Entity
@Table(name = "sys_resource_permission")
public class SysResourcePermission extends BaseEntity {

  /**
   * 资源ID
   */
  @Column(name = "resource_id")
  private Long resourceId;

  /**
   * 角色ID
   */
  @Column(name = "role_id")
  private Long roleId;

  /**
   * 角色属性名称
   */
  @Transient
  private String roleAttribute;

  /**
   * 资源路径
   */
  @Transient
  private String resourcePath;
}
