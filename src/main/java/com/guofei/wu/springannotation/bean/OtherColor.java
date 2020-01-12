package com.guofei.wu.springannotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.util.StringValueResolver;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-12 15:27
 * @since v3.0
 */
public class OtherColor implements ApplicationContextAware, ApplicationEventPublisherAware
        , BeanClassLoaderAware, BeanFactoryAware, BeanNameAware, EmbeddedValueResolverAware, EnvironmentAware,
        ImportAware, NotificationPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    private AnnotationMetadata importMetadata;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        applicationEventPublisher.publishEvent("name is Mason");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

        this.importMetadata = importMetadata;
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {

    }
}
