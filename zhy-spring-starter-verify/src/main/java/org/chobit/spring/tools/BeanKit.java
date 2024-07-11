package org.chobit.spring.tools;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author rui.zhang
 */
public final class BeanKit {


    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    private static ApplicationContext context;


    public static void init(ApplicationContext context) {
        if (null == BeanKit.context) {
            BeanKit.context = context;
        }
    }


    public static boolean isInit() {
        return null != BeanKit.context;
    }


    /**
     * 获取Bean实例
     *
     * @param c   类名
     * @param <T> bean实例类型
     * @return Bean实例
     */
    public static <T> T get(Class<T> c) {
        if (null == context) {
            return null;
        }
        return context.getBean(c);
    }


    /**
     * 获取Bean对象
     *
     * @param beanName bean名称
     * @return Bean对象
     */
    public static Object getBean(String beanName) {
        if (null == context) {
            return null;
        }
        return context.getBean(beanName);
    }

    /**
     * 获取Bean对象
     *
     * @param beanName bean名称
     * @return Bean对象
     */
    public static <T> T getBean(String beanName, Class<T> requiredType) {
        if (null == context) {
            return null;
        }
        return context.getBean(beanName, requiredType);
    }


    /**
     * 根据方法名获取方法
     *
     * @param beanObject bean对象
     * @param methodName 方法名称
     * @return 方法对象
     */
    public static Method getMethod(Object beanObject, String methodName) {
        //利用bean获取class对象，进而获取本类以及父类或者父接口中所有的公共方法(public修饰符修饰的)
        Method[] methods = beanObject.getClass().getMethods();
        //循环遍历，获取指定方法名的方法
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase(methodName)) {
                return method;
            }
        }
        return null;
    }


    private BeanKit() {
    }

}
