package com.mycom.myapp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// com.mycom.myapp.repository 의 모든 메소드가 호출될 때 기본 로그를 남기는 Aspect
// repository 패키지의 메소드는 모두 login한 상태에서만 호출 가능
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    // Session
    private final HttpSession session;

    @Pointcut(value = "execution( * com.mycom.myapp.repository.*.*(..) )")
    private void logPointcut() {}

    // 로그인 한 사용자가 호출할 경우, 호출한 사용자의 이름과 시각을 출력.
    @Before("logPointcut()")
    public void logRepositoryMethodCall(JoinPoint joinPoint) {// joinpoint 는 위 포인트컷의 대상중 실제로 호출된 메소드 진입점
        String username = (String) session.getAttribute("username");
        if (username == null)  return;

        String methodName = joinPoint.getSignature().getName(); // 메소드 명
        log.info("User [" + username + "] 가 Method : " + methodName + " 을 " + LocalDateTime.now() + "에 호출했습니다.");
    }
}
