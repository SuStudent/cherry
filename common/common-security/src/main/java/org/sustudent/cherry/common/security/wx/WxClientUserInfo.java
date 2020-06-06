package org.sustudent.cherry.common.security.wx;

import lombok.Data;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WxClientUserInfo.java
 * @Description WxClientUserInfo
 * @createTime 2020/06/06/ 20:51:00
 */
@Data
public class WxClientUserInfo {
  private String encryptedData;
  private String iv;
  private WxUser rawData;
  private String signature;
}
