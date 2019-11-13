package org.sustudent.cherry.common.core.mybatis;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName MybatisConfig.java
 * @Description MybatisConfig
 * @createTime 2019/11/12/ 13:21:00
 */
@Configuration
public class MybatisConfig {

  @Autowired
  private void addPageInterceptor(PageHelperAutoConfiguration pageHelperAutoConfiguration,List<SqlSessionFactory> sqlSessionFactoryList) {
    sqlSessionFactoryList.forEach(sqlSessionFactory -> {
      sqlSessionFactory.getConfiguration().addInterceptor(new PageIntercept());
    });
  }

}
