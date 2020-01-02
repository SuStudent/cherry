package org.sustudent.cherry.services.user.cache;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.sustudent.cherry.services.user.entity.SysResource;
import org.sustudent.cherry.services.user.mapper.SysResourceMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysResourceCache.java
 * @Description SysResourceCache
 * @createTime 2019/11/22/ 16:43:00
 */
@Service
public class SysResourceCache {

  @Autowired
  private SysResourceMapper sysResourceMapper;

  @Cacheable(cacheNames = "all_sys_resource")
  public List<SysResource> findAllSysResource() {
    Example example = new Example(SysResource.class);
    example.createCriteria()
        .andEqualTo("deleted", false);
    example.orderBy("sort").asc();
    return sysResourceMapper.selectByExample(example);
  }
}
