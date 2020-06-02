package org.sustudent.cherry.common.core.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName Page.java
 * @Description Page
 * @createTime 2019/11/12/ 11:55:00
 */
public class Page<T> implements Serializable {

  private int pageSize;
  private int pageNo;
  private Collection<T> entities;
  private int entityCount;
  private int pageCount;

  public Page() {
    new Page(20, 1);
  }

  public Page(int pageSize, int pageNo) {
    if (pageNo <= 0 || pageSize <= 0) {
      throw new IllegalArgumentException(
          "Illegal paging arguments. [pageSize=" + pageSize + ", pageIndex=" + pageNo + "]");
    }
    this.pageSize = pageSize;
    this.pageNo = pageNo;

  }

  public int getPageSize() {
    return this.pageSize;
  }

  public int getPageNo() {
    return this.pageNo;
  }

  public void setEntities(Collection<T> entities) {
    this.entities = entities;
  }

  public Collection<T> getEntities() {
    return this.entities != null ? this.entities : Collections.EMPTY_LIST;
  }

  public int getEntityCount() {
    return this.entityCount;
  }

  public void setEntityCount(int entityCount) {
    if (entityCount < 0) {
      throw new IllegalArgumentException(
          "Illegal entityCount arguments. [entityCount=" + entityCount + "]");
    }
    this.entityCount = entityCount;
    this.pageCount = (entityCount - 1) / this.pageSize + 1;

  }

  public int getPageCount() {
    return this.pageCount;
  }

  public Iterator<T> iterator() {
    return this.entities != null ? this.entities.iterator() : null;
  }
}
