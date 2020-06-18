package org.sustudent.cherry.services.pipi.socket.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import org.springframework.util.Assert;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SocketMessageResolver.java
 * @Description SocketMessageResolver
 * @createTime 2020/06/18/ 22:53:00
 */
public class SocketMessageResolver {

  private static final Map<MessageTypeEnum, Class<? extends SocketMessage>> socketMessageTypeMap = new HashMap<>();

  static {
    Reflections reflections = new Reflections("org.sustudent.cherry.services.pipi.socket.messages");
    Set<Class<? extends SocketMessage>> subTypes = reflections.getSubTypesOf(SocketMessage.class);
    subTypes.forEach(x -> {
      try {
        SocketMessage socketMessage = x.newInstance();
        socketMessageTypeMap.put(socketMessage.getType(), socketMessage.getClass());
      } catch (IllegalAccessException | InstantiationException e) {
        e.printStackTrace();
      }
    });
  }

  public static Class<? extends SocketMessage> getSocketMessageClass(MessageTypeEnum messageTypeEnum) {
    Assert.notNull(messageTypeEnum, "messageTypeEnum must not be null!");
    Class<? extends SocketMessage> clazz = socketMessageTypeMap.get(messageTypeEnum);
    Assert.notNull(clazz, "messageTypeEnum not found !");
    return clazz;
  }

}
