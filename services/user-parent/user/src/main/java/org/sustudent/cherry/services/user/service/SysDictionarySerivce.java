package org.sustudent.cherry.services.user.service;

import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.entity.SysDictionary;
import org.sustudent.cherry.services.user.mapper.SysDictionaryMapper;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionarySerivce.java
 * @Description SysDictionarySerivce
 * @createTime 2019/12/21/ 19:50:00
 */
@Transactional
public class SysDictionarySerivce extends BaseService<SysDictionaryMapper, SysDictionary> {

  public void findPage(Page<SysDictionary> page, SysDictionary sysDictionary) {
    mapper.findPage(page,sysDictionary);
  }
}
