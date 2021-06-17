package cn.myst.web.upload.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ziming.xing
 * Create Date：2020/12/25
 */
@Configuration
public class AppConfigBean {
    public static String path;

    @Value("${upload.path}")
    public void setPath(String path) {
        AppConfigBean.path = path;
    }
}
