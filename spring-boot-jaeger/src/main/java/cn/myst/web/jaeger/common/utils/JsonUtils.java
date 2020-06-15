package cn.myst.web.jaeger.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json 工具类
 *
 * @author ziming.xing
 * Create Time：2020/6/15
 */
public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 忽略多余字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将json字符串解析为对象
     *
     * @param json json字符串
     * @param clz  对象类型
     * @param <T>  对象泛型
     * @return 解析结果对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T parse(String json, Class<T> clz) {
        if (json == null) {
            return null;
        }

        if (String.class.equals(clz)) {
            return (T) json;
        }

        T result = null;
        try {
            result = mapper.readValue(json, clz);
        } catch (IOException e) {
            log.warn("Parse json string failed: {}", json, e);
        }
        return result;
    }

    /**
     * 将json字符串解析为对象，用于解析泛型对象，如List<Integer>
     *
     * @param json json字符串
     * @param tf   对象类型
     * @param <T>  对象泛型
     * @return 解析结果对象
     */
    public static <T> T parse(String json, TypeReference<T> tf) {
        if (json == null) {
            return null;
        }

        T result = null;
        try {
            result = mapper.readValue(json, tf);
        } catch (IOException e) {
            log.warn("Parse json string failed: {}", json, e);
        }
        return result;
    }

    /**
     * 将对象转为Json字符串
     *
     * @param obj 对象
     * @return json字符串
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        }

        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.warn("Encode object failed: {}", obj, e);
        }
        return result;
    }
}
