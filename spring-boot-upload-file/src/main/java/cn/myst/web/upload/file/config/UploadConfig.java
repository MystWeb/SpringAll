package cn.myst.web.upload.file.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Getter
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class UploadConfig {

    @Value("${upload.path}")
    private String uploadPath;
}
