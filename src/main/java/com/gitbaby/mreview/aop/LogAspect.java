package com.gitbaby.mreview.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j2
@Component
public class LogAspect {
  @Before("execution(* *..*.ReviewController.*(..))") // 비포는 반환 x 어라운드는 무조건 반환. 파라미터는 조인포인트 타입
  public void beforeLog(JoinPoint joinPoint) {
    log.info("-------------------------" + joinPoint.getSignature().getName()); // 메서드의 선언부 : 시그니처
    Arrays.stream(joinPoint.getArgs()).forEach(log::info);
  }
}
