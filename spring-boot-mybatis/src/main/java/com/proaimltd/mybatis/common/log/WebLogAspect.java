package com.proaimltd.mybatis.common.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author: ziming.xing
 * @date: 2019/7/12 20:21
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    private Gson gson = new Gson();

    @AfterThrowing(pointcut = "webLog()", throwing = "exception")
    public void afterException(Exception exception) {
        log.info(gson.toJson(exception));
    }

    @Pointcut("execution(* com.proaimltd.*.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void beforeRequest(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("------Request Begin------");
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void afterReturning(Object ret) {
        log.info("Elapsed Time:" + (System.currentTimeMillis() - startTime.get()));
        log.info("Response:" + gson.toJson(ret));
        log.info("------Request Completed------");
    }
}
