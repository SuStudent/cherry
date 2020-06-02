package org.sustudent.cherry.common.security.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName CherryFilterSecurityInterceptor.java
 * @Description CherryFilterSecurityInterceptor
 * @createTime 2020/06/02/ 13:53:00
 */
public class CherryFilterSecurityInterceptor extends AbstractSecurityInterceptor implements
    Filter {
  // ~ Static fields/initializers
  // =====================================================================================

  private static final String FILTER_APPLIED =
      "__spring_security_filterSecurityInterceptor_filterApplied_cherry";

  // ~ Instance fields
  // ================================================================================================

  private FilterInvocationSecurityMetadataSource securityMetadataSource;
  private boolean observeOncePerRequest = true;

  // ~ Methods
  // ========================================================================================================

  /**
   * Not used (we rely on IoC container lifecycle services instead)
   *
   * @param arg0 ignored
   * @throws ServletException never thrown
   */
  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  /**
   * Not used (we rely on IoC container lifecycle services instead)
   */
  @Override
  public void destroy() {
  }

  /**
   * Method that is actually called by the filter chain. Simply delegates to the {@link
   * #invoke(FilterInvocation)} method.
   *
   * @param request the servlet request
   * @param response the servlet response
   * @param chain the filter chain
   * @throws IOException if the filter chain fails
   * @throws ServletException if the filter chain fails
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    FilterInvocation fi = new FilterInvocation(request, response, chain);
    invoke(fi);
  }

  public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
    return this.securityMetadataSource;
  }

  @Override
  public SecurityMetadataSource obtainSecurityMetadataSource() {
    return this.securityMetadataSource;
  }

  public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
    this.securityMetadataSource = newSource;
  }

  @Override
  public Class<?> getSecureObjectClass() {
    return FilterInvocation.class;
  }

  public void invoke(FilterInvocation fi) throws IOException, ServletException {
    if ((fi.getRequest() != null)
        && (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
        && observeOncePerRequest) {
      // filter already applied to this request and user wants us to observe
      // once-per-request handling, so don't re-do security checking
      fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
    } else {
      // first time this request being called, so perform security checking
      if (fi.getRequest() != null) {
        fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
      }

      InterceptorStatusToken token = super.beforeInvocation(fi);

      try {
        fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
      } finally {
        super.finallyInvocation(token);
      }

      super.afterInvocation(token, null);
    }
  }

  /**
   * Indicates whether once-per-request handling will be observed. By default this is
   * <code>true</code>, meaning the <code>FilterSecurityInterceptor</code> will only execute
   * once-per-request. Sometimes users may wish it to execute more than once per request, such as
   * when JSP forwards are being used and filter security is desired on each included fragment of
   * the HTTP request.
   *
   * @return <code>true</code> (the default) if once-per-request is honoured, otherwise
   * <code>false</code> if <code>FilterSecurityInterceptor</code> will enforce
   * authorizations for each and every fragment of the HTTP request.
   */
  public boolean isObserveOncePerRequest() {
    return observeOncePerRequest;
  }

  public void setObserveOncePerRequest(boolean observeOncePerRequest) {
    this.observeOncePerRequest = observeOncePerRequest;
  }
}
