package dev.com.mcp.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

@Configuration
public class ToolRegistrationConfig {

    @Bean
    public List<ToolCallback> toolCallbacks(ListableBeanFactory beanFactory) {
        List<ToolCallback> callbacks = new ArrayList<>();
        for (String beanName : beanFactory.getBeanNamesForAnnotation(Service.class)) {
            Class<?> beanType = beanFactory.getType(beanName);
            if (beanType == null || !hasToolMethods(beanType)) {
                continue;
            }
            Object bean = beanFactory.getBean(beanName);
            callbacks.addAll(Arrays.asList(ToolCallbacks.from(bean)));
        }
        return callbacks;
    }

    private boolean hasToolMethods(Class<?> beanType) {
        Class<?> targetClass = ClassUtils.getUserClass(beanType);
        for (Method method : targetClass.getMethods()) {
            if (AnnotationUtils.findAnnotation(method, Tool.class) != null) {
                return true;
            }
        }
        return false;
    }
}
