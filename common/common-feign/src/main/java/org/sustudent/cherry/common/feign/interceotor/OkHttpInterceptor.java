package org.sustudent.cherry.common.feign.interceotor;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
public class OkHttpInterceptor implements Interceptor {

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    log.debug(String.format("sending %s request %s%n%s", request.method(),
        request.url(), request.headers()));
    return chain.proceed(request);
  }
}
