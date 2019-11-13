package org.sustudent.cherry.common.core.repository;

import org.sustudent.cherry.common.core.persistence.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<T extends BaseEntity> extends Mapper<T> {


}
