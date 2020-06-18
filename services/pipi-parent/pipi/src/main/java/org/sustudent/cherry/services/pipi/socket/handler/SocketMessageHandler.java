package org.sustudent.cherry.services.pipi.socket.handler;

import javax.websocket.Session;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;

public interface SocketMessageHandler {

  void handle(Session session, SocketMessage socketMessage);

  MessageTypeEnum supportType();
}
