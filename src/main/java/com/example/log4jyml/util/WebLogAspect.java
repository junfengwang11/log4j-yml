package com.example.log4jyml.util;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class WebLogAspect {

    private static final Logger log1 = LogUtils.getBussinessLogger();
  /** 两个..代表所有子目录，最后括号里的两个..代表所有参数 */
  @Pointcut("" + "execution(public * com.example.log4jyml..controller.*.*(..))")
  public void logPointCut() {}

  @Before("logPointCut()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes =
        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    // 记录下请求内容
      log1.info("请求地址 : " + request.getRequestURL().toString());
      log1.info("HTTP METHOD : " + request.getMethod());
      log1.info("IP : " + request.getRemoteAddr());
      log1.info(
        "CLASS_METHOD : "
            + joinPoint.getSignature().getDeclaringTypeName()
            + "."
            + joinPoint.getSignature().getName());
      log1.info("参数 : " + Arrays.toString(joinPoint.getArgs()));
  }
  /** returning的值和doAfterReturning的参数名一致 */
  @AfterReturning(returning = "ret", pointcut = "logPointCut()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
      log1.info("返回值 : " + ret);
  }

  @Around("logPointCut()")
  public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    long startTime = System.currentTimeMillis();
    // ob 为方法的返回值
    Object ob = pjp.proceed();
      log1.info("耗时 : " + (System.currentTimeMillis() - startTime));
    return ob;
  }
}
