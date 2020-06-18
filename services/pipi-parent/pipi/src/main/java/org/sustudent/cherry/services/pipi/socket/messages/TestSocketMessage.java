package org.sustudent.cherry.services.pipi.socket.messages;

import lombok.Data;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName TestSocketMessage.java
 * @Description TestSocketMessage
 * @createTime 2020/06/18/ 22:27:00
 */
@Data
public class TestSocketMessage extends SocketMessage {

  private String username;

  private String sex;

  @Override
  public MessageTypeEnum getType() {
    return MessageTypeEnum.TEST;
  }
}
