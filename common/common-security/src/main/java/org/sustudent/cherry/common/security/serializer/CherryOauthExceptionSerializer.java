package org.sustudent.cherry.common.security.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Map;
import org.sustudent.cherry.common.security.exception.CherryOauthException;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName 自定义Oauth认证异常序列化
 * @Description CherryOauthExceptionSerializer
 * @createTime 2019/10/21/ 13:30:00
 */
public class CherryOauthExceptionSerializer extends StdSerializer<CherryOauthException> {

  protected CherryOauthExceptionSerializer() {
    super(CherryOauthException.class);
  }

  @Override
  public void serialize(CherryOauthException value, JsonGenerator gen,
      SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    // 封装属性，与 ResponseResult
    gen.writeNumberField("status", value.getCode());
    gen.writeBooleanField("success", false);
    gen.writeStringField("msg", value.getMessage());

    if (value.getAdditionalInformation() != null) {
      for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
        String key = entry.getKey();
        String add = entry.getValue();
        gen.writeStringField(key, add);
      }
    }
    gen.writeEndObject();
  }
}
