package org.sustudent.cherry.services.pipi.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName GameSocketStorageManage.java
 * @Description session管理器
 * @createTime 2020/06/18/ 13:17:00
 */
@Slf4j
public final class GameSocketStorageManage {

  /**
   * key: roomNo
   */
  private static final Map<String, GameSocketRoom> roomMap = new ConcurrentHashMap<>();

  /**
   * key: sessionId
   */
  private static final Map<String, GameSocketSession> gameSessionMap = new ConcurrentHashMap<>();


  public static GameSocketSession convertSession(Session session) {
    return gameSessionMap.get(session.getId());
  }

  public static GameSocketSession convertSession(Session session, String roomNo, Long userId) {
    if (gameSessionMap.get(session.getId()) != null) {
      return convertSession(session);
    }
    return new GameSocketSession(session, roomNo, userId);
  }

  public static List<GameSocketSession> getSessions(String roomNo) {
    GameSocketRoom socketRoom = roomMap.get(roomNo);
    if (socketRoom != null) {
      return socketRoom.getSessions();
    }
    return new ArrayList<>();
  }

  public static void saveSession(Session session, String roomNo, Long userId) {
    if (StringUtils.isBlank(roomNo)) {
      log.error("roomNo must be is not null;");
      return;
    }
    GameSocketSession gameSocketSession = convertSession(session, roomNo, userId);
    gameSessionMap.put(session.getId(), gameSocketSession);
    synchronized (roomNo) {
      GameSocketRoom room = roomMap.getOrDefault(roomNo, new GameSocketRoom());
      room.setRoomNo(roomNo);
      room.getSessions().add(gameSocketSession);
      roomMap.put(roomNo, room);
    }
    log.info("保存成功，sessionSize: {}, roomSessionSize: {}",gameSessionMap.size(),roomMap.size());
  }

  public static void deleteSession(Session session) {
    GameSocketSession gameSocketSession = gameSessionMap.remove(session.getId());
    if (gameSocketSession == null) {
      return;
    }
    GameSocketRoom socketRoom = roomMap.get(gameSocketSession.getRoomNo());
    if (socketRoom != null) {
      synchronized (socketRoom.getRoomNo()) {
        socketRoom.getSessions().remove(gameSocketSession);
      }
    }
    log.info("删除成功，sessionSize: {}, roomSessionSize: {}",gameSessionMap.size(),roomMap.size());
  }
}
