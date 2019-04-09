package com.proaim.logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootLoggingApplicationTests {
    Logger logger = LoggerFactory.getLogger(getClass());
    // 记录器
    @Test
    public void contextLoads() {

        // 日志级别
        // 由低到高 trace < debug < info < warn < error
        // 可以调整需要输出的日志级别
        //logging.level.com.study=trace
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");
        System.out.println();
        log.trace("Slf4j-trace...");
        log.debug("Slf4j-debug...");
        log.info("Slf4j-info...");
        log.warn("Slf4j-warn...");
        log.error("Slf4j-error...");
    }

}
