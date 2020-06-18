package org.sustudent.cherry.services.pipi.socket.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SocketMessage.java
 * @Description SocketMessage
 * @createTime 2020/06/18/ 16:10:00
 */
@Data
@JsonInclude(Include.NON_EMPTY)
public abstract class SocketMessage implements Serializable {

  private Date sendDate;

  private String serialNo;

  public abstract MessageTypeEnum getType();
}
