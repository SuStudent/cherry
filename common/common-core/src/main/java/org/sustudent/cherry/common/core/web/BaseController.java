package org.sustudent.cherry.common.core.web;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.model.ResponseResult;

public abstract class BaseController {

  protected <T> Page<T> getPage(HttpServletRequest request, Class<T> t) {
    int pageNo = 1;
    int pageSize = 20;

    String pageNoStr = request.getParameter("pageNo");
    if (StringUtils.isNotBlank(pageNoStr)) {
      pageNo = Integer.parseInt(pageNoStr);
    }

    String pageSizeStr = request.getParameter("pageSize");
    if (StringUtils.isNotBlank(pageSizeStr)) {
      pageSize = Integer.parseInt(pageSizeStr);
    }

    return new Page<T>(pageSize, pageNo);
  }

  protected ResponseResult success() {
    return success(null);
  }

  protected <T> ResponseResult<T> success(T data) {
    return ResponseResult.success(data);
  }

  protected ResponseResult<?> error(String msg) {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
  }

  protected <T> ResponseResult<T> error(Integer status, String msg) {
    return ResponseResult.error(status, msg);
  }


}
