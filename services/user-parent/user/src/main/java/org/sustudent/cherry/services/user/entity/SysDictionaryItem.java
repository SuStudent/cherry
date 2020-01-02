package org.sustudent.cherry.services.user.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionaryItem.java
 * @Description SysDictionaryItem
 * @createTime 2019/12/21/ 19:36:00
 */
@Data
@Entity
@Table(name = "sys_dictionary_item")
public class SysDictionaryItem extends BaseEntity {


  /**
   * 标签
   */
  @Column(name = "label")
  private String label;

  /**
   * 值
   */
  @Column(name = "value")
  private String value;

  /**
   * 启用
   */
  @Column(name = "enabled")
  private Boolean enabled;

  /**
   * 排序
   */
  @Column(name = "sort")
  private Integer sort;

  /**
   * 字典ID
   */
  @Column(name = "dictionary_id")
  private Long dictionaryId;
}

