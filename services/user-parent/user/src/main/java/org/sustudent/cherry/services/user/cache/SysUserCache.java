package org.sustudent.cherry.services.user.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.security.model.CherryGrantedAuthority;
import org.sustudent.cherry.common.security.model.CherryUser;
import org.sustudent.cherry.services.user.entity.SysUser;
import org.sustudent.cherry.services.user.mapper.SysUserMapper;
import org.sustudent.cherry.services.user.mapper.SysUserPermissionMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUserCache.java
 * @Description SysUserCache
 * @createTime 2019/11/22/ 16:48:00
 */
@Service
public class SysUserCache {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysUserPermissionMapper userPermissionMapper;


  @Cacheable(value = "sysUser", key = "methodName + '::' + #username")
  public CherryUser findUserByUsername(String username){
    Example example = new Example(SysUser.class);
    example.createCriteria()
        .andEqualTo("username", username)
        .andEqualTo("deleted", false);
    SysUser sysUser = sysUserMapper.selectOneByExample(example);

    if (sysUser == null) {
      return null;
    }

    CherryUser user = this.buildUser(sysUser);

    Set<CherryGrantedAuthority> authorities = userPermissionMapper
        .findUserPermission(sysUser.getId())
        .stream().map(v -> {
          CherryGrantedAuthority authority = new CherryGrantedAuthority();
          authority.setAuthority(v.getRoleAttribute());
          return authority;
        }).collect(Collectors.toSet());

//    Set<CherryGrantedAuthority> authorities = new HashSet<>();
//    CherryGrantedAuthority authority = new CherryGrantedAuthority();
//    authority.setAuthority("test01");
//    authorities.add(authority);

    user.setAuthorities(authorities);
    return user;
  }

  private CherryUser buildUser(SysUser sysUser) {
    CherryUser user = new CherryUser();
    user.setId(sysUser.getId());
    user.setEmail(sysUser.getEmail());
    user.setMobile(sysUser.getMobile());
    user.setPhoto(sysUser.getPhoto());
    user.setRealName(sysUser.getRealName());
    user.setUsername(sysUser.getUsername());
    user.setPassword(sysUser.getPassword());
    user.setEnabled(sysUser.getEnabled());
    user.setAccountNonExpired(sysUser.getAccountNonExpired());
    user.setAccountNonLocked(sysUser.getAccountNonLocked());
    return user;
  }
}
