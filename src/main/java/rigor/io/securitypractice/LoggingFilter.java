package rigor.io.securitypractice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingFilter implements javax.servlet.Filter {

  private final Log log = LogFactory.getLog(getClass());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//    boolean expression = servletRequest instanceof HttpServletResponse;
//    System.out.println(expression);
//    System.out.println(servletRequest);
//    Assert.isTrue(expression, "assuming http request");

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    String uri = httpServletRequest.getRequestURI();
    this.log.info("new request for " + uri);
    long time = System.currentTimeMillis();
    filterChain.doFilter(httpServletRequest, servletResponse);
    long delta = System.currentTimeMillis() - time;
    this.log.info("request for " + uri + " took " + delta + " ms");
  }

  @Override
  public void destroy() {

  }
}
