package org.sustudent.cherry.common.core.service;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.persistence.BaseEntity;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.common.core.utils.ContextUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * 基础业务处理类，CRUD封装
 * @param <M>
 * @param <T>
 */
public class BaseService<M extends BaseMapper<T>, T extends BaseEntity> {

  @Autowired
  protected M mapper;


  /**
   * 根据主键查询
   */
  public T getById(Long id) {
    Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[1];

    Example example = new Example(entityClass);
    example.createCriteria()
        .andEqualTo("deleted", false)
        .andEqualTo("id", id);
    return mapper.selectOneByExample(example);
  }


  /**
   * 新增或更新
   */
  @Transactional
  public int saveOrUpdate(T t) {
    if (t.getId() != null && !t.getId().equals(0L)) {
      return this.update(t);
    } else {
      return this.insert(t);
    }
  }

  /**
   * 插入
   */
  @Transactional
  public int insert(T t) {
      t.setDeleted(false);
      t.setCreateBy(ContextUtils.getLoginUserId());
      t.setCreateTime(new Date());
      return mapper.insertSelective(t);
  }

  /**
   * 更新
   */
  @Transactional
  public int update(T t) {
      t.setDeleted(false);
      t.setUpdateBy(ContextUtils.getLoginUserId());
      t.setUpdateTime(new Date());
      return mapper.updateByPrimaryKeySelective(t);
  }

  /**
   * 逻辑删除
   */
  @Transactional
  public int delete(T t) {
      t.setDeleted(true);
      t.setUpdateBy(ContextUtils.getLoginUserId());
      t.setUpdateTime(new Date());
      return mapper.updateByPrimaryKeySelective(t);
  }

  /**
   * 逻辑删除
   */
  @Transactional
  public int delete(Long id) {
    T t = this.getById(id);
    if(t == null){
      return 0;
    }
    return delete(t);
  }
}
