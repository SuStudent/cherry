package org.sustudent.cherry.services.pipi.socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.management.RuntimeMBeanException;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
public class CustomDecoder implements Decoder.Text<SocketMessage> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }

  @Override
  public SocketMessage decode(String s) throws DecodeException {
    try {
      Map<String, Object> map = objectMapper.readValue(s, new TypeReference<Map<String, Object>>() {
      });
      String type = MapUtils.getString(map, "type");
      if (StringUtils.isBlank(type)) {
        throw new RuntimeException("type 缺失");
      }
      Class<? extends SocketMessage> clazz = SocketMessageResolver
          .getSocketMessageClass(MessageTypeEnum.resolve(type));
      // TODO 明天再写
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public boolean willDecode(String s) {
    return false;
  }
}
