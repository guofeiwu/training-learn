package com.guofei.wu.springannotation.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 21:34
 * @since v3.0
 */
public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {


        // 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 获取环境变量
        Environment environment = context.getEnvironment();

        // bean定义注册
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取资源加载器
        ResourceLoader resourceLoader = context.getResourceLoader();

        String property = environment.getProperty("os.name");
        if (property.contains("Mac")) {
            return true;
        }
        return false;
    }
}
