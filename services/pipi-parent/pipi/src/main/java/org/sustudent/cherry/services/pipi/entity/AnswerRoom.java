package org.sustudent.cherry.services.pipi.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.sustudent.cherry.common.core.persistence.BaseEntity;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AnswerRoom.java
 * @Description AnswerRoom
 * @createTime 2020/06/14/ 16:23:00
 */
@Data
@Entity
@Table(name = "answer_room")
public class AnswerRoom extends BaseEntity {

  /**
   * 房间名称
   */
  @Column(name = "name")
  private String name;

  /**
   * 房间描述
   */
  @Column(name = "description")
  private String description;

  /**
   * 经度
   */
  @Column(name = "longitude")
  private String longitude;

  /**
   * 纬度
   */
  @Column(name = "latitude")
  private String latitude;

  /**
   * 房间密码
   */
  @Column(name = "password")
  private String password;

  /**
   * 距离
   */
  @Transient
  private String distance;
}
