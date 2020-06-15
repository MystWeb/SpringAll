package cn.myst.web.jaeger.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger配置类
 *
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 版本号
    public static final String VERSION = "1.0";
    // URL前缀
    public static final String URL_PREFIX = "/api";
    // URL版本号
    public static final String URL_VERSION = "/" + VERSION;

    // JWT Token前缀
    public static final String TOKEN_PROFILE = "Bearer ";
    // JWT 授权
    public static final String AUTHORIZATION = "Authorization";

    private static final String DEFAULT_INCLUDE_PATTERN = URL_PREFIX + URL_VERSION + "/.*";

    @Bean
    public Docket api() {
        log.debug("Starting Swagger");
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .ignoredParameterTypes(SpringDataWebProperties.Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(apiKey())
                .useDefaultResponseMessages(false);

        docket = docket.select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        return docket;
    }

    Contact contact = new Contact(
            "邢子铭",
            "http://github.com/MystWeb",
            "158563862@qq.com");

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .contact(contact)
                .version(VERSION)
                .build();
    }

    private List<ApiKey> apiKey() {
        List<ApiKey> keys = new ArrayList<>();
        keys.add(new ApiKey("JWT", AUTHORIZATION, "header"));
        return keys;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }
}
