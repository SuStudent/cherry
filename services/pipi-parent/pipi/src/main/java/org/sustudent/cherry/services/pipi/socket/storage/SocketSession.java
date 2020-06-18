package org.sustudent.cherry.services.pipi.socket.storage;

import java.io.Serializable;
import java.util.Objects;
import javax.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName GameSocketSession.java
 * @Description GameSocketSession
 * @createTime 2020/06/18/ 13:15:00
 */
@Data
@AllArgsConstructor
public class SocketSession implements Serializable {

  private Session session;

  private String roomNo;

  private Long userId;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SocketSession that = (SocketSession) o;
    return Objects.equals(session != null ? session.getId() : null,
        that.session != null ? that.session.getId() : null) &&
        Objects.equals(roomNo, that.roomNo) &&
        Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(session != null ? session.getId() : null, roomNo, userId);
  }
}
