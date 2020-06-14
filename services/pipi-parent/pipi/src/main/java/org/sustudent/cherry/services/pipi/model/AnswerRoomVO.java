package org.sustudent.cherry.services.pipi.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AnswerRoomVO.java
 * @Description AnswerRoomVO
 * @createTime 2020/06/14/ 16:33:00
 */
@Data
public class AnswerRoomVO implements Serializable {

  private String keywords;

  private String latitude;

  private String longitude;

}
