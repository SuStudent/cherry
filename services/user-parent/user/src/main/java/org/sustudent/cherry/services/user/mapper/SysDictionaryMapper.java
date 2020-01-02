package org.sustudent.cherry.services.user.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.user.entity.SysDictionary;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionaryMapper.java
 * @Description SysDictionaryMapper
 * @createTime 2019/12/21/ 19:49:00
 */
public interface SysDictionaryMapper extends BaseMapper<SysDictionary> {

  List<SysDictionary> findPage(
      @Param("page") Page<SysDictionary> page, @Param("dict") SysDictionary sysDictionary);
}
