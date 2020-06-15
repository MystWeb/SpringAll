package cn.myst.web.jaeger.common.utils;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Slf4j
@Component
public class TracerUtils {
    @Autowired
    private Tracer tracer;

    @Autowired
    private SpringContextUtils contextUtils;

    /**
     * 增强当前span，从Http上下文中提取信息补充到span中
     *
     * @param request  Http请求
     * @param response Http响应
     * @param handler  方法处理器
     */
    public void enhanceCurrentSpan(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        if (tracer == null) {
            log.warn("Tracer is not initialized!");
            return;
        }
        // 方法解析
        Method method = handler.getMethod();
        String methodName = method.getName();
        String className = handler.getBean().getClass().getName();
        Map<String, String[]> params = request.getParameterMap();

        // Span 内容增强
        Span span = tracer.activeSpan();
        if (span == null) {
            return;
        }
        span.setTag("参数", JsonUtils.toJsonString(params));
        span.setTag("方法", methodName);
        span.setTag("类", className);
        span.setTag("配置文件", String.join(",", contextUtils.getActiveProfiles()));

        // 添加Swagger API说明到span
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            span.setTag("API", annotation.value());
        }
    }

    /**
     * 当前span新增tag
     *
     * @param key   tag key
     * @param value tag value
     */
    public void addTag(String key, String value) {
        if (tracer == null) {
            return;
        }

        // Span 内容增强
        Span span = tracer.activeSpan();
        if (span != null) {
            span.setTag(key, value);
        }
    }

    /**
     * 新建span
     *
     * @param operationName 操作名称
     * @param tags          标签
     * @param logs          日志
     */
    public Span startSpan(String operationName, Map<String, String> tags, List<String> logs) {
        if (tracer == null) {
            log.warn("Tracer is not initialized!");
            return null;
        }

        Tracer.SpanBuilder builder = tracer.buildSpan(operationName);
        builder.asChildOf(tracer.activeSpan());
        builder.withTag("配置文件", String.join(",", contextUtils.getActiveProfiles()));
        if (!CollectionUtils.isEmpty(tags)) {
            for (Map.Entry<String, String> entry : tags.entrySet()) {
                builder = builder.withTag(entry.getKey(), entry.getValue());
            }
        }
        Span span = builder.start();

        if (!CollectionUtils.isEmpty(logs)) {
            for (String logStr : logs) {
                span.log(logStr);
            }
        }

        return span;
    }

    /**
     * 停止span
     *
     * @param span span
     */
    public void stopSpan(Span span) {
        if (span != null) {
            span.finish();
        }
    }

    public Span getActiveSpan() {
        if (tracer == null) {
            return null;
        }

        return tracer.activeSpan();
    }
}
