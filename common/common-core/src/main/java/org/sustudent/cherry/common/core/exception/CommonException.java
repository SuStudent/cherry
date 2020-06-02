package org.sustudent.cherry.common.core.exception;


public class CommonException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CommonException() {
    super();
  }

  public CommonException(String msg) {
    super(msg);
  }

  public CommonException(Throwable throwable) {
    super(throwable);
  }

  public CommonException(Throwable throwable, String msg) {
    super(throwable);
  }
}
