package org.sustudent.cherry.services.user.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.api.entity.SysUser;
import org.sustudent.cherry.services.user.mapper.SysUserMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysUserService.java
 * @Description SysUserService
 * @createTime 2019/10/20/ 19:02:00
 */
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {


  @Cacheable(value = "sysUser", key = "methodName + '::' + #username")
  public SysUser findUserByUsername(String username) {
    Example example = new Example(SysUser.class);
    example.createCriteria()
        .andEqualTo("username", username)
        .andEqualTo("deleted", false);
    return mapper.selectOneByExample(example);
  }

  public void findPage(Page<SysUser> page) {
    mapper.findPage(page,new SysUser());
  }

  public static void main(String[] args) {
    System.out.println(new BCryptPasswordEncoder().encode("cherry"));
  }
}
