package org.sustudent.cherry.common.security.model;

import lombok.Data;
import org.springframework.security.access.ConfigAttribute;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryConfigAttribute.java
 * @Description CherryConfigAttribute
 * @createTime 2019/11/22/ 15:08:00
 */
@Data
public class CherryConfigAttribute implements ConfigAttribute {


  public String attribute;
}
