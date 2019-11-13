package org.sustudent.cherry.common.core.model;

import org.springframework.security.core.userdetails.UserDetails;
import org.sustudent.cherry.common.core.persistence.BaseEntity;


/**
 * 登录用户抽象类。
 */
public abstract class CherryUser extends BaseEntity implements UserDetails {

}
