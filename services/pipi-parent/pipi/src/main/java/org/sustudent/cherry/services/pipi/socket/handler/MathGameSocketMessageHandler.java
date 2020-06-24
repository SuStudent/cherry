package org.sustudent.cherry.services.pipi.socket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;
import org.sustudent.cherry.services.pipi.service.MathGameService;
import org.sustudent.cherry.services.pipi.socket.messages.MathGameSocketMessage;
import org.sustudent.cherry.services.pipi.socket.messages.SocketMessage;
import org.sustudent.cherry.services.pipi.socket.storage.SocketStorageManage;

import javax.websocket.Session;

import java.io.IOException;
import java.util.List;

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

    //为每个线程 保存独立的 Session 信息
    private static final ThreadLocal<Session> threadSession = new ThreadLocal<>();

    //为每个线程 保存独立的 SocketMessage 信息
    private static final ThreadLocal<SocketMessage> threadSocketMessage = new ThreadLocal<>();

    @Override
    public void handle(Session session, SocketMessage socketMessage) throws IOException, InterruptedException {

        threadSession.set(session);
        threadSocketMessage.set(socketMessage);

        //通过 session 查出与该玩家同房间的玩家
        List<Session> thisRoomAllSessions = SocketStorageManage.getThisRoomAllSessions(threadSession.get());
        String roomNo = SocketStorageManage.getThisRoomNoBySession(threadSession.get());

        MathGameSocketMessage mathGameSocketMessage = (MathGameSocketMessage) threadSocketMessage.get();
        String gameKey = mathGameSocketMessage.getGameKey();

        //游戏开始：
        //  发送游戏题目 同时将正确答案存储起来
        MathGameService mathGameService = new MathGameService();
        if (START.getKey().equals(gameKey)) {

            String questionStr = mathGameService.arithmeticString();
            String answerStr = mathGameService.getAnswerStr(questionStr);
            mathGameSocketMessage.setQuestion(questionStr);

            SocketStorageManage.putAnswerToRoom(roomNo, answerStr);

            for (Session playerSession : thisRoomAllSessions) {
                playerSession.getBasicRemote().sendText(mathGameSocketMessage.toString());
            }

            Thread.sleep(5000);
        }

        //游戏进行中：
        //  对比各玩家答案是否与正确答案一直 并返回结果
        if (PLAYING.getKey().equals(gameKey)) {
            String correctAnswer = SocketStorageManage.getAnswerByRoomNo(roomNo);
            String myAnswer = mathGameSocketMessage.getAnswer();

            if (correctAnswer.equals(myAnswer)) {
                mathGameSocketMessage.setCorrectOrWrong(true);
            } else {
                mathGameSocketMessage.setCorrectOrWrong(false);
            }

            threadSession.get().getBasicRemote().sendText(mathGameSocketMessage.toString());
        }


        //单独启动一个线程 定时发送问题
        new Thread(() -> {

            for (int i = 1; i < 10; i++) {
                String questionStr = mathGameService.arithmeticString();
                String answerStr = mathGameService.getAnswerStr(questionStr);
                mathGameSocketMessage.setQuestion(questionStr);

                SocketStorageManage.putAnswerToRoom(roomNo, answerStr);

                for (Session playerSession : thisRoomAllSessions) {
                    try {
                        playerSession.getBasicRemote().sendText(mathGameSocketMessage.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


    }

    @Override
    public MessageTypeEnum supportType() {
        return MessageTypeEnum.MATH;
    }


}