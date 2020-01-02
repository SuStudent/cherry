package org.sustudent.cherry.services.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionary.java
 * @Description SysDictionary
 * @createTime 2019/12/21/ 19:33:00
 */
@Data
@Entity
@Table(name = "sys_dictionary")
public class SysDictionary extends BaseEntity {

  /**
   * 字典Code
   */
  @Column(name = "code")
  private String code;

  /**
   * 字典名称
   */
  @Column(name = "name")
  private String name;

}
