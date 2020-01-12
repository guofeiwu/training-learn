package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.bean.Person;
import com.guofei.wu.springannotation.config.MainConfigOfLifeCycle;
import com.guofei.wu.springannotation.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_propertyValue {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);

    @Test
    public void test1() {
        printBeanName(ac);

        Person person = (Person) ac.getBean("person");


        ConfigurableEnvironment environment = ac.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);


        System.out.println(person);
    }

    private void printBeanName(ApplicationContext ac) {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
