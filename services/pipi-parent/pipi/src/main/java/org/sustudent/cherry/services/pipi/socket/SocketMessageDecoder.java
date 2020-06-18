package org.sustudent.cherry.services.pipi.socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.socket.core.SocketMessageResolver;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CustomDecoder.java
 * @Description CustomDecoder
 * @createTime 2020/06/18/ 22:09:00
 */
@Slf4j
public class SocketMessageDecoder implements Decoder.Text<SocketMessage> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {
    log.info("init.");
  }

  @Override
  public void destroy() {
    log.info("destroy.");

  }

  @Override
  public SocketMessage decode(String s) throws DecodeException {
    SocketMessage socketMessage = null;

    try {
      Map<String, Object> map = objectMapper.readValue(s, new TypeReference<Map<String, Object>>() {
      });
      String type = MapUtils.getString(map, "type");
      if (StringUtils.isBlank(type)) {
        throw new RuntimeException("type 缺失");
      }
      Class<? extends SocketMessage> clazz = SocketMessageResolver
          .getSocketMessageClass(MessageTypeEnum.resolve(type));
      try {
        socketMessage = clazz.newInstance();
        BeanUtils.populate(socketMessage, map);
      } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
        log.error("socketMessage 解析失败");
      }
    } catch (IOException e) {
      log.error("json 解析失败");
    }

    return socketMessage;
  }

  @Override
  public boolean willDecode(String s) {
    return true;
  }

}
