package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysRole.java
 * @Description SysRole
 * @createTime 2019/11/20/ 10:41:00
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {

  /**
   * 角色名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 属性名
   */
  @Column(name = "attribute")
  private String attribute;

  /**
   * 描述
   */
  @Column(name = "description")
  private String description;

}
