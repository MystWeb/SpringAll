package cn.myst.web.jaeger.service.interceptor;

import cn.myst.web.jaeger.common.utils.JsonUtils;
import cn.myst.web.jaeger.common.utils.TracerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {
    private final TracerUtils tracer;

    public LoggerInterceptor(TracerUtils tracer) {
        this.tracer = tracer;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            tracer.enhanceCurrentSpan(request, response, (HandlerMethod) handler);
            tracer.addTag("用户", JsonUtils.toJsonString(getUser()));
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
    }

    private String getUser() {
        return "用户A";
    }
}
