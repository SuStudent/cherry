package org.sustudent.cherry.services.pipi.socket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.common.security.utils.ContextUtils;
import org.sustudent.cherry.services.pipi.enums.GameKeyEnum;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.service.MathGameService;
import org.sustudent.cherry.services.pipi.socket.core.SocketMessageResolver;
import org.sustudent.cherry.services.pipi.socket.messages.MathGameSocketMessage;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;
import org.sustudent.cherry.services.pipi.socket.storage.SocketSession;
import org.sustudent.cherry.services.pipi.socket.storage.SocketStorageManage;

import javax.websocket.Session;

import java.io.IOException;
import java.util.List;
import org.sustudent.cherry.services.user.api.feign.UserServiceClient;

import static org.sustudent.cherry.services.pipi.enums.GameKeyEnum.COME_IN;
import static org.sustudent.cherry.services.pipi.enums.GameKeyEnum.PLAYING;
import static org.sustudent.cherry.services.pipi.enums.GameKeyEnum.START;

/**
 * @Author ggl
 * @Description 数学小程序 处理流程
 * @Date 2020/6/24 11:32
 * @Version 1.0
 */
@Slf4j
@Component
public class MathGameSocketMessageHandler implements SocketMessageHandler {

  @Autowired
  private UserServiceClient userServiceClient;

  @Override
  public void handle(SocketSession session, SocketMessage socketMessage) throws IOException {

//    MathGameSocketMessage gameSocketMessage = (MathGameSocketMessage) socketMessage;
//
//    String roomNo = session.getRoomNo();
//
//    String gameKey = gameSocketMessage.getGameKey();
//    // TODO 以下代码 需优化，先暂且这样
//
//    // 进入房间处理
//    if (COME_IN.getKey().equals(gameKey)) {
//      List<SocketSession> roomSessions = SocketStorageManage.getSessions(roomNo);
//      if (roomSessions.size() > 2) {
//        SocketStorageManage.deleteSession(session.getSession());
//        return;
//      }
//      MathGameSocketMessage sendMessage = new MathGameSocketMessage();
//      sendMessage.setGameKey(gameKey);
//      // 互相发送用户信息
//      CherryUser loginUser = ContextUtils.getLoginUser();
//      for (int i = 0; i < roomSessions.size(); i++) {
//        SocketSession sendSession = roomSessions.get(roomSessions.size() - 1 - i);
//        sendMessage.setUser(userServiceClient
//            .findUserById(sendSession.getUserId()).getData());
//        SocketMessageResolver.sendMessage(sendSession.getSession(), sendMessage);
//      }
//    } else if (START.getKey().equals(gameKey)) {
//
//      String questionStr = MathGameService.arithmeticString();
//      String answerStr = MathGameService.getAnswerStr(questionStr);
//      mathGameSocketMessage.setQuestion(questionStr);
//
//      SocketStorageManage.putAnswerToRoom(roomNo, answerStr);
//
//      for (Session playerSession : thisRoomAllSessions) {
//        playerSession.getBasicRemote().sendText(mathGameSocketMessage.toString());
//      }
//
//    }
  }

  @Override
  public MessageTypeEnum supportType() {
    return MessageTypeEnum.MATH;
  }


}