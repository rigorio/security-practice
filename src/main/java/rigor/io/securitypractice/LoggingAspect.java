package rigor.io.securitypractice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  private final Log log = LogFactory.getLog(getClass());

  @Around("execution( * rigor.io..*.*(..))")
  public Object log(ProceedingJoinPoint pjp) throws Throwable {

    this.log.info("before " + pjp.toString());
    Object object = pjp.proceed();
    this.log.info("after " + pjp.toString());
    return object;
  }

}
