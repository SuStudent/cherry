package org.sustudent.cherry.services.user.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.user.entity.SysDictionaryItem;
import org.sustudent.cherry.services.user.mapper.SysDictionaryItemMapper;
import org.sustudent.cherry.services.user.mapper.SysDictionaryMapper;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionaryItemService.java
 * @Description SysDictionaryItemService
 * @createTime 2019/12/21/ 19:51:00
 */
@Transactional
public class SysDictionaryItemService extends BaseService<SysDictionaryItemMapper, SysDictionaryItem> {

  public List<SysDictionaryItem> findDictItems(SysDictionaryItem sysDictionaryItem) {
    return mapper.findDictItems(sysDictionaryItem);
  }
}
