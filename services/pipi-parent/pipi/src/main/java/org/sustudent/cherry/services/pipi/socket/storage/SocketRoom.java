package org.sustudent.cherry.services.pipi.socket.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName GameSocketRoom.java
 * @Description GameSocketRoom
 * @createTime 2020/06/18/ 13:11:00
 */
@Data
public class SocketRoom implements Serializable {

  private String roomNo;

  private List<SocketSession> sessions;

  public List<SocketSession> getSessions() {
    if(sessions == null) {
      sessions = new ArrayList<>();
    }
    return sessions;
  }
}
