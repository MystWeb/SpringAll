package cn.myst.web.upload.file.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Date;

/**
 * @author ziming.xing
 * Create Date：2020/12/24
 */
@Slf4j
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    // 版本号
    public static final String VERSION = "1.0";

    @Bean
    public Docket createRestApi() {
        log.debug("Starting Swagger");
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(metaInfo())
                .ignoredParameterTypes(SpringDataWebProperties.Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class);

        docket = docket.select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    Contact contact = new Contact(
            "Myst",
            "https://mystweb.cn",
            "158563862@qq.com");

    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
//                .description("文档描述")
                .contact(contact)
                .version(VERSION)
                .build();
    }
}
