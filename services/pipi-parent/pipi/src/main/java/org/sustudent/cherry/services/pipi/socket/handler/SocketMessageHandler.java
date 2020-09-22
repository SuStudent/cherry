package org.sustudent.cherry.services.pipi.socket.handler;

import java.io.IOException;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;
import org.sustudent.cherry.services.pipi.socket.storage.SocketSession;

public interface SocketMessageHandler {

  void handle(SocketSession session, SocketMessage socketMessage) throws IOException;

  MessageTypeEnum supportType();
}
