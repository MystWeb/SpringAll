package com.proaimltd.mybatis.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.proaimltd.mybatis.template.model.mapper")
@SpringBootApplication
public class SpringBootMybatisTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisTemplateApplication.class, args);
    }

}
