package com.yaoyili.utils;

import org.springframework.context.ApplicationContext;

public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext () {
        return applicationContext;
    }

    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    public static Object getBean(Class bean) {
        return applicationContext.getBean(bean);
    }
}