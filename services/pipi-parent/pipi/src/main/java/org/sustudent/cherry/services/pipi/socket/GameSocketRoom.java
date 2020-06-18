package org.sustudent.cherry.services.pipi.socket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName GameSocketRoom.java
 * @Description GameSocketRoom
 * @createTime 2020/06/18/ 13:11:00
 */
@Data
public class GameSocketRoom implements Serializable {

  private String roomNo;

  private List<GameSocketSession> sessions;

  public List<GameSocketSession> getSessions() {
    if(sessions == null) {
      sessions = new ArrayList<>();
    }
    return sessions;
  }
}
