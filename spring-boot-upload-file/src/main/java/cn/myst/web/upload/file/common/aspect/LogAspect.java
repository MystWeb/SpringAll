package cn.myst.web.upload.file.common.aspect;

import cn.myst.web.upload.file.common.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 日志切面
     *
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* cn.myst.web.*.*.*(..)))")
    public void logPoint(JoinPoint joinPoint, Throwable ex) {
        LogUtils.logToFile(joinPoint, ex);
    }
}