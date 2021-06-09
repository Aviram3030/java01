package com.example.springrobots.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RobotBeanProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        Class<? extends Object> clz = bean.getClass();
        try {
            Method method = clz.getMethod("getName", String.class);
            method.invoke(bean);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
