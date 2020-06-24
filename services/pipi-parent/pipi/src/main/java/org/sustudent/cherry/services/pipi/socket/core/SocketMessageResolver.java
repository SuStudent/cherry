package org.sustudent.cherry.services.pipi.socket.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.handler.SocketMessageHandler;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SocketMessageResolver.java
 * @Description SocketMessageResolver
 * @createTime 2020/06/18/ 22:53:00
 */
@Slf4j
@Component
public class SocketMessageResolver {

  private static final Map<MessageTypeEnum, Class<? extends SocketMessage>> socketMessageTypeMap = new HashMap<>();

  private static final Map<MessageTypeEnum, SocketMessageHandler> socketMessageHandlerMap = new HashMap<>();

  @Autowired
  public SocketMessageResolver(List<SocketMessageHandler> socketMessageHandlers) {
    if (CollectionUtils.isNotEmpty(socketMessageHandlers)) {
      socketMessageHandlers.forEach(handler -> {
        socketMessageHandlerMap.put(handler.supportType(), handler);
      });
    }
  }

  @PostConstruct
  public void postConstructTest() {
    resolveMessageType();
  }

  private void resolveMessageType() {
    Reflections reflections = new Reflections("org.sustudent.cherry.services.pipi.socket.messages");
    Set<Class<? extends SocketMessage>> subTypes = reflections.getSubTypesOf(SocketMessage.class);
    subTypes.forEach(subType -> {
      try {
        SocketMessage socketMessage = subType.newInstance();
        if (!socketMessageHandlerMap.containsKey(socketMessage.getType())) {
          log.warn("{} handler not found", socketMessage.getType());
        }
        socketMessageTypeMap.put(socketMessage.getType(), socketMessage.getClass());
      } catch (IllegalAccessException | InstantiationException e) {
        e.printStackTrace();
      }
    });
  }

  public static Class<? extends SocketMessage> getSocketMessageClass(
      MessageTypeEnum messageTypeEnum) {
    Assert.notNull(messageTypeEnum, "messageTypeEnum must not be null!");
    Class<? extends SocketMessage> clazz = socketMessageTypeMap.get(messageTypeEnum);
    Assert.notNull(clazz, "messageTypeEnum not found !");
    return clazz;
  }

  public static void handle(Session session,SocketMessage socketMessage) throws IOException, InterruptedException {
    Assert.notNull(session, "messageTypeEnum must not be null!");
    Assert.notNull(socketMessage, "socketMessage must not be null!");
    SocketMessageHandler socketMessageHandler = socketMessageHandlerMap.get(socketMessage.getType());
    Assert.notNull(socketMessageHandler, "messageTypeEnum not found !");
    socketMessageHandler.handle(session,socketMessage);
  }
}
