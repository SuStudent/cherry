package org.sustudent.cherry.services.user.service;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.exception.CommonException;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.common.security.wx.WxUser;
import org.sustudent.cherry.services.user.entity.SysUser;
import org.sustudent.cherry.services.user.entity.WeChartUser;
import org.sustudent.cherry.services.user.mapper.WeChartUserMapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName WeChartUserService.java
 * @Description WeChartUserService
 * @createTime 2020/06/06/ 19:22:00
 */
@Service
@Transactional
public class WeChartUserService extends BaseService<WeChartUserMapper, WeChartUser> {

  public WeChartUser getByOpenIdAndUnionId(String openId, String unionId) {
    Example example = new Example(WeChartUser.class);
    example.and().andEqualTo("deleted", false)
        .andEqualTo("openId", openId);
    if(StringUtils.isNotBlank(unionId)) {
      example.and().andEqualTo("unionId", unionId);
    }
    return mapper.selectOneByExample(example);
  }

  public void saveFromWxUser(WxUser wxUser, SysUser sysUser) {
    WeChartUser weChartUser = new WeChartUser();
    try {
      PropertyUtils.copyProperties(weChartUser,wxUser);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new CommonException("bean 转换失败");
    }
    weChartUser.setSysUserId(sysUser.getId());
    super.saveOrUpdate(weChartUser);
  }
}
