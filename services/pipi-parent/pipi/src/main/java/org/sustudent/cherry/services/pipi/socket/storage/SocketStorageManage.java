package org.sustudent.cherry.services.pipi.socket.storage;

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
public final class SocketStorageManage {

  /**
   * key: roomNo
   */
  private static final Map<String, SocketRoom> roomMap = new ConcurrentHashMap<>();

  /**
   * key: sessionId
   */
  private static final Map<String, SocketSession> socketSessionMap = new ConcurrentHashMap<>();


  public static SocketSession convertSession(Session session) {
    return socketSessionMap.get(session.getId());
  }

  public static SocketSession convertSession(Session session, String roomNo, Long userId) {
    if (socketSessionMap.get(session.getId()) != null) {
      return convertSession(session);
    }
    return new SocketSession(session, roomNo, userId);
  }

  public static List<SocketSession> getSessions(String roomNo) {
    SocketRoom socketRoom = roomMap.get(roomNo);
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
    SocketSession socketSession = convertSession(session, roomNo, userId);
    socketSessionMap.put(session.getId(), socketSession);
    synchronized (roomNo) {
      SocketRoom room = roomMap.getOrDefault(roomNo, new SocketRoom());
      room.setRoomNo(roomNo);
      room.getSessions().add(socketSession);
      roomMap.put(roomNo, room);
    }
    log.info("保存成功，sessionSize: {}, roomSessionSize: {}",socketSessionMap.size(),roomMap.size());
  }

  public static void deleteSession(Session session) {
    SocketSession socketSession = socketSessionMap.remove(session.getId());
    if (socketSession == null) {
      return;
    }
    SocketRoom socketRoom = roomMap.get(socketSession.getRoomNo());
    if (socketRoom != null) {
      synchronized (socketRoom.getRoomNo()) {
        socketRoom.getSessions().remove(socketSession);
      }
    }
    log.info("删除成功，sessionSize: {}, roomSessionSize: {}",socketSessionMap.size(),roomMap.size());
  }
}
