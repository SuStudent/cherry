package org.sustudent.cherry.services.pipi.socket;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.services.pipi.socket.core.SocketMessageResolver;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;
import org.sustudent.cherry.services.pipi.socket.storage.SocketStorageManage;


/**
 * @Author ggl
 * @Description 小游戏 WebSocket 服务器
 * @Date 2020/6/15 14:39
 * @Version 1.0
 */
@Slf4j
@Component
@ServerEndpoint(value = "/gameSocket/{roomNo}/{userId}", decoders = SocketMessageDecoder.class)
public class SocketEndpoint {

  @OnOpen
  public void OnOpen(Session session, @PathParam(value = "roomNo") String roomNo,
      @PathParam(value = "userId") Long userId) {
    SocketStorageManage.saveSession(session, roomNo, userId);
  }

  @OnMessage
  public void OnMessage(Session session, SocketMessage socketMessage) throws IOException {
    SocketMessageResolver.handle(session, socketMessage);
  }

  @OnClose
  public void OnClose(Session session) {
    SocketStorageManage.deleteSession(session);
  }

}
