package com.proaimltd.mybatis.config;

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

/**
 * @author: ziming.xing
 * @date: 2019/7/11 14:57
 */
@Configuration
@EnableSwagger2
// @ConditionalOnExpression注解，用户是否实例化本类，用于是否启用Swagger的判断（生产环境需要屏蔽Swagger）
public class SwaggerConfig {
    private static final String VERSION = "1.0";

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.proaimltd.mybatis.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private Contact contact = new Contact(
            "Myst",
            "http://github.com/MingAndAKM",
            "158563862@qq.com");

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot Mybatis API")
                .contact(contact)
                .description("Spring boot Mybatis ")
                .version(VERSION)
                .build();
    }
}
