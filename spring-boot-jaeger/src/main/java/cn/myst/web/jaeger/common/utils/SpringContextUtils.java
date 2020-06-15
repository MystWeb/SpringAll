package cn.myst.web.jaeger.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 动态获取bean的工具类，可用于在线程里面获取bean
 *
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Component("springContextUtils")
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 根据Bean名称获取Bean对象
     *
     * @param name Bean名称
     * @return 对应名称的Bean对象
     */
    public static Object getBean(String name) {
        return context.getBean(name);
    }

    /**
     * 根据Bean的类型获取对应的Bean
     *
     * @param requiredType Bean类型
     * @return 对应类型的Bean对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    /**
     * 根据Bean名称获取指定类型的Bean对象
     *
     * @param name         Bean名称
     * @param requiredType Bean类型（可为空）
     * @return 获取对应Bean名称的指定类型Bean对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return context.getBean(name, requiredType);
    }

    /**
     * 判断是否包含对应名称的Bean对象
     *
     * @param name Bean名称
     * @return 包含：返回true，否则返回false。
     */
    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    /**
     * 获取对应Bean名称的类型
     *
     * @param name Bean名称
     * @return 返回对应的Bean类型
     */
    public static Class<?> getType(String name) {
        return context.getType(name);
    }

    /**
     * 获取上下文对象，可进行各种Spring的上下文操作
     *
     * @return Spring上下文对象
     */
    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取启动环境列表
     *
     * @return 环境列表
     */
    public String[] getActiveProfiles() {
        return context.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取启动应用名称
     *
     * @return 应用名称
     */
    public String getApplicationName() {
        return context.getApplicationName();
    }

}
