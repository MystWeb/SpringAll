package com.proaimltd.mybatis.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 两个*代表任意个包,比如可以扫到com.demo.aaa.mapper,也可以扫到com.demo.aaa.bbb.mapper
@MapperScan("com.proaimltd.mybatis.annotation.**.mapper")
public class SpringBootMybatisRelationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisRelationsApplication.class, args);
    }

}
