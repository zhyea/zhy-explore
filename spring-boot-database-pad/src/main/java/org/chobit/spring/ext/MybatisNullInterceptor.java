package org.chobit.spring.ext;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * @author robin
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisNullInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisNullInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];

        if (null != parameter) {
            padNullFields(parameter);
        }

        Executor executor = (Executor) invocation.getTarget();
        return executor.update(ms, parameter);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }


    private void padNullFields(Object parameter) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(parameter.getClass());

        for (PropertyDescriptor pd : targetPds) {
            try {
                padNullField(pd, parameter);
            } catch (Exception e) {
                logger.error("pad null fields failed", e);
            }
        }
    }


    private void padNullField(PropertyDescriptor pd, Object parameter)
            throws InvocationTargetException, IllegalAccessException {
        Method readMethod = pd.getReadMethod();
        Method writeMethod = pd.getWriteMethod();

        if (null == readMethod || null == writeMethod) {
            return;
        }
        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
            readMethod.setAccessible(true);
        }
        Object value = readMethod.invoke(parameter);
        if (null != value) {
            return;
        }

        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
            writeMethod.setAccessible(true);
        }

        Class<?> clazz = pd.getPropertyType();

        setDefaultValue(parameter, clazz, writeMethod);
    }


    /**
     * 为字段设置默认值
     *
     * @param parameter   参数
     * @param fieldClass  字段类型
     * @param writeMethod 字段写方法
     */
    private void setDefaultValue(Object parameter, Class<?> fieldClass, Method writeMethod)
            throws InvocationTargetException, IllegalAccessException {
        if (fieldClass.isAssignableFrom(String.class)) {
            writeMethod.invoke(parameter, "");
        }
        if (fieldClass.isAssignableFrom(Boolean.class)) {
            writeMethod.invoke(parameter, false);
        }
        if (fieldClass.isAssignableFrom(Byte.class)) {
            writeMethod.invoke(parameter, (byte) 0);
        }
        if (fieldClass.isAssignableFrom(Integer.class)) {
            writeMethod.invoke(parameter, 0);
        }
        if (fieldClass.isAssignableFrom(Long.class)) {
            writeMethod.invoke(parameter, 0L);
        }
        if (fieldClass.isAssignableFrom(LocalDateTime.class)) {
            writeMethod.invoke(parameter, LocalDateTime.now());
        }
    }
}
