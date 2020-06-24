package org.sustudent.cherry.services.pipi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

  TEST("TEST", "测试"),

  MATH("MATH", "数学小程序");

  private final String type;

  private final String description;

  public static MessageTypeEnum resolve(String type) {
    for (MessageTypeEnum value : MessageTypeEnum.values()) {
      if (value.getType().equalsIgnoreCase(type)) {
        return value;
      }
    }
    return null;
  }
}
