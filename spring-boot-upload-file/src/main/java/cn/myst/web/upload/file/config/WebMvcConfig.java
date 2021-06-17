package cn.myst.web.upload.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理跨域请求的配置类
 *
 * @author ziming.xing
 * Create Date：2020/12/24
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui/")
                .setViewName("forward:/swagger-ui/index.html");
    }
}
