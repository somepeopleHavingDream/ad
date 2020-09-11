package org.yangxin.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangxin
 * 2020/05/23 19:31
 */
@SuppressWarnings({"unchecked", "rawtypes", "NullableProblems"})
@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    /**
     * 应用上下文
     */
    private static ApplicationContext applicationContext;

    public static final Map<Class, Object> DATA_TABLE_MAP = new ConcurrentHashMap<>();
//    public static final Map<Class, Object> dataTableMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DataTable.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }

    /**
     * 通过实例名称从应用上下文中获取实例对象
     */
    private static <T> T bean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过字节码对象从应用上下文中拿到相应的对象实例
     */
    private static <T> T bean(Class clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * 通过字节码对象拿到对应对象实例
     */
    public static <T> T of(Class<T> clazz) {
        T instance = (T) DATA_TABLE_MAP.get(clazz);
        if (instance != null) {
            return instance;
        }

        DATA_TABLE_MAP.put(clazz, bean(clazz));
        return (T) DATA_TABLE_MAP.get(clazz);
    }
}
