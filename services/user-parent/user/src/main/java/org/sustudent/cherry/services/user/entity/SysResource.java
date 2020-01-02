package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;
import org.sustudent.cherry.services.user.enums.ResourceType;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysResource.java
 * @Description SysResource
 * @createTime 2019/11/21/ 10:31:00
 */
@Data
@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseEntity {

  /**
   * 标题
   */
  @Column(name = "title")
  private String title;

  /**
   * 路径
   */
  @Column(name = "path")
  private String path;

  /**
   * 资源标识(同类型下不能重复)
   */
  @Column(name = "`key`")
  private String key;

  /**
   * 可导航
   */
  @Column(name = "navigable")
  private Boolean navigable;

  /**
   * 图标
   */
  @Column(name = "icon")
  private String icon;

  /**
   * 父ID
   */
  @Column(name = "parent_id")
  private Long parentId;

  /**
   * 排序字段
   */
  @Column(name = "sort")
  private Integer sort;

  /**
   * 资源类型
   */
  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private ResourceType type;
}
