package cn.myst.web.jaeger.service.interceptor;

import cn.myst.web.jaeger.common.utils.TracerUtils;
import io.opentracing.Span;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.*;

import static org.apache.ibatis.mapping.SqlCommandType.*;

/**
 * @author ziming.xing
 * Create Time：2020/6/15
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlLoggingInterceptor implements Interceptor {
    private final TracerUtils tracerUtils;

    private static final Map<SqlCommandType, String> COMMANDS;

    static {
        COMMANDS = new HashMap<>(6);
        COMMANDS.put(UNKNOWN, "UNKNOWN");
        COMMANDS.put(INSERT, "INSERT");
        COMMANDS.put(UPDATE, "UPDATE");
        COMMANDS.put(DELETE, "DELETE");
        COMMANDS.put(SELECT, "SELECT");
        COMMANDS.put(FLUSH, "FLUSH");
    }

    public SqlLoggingInterceptor(TracerUtils tracerUtils) {
        this.tracerUtils = tracerUtils;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        // 获取sql语句
        String sql = getSql(configuration, boundSql);
        String operationName = COMMANDS.get(mappedStatement.getSqlCommandType());

        // 构造标签
        Map<String, String> tags = new HashMap<>(2);
        tags.put("SQL", sql);

        // 创建新的span
        Span span = tracerUtils.startSpan(operationName, tags, null);

        // SQL执行
        Object returnVal = invocation.proceed();

        // span结束
        tracerUtils.stopSpan(span);
        return returnVal;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterObject == null || parameterMappings.size() == 0) {
            return sql;
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        return sql;
    }

    private String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }
}
