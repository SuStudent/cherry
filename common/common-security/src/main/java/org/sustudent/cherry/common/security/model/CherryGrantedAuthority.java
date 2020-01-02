package org.sustudent.cherry.common.security.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryGrantedAuthority.java
 * @Description CherryGrantedAuthority
 * @createTime 2019/11/20/ 13:02:00
 */
@Data
public class CherryGrantedAuthority implements GrantedAuthority {

  private String authority;

}
