package org.sustudent.cherry.services.pipi.socket.messages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.Reflections;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName TestSocketMessage.java
 * @Description TestSocketMessage
 * @createTime 2020/06/18/ 22:27:00
 */
public class TestSocketMessage extends SocketMessage {

  private String name;

  private String asd;


  public static void main(String[] args) {
    Reflections reflections = new Reflections("org.sustudent.cherry.services.pipi.socket");
    Set<Class<? extends SocketMessage>> subTypes = reflections.getSubTypesOf(SocketMessage.class);
    subTypes.forEach(x -> {
      try {
        SocketMessage socketMessage = x.newInstance();
        System.out.println(socketMessage.getType());
        System.out.println(socketMessage.getClass());
      } catch ( IllegalAccessException | InstantiationException e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public MessageTypeEnum getType() {
    return MessageTypeEnum.TEST;
  }
}
