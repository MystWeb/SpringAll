package com.proaimltd.mybatis.config;

import com.proaimltd.mybatis.common.enums.EnumDatasource;
import com.proaimltd.mybatis.config.datasource.DynamicDataSourceContextHolder;
import com.proaimltd.mybatis.config.datasource.DynamicRoutingDatasource;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库读写分离配置
 *
 * @author: ziming.xing
 * @date: 2019/7/18 21:56
 */
@Slf4j
@Configuration
@MapperScan(value = {"com.proaimltd.mybatis.model.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class DatasourceConfig implements TransactionManagementConfigurer {
    private static final String MAPPER_LOCATION = "classpath*:mapper/**/*Mapper.xml";

    @Autowired
    private AppConfigBean appConfigBean;

    @Bean(name = "READ")
    DataSource readDataSource() {
        HikariDataSource dataSource = hikariDataSource();
        dataSource.setJdbcUrl(appConfigBean.getJdbcUrl1());
        dataSource.setUsername(appConfigBean.getJdbcUsername1());
        dataSource.setPassword(appConfigBean.getJdbcPassword1());
        dataSource.setDriverClassName(appConfigBean.getJdbcDriverclassname1());
        return setupHikari(dataSource);
    }

    @Bean(name = "WRITE")
    DataSource writeDataSource() {
        HikariDataSource dataSource = hikariDataSource();
        dataSource.setJdbcUrl(appConfigBean.getJdbcUrl2());
        dataSource.setUsername(appConfigBean.getJdbcUsername2());
        dataSource.setPassword(appConfigBean.getJdbcPassword2());
        dataSource.setDriverClassName(appConfigBean.getJdbcDriverclassname2());
        return setupHikari(dataSource);
    }

    @Bean(name = "hikariDS")
    public HikariDataSource hikariDataSource() {
        return new HikariDataSource();
    }

    private DataSource setupHikari(HikariDataSource dataSource) {
        dataSource.setConnectionTestQuery(appConfigBean.getJdbcConnectionTestQuery());
        dataSource.setConnectionTimeout(appConfigBean.getJdbcConnectionTimeout());
        dataSource.setIdleTimeout(appConfigBean.getJdbcIdleTimeout());
        dataSource.setMaxLifetime(appConfigBean.getJdbcMaxLifetime());
        dataSource.setMaximumPoolSize(appConfigBean.getJdbcMaxPoolSize());
        return dataSource;
    }

    @Bean(name = "dynamicDatasource")
    DataSource dynamicDatasource() {
        DynamicRoutingDatasource dynamicRoutingDatasource = new DynamicRoutingDatasource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(EnumDatasource.READ.name(), readDataSource());
        dataSourceMap.put(EnumDatasource.WRITE.name(), writeDataSource());

        dynamicRoutingDatasource.setDefaultTargetDataSource(writeDataSource());
        dynamicRoutingDatasource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.datasourceKeys.addAll(dataSourceMap.keySet());
        return dynamicRoutingDatasource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicDatasource") DataSource dataSource) throws SQLException {

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            factoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
            factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            return factoryBean.getObject();
        } catch (Exception e) {
            log.warn("getSqlSessionFactory failed, errorMessage:{}", e);
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dynamicDatasource());
    }
}
