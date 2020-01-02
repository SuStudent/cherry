package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.entity.SysDictionaryItem;

public interface SysDictionaryItemMapper extends BaseMapper<SysDictionaryItem> {

  List<SysDictionaryItem> findDictItems(SysDictionaryItem sysDictionaryItem);
}
