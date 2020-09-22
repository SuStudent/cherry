package org.sustudent.cherry.services.pipi.service;

import static org.sustudent.cherry.services.pipi.enums.GameKeyEnum.ARITHMETIC_DELIMITER;

/**
 * @Author ggl
 * @Description 数学计算小游戏 基础功能
 * @Date 2020/6/15 14:47
 * @Version 1.0
 */
public class MathGameService {

    private static final String OPERATIONAL_SYMBOL = "+-*/";

    /**
     * @Description 生成10以内的四则运算题目
     * @param
     * @return java.lang.String
     */
    public static String arithmeticString() {

        int a = (int) (Math.random() * 10 + 1);
        int b = (int) (Math.random() * 10 + 1);
        String symbol = String.valueOf(OPERATIONAL_SYMBOL.charAt((int) (Math.random() * 4 + 0)));

        return a + ARITHMETIC_DELIMITER.getKey() + symbol + ARITHMETIC_DELIMITER.getKey() + b;

    }

    public static void main(String[] args) {
        System.out.println(arithmeticString());
    }

    public static String getAnswerStr(String message) {
        String[] split = message.split(ARITHMETIC_DELIMITER.getKey());
        Integer first = Integer.valueOf(split[0]);
        String operator = split[1];
        Integer second = Integer.valueOf(split[2]);
        String answerStr;
        if ("+".equals(operator)) {
            answerStr = String.valueOf(first + second);
        } else if ("-".equals(operator)) {
            answerStr = String.valueOf(first - second);
        } else if ("*".equals(operator)) {
            answerStr = String.valueOf(first * second);
        } else {
            answerStr = String.valueOf(first / second);
        }
        return answerStr;
    }
}
