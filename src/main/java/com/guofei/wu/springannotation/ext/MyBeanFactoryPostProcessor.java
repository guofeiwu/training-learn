package com.guofei.wu.springannotation.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-20 20:22
 * @since v3.0
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor... postProcessBeanFactory...");
        System.out.println("bean的个数：" + beanFactory.getBeanDefinitionCount());
        System.out.println("bean名称的个数：" + Arrays.asList(beanFactory.getBeanDefinitionNames()));
    }
}
