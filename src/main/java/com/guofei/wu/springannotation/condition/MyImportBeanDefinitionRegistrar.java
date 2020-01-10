package com.guofei.wu.springannotation.condition;

import com.guofei.wu.springannotation.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:07
 * @since v3.0
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    //importingClassMetadata 被@Import注解的类的信息
    // BeanDefinitionRegistry: Bean定义注册类
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        boolean b = registry.containsBeanDefinition("com.guofei.wu.springannotation.bean.Color");
        boolean b1 = registry.containsBeanDefinition("com.guofei.wu.springannotation.bean.Black");
        if (b && b1) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            registry.registerBeanDefinition("rainBow", beanDefinition);
        }
    }
}
