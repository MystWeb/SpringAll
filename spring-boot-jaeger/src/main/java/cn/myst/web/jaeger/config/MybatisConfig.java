package cn.myst.web.jaeger.config;

import cn.myst.web.jaeger.common.utils.TracerUtils;
import cn.myst.web.jaeger.service.interceptor.SqlLoggingInterceptor;
import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
// 扫描Mapper接口
@MapperScan("cn.myst.web.**.mapper")
@Configuration
@ConditionalOnBean({SqlSessionFactory.class})
@EnableConfigurationProperties({PageHelperProperties.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MybatisConfig {

    private final List<SqlSessionFactory> sqlSessionFactoryList;

    private final PageHelperProperties properties;

    private final TracerUtils tracerUtils;

    @Bean
    @ConfigurationProperties(prefix = "pagehelper")
    public Properties pageHelperProperties() {
        return new Properties();
    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        SqlLoggingInterceptor sqlLoggingInterceptor = new SqlLoggingInterceptor(tracerUtils);
        Properties properties = new Properties();
        properties.putAll(this.pageHelperProperties());
        properties.putAll(this.properties.getProperties());
        interceptor.setProperties(properties);

        for (SqlSessionFactory sqlSessionFactory : this.sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
            sqlSessionFactory.getConfiguration().addInterceptor(sqlLoggingInterceptor);
        }
    }
}
