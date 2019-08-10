package com.proaimltd.mybatis.restful.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * @author: ziming.xing
 * @date: 2019/8/10 16:50
 */
@Slf4j
@Configuration
@EnableSwagger2
// @ConditionalOnExpression注解，用户是否实例化本类，用于是否启用Swagger的判断（生产环境需要屏蔽Swagger）
/*@ConditionalOnExpression("${swagger.enable:true}")*/
public class SwaggerConfig {

    private Contact contact = new Contact(
            "Myst",
            "http://github.com/MystAndCode",
            "158563862@qq.com");

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .contact(contact)
                .title("用户角色权限 API")
                .description("Mybatis 1对1，1对多，多对多相关接口API文档")
                .version("1.0").build();
    }

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
                .useDefaultResponseMessages(false);

        docket = docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.proaimltd.mybatis.restful.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

}