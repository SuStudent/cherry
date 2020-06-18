package org.sustudent.cherry.services.pipi.socket.handler;

import javax.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName TestSocketMessageHandler.java
 * @Description TestSocketMessageHandler
 * @createTime 2020/06/19/ 00:23:00
 */
@Slf4j
@Component
public class TestSocketMessageHandler implements SocketMessageHandler {

  @Override
  public void handle(Session session, SocketMessage socketMessage) {
    log.info("I'm receive. {}", socketMessage);
  }

  @Override
  public MessageTypeEnum supportType() {
    return MessageTypeEnum.TEST;
  }
}
