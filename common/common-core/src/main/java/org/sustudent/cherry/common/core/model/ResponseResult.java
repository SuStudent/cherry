package org.sustudent.cherry.common.core.model;

import java.io.Serializable;
import org.apache.http.HttpStatus;

public class ResponseResult<T> implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * 请求结果
   */
  private Boolean success;

  /**
   * Http 状态码（请求否成功通过 success判断）
   */
  private Integer status;

  /**
   * 提示消息
   */
  private String msg;

  /**
   * 数据对象
   */
  private T data;


  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public ResponseResult() {
  }

  public ResponseResult(Boolean success, Integer status, String msg, T data) {
    super();
    this.success = success;
    this.status = status;
    this.msg = msg;
    this.data = data;
  }

  public static <T> ResponseResult<T> success(T data) {
    return new ResponseResult<T>(true, HttpStatus.SC_OK, "OK", data);
  }

  public static ResponseResult<?> success() {
    return success(null);
  }

  public static <T> ResponseResult<T> error(Integer status, String msg) {
    return new ResponseResult<T>(false, status, msg, null);
  }

  public static ResponseResult<?> error(String msg) {
    return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
  }

  @Override
  public String toString() {
    return "ResponseResult [success=" + success + ", status=" + status + ", msg=" + msg + ", data="
        + data + "]";
  }

}