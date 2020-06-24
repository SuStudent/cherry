package org.sustudent.cherry.services.pipi.socket.messages;

import lombok.Data;
import org.sustudent.cherry.services.pipi.enums.MessageTypeEnum;

/**
 * @Author ggl
 * @Description
 * @Date 2020/6/24 15:51
 * @Version 1.0
 */
@Data
public class MathGameSocketMessage extends SocketMessage {

    private String gameKey;             //前台 游戏指令关键字 例如：点击开始按钮为 START

    private String question;            //后台 发送的问题

    private String answer;              //前台 玩家的答案

    private boolean correctOrWrong;     //后台 答案是否正确

    private Integer countdown;          //后台 倒计时

    @Override
    public MessageTypeEnum getType() {
        return MessageTypeEnum.MATH;
    }
}
