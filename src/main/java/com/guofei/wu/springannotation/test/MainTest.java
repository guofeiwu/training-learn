package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.bean.Blue;
import com.guofei.wu.springannotation.bean.Person;
import com.guofei.wu.springannotation.config.MainConfig;
import com.guofei.wu.springannotation.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:22
 * @since v3.0
 */
public class MainTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig2.class);


    @Test
    public void testImport() {
        printBeanName(ac);
        Object blue = ac.getBean(Blue.class);
        System.out.println(blue);

        Object colorFactoryBean = ac.getBean("colorFactoryBean");
        System.out.println("bean的类型:" + colorFactoryBean.getClass());
        System.out.println(colorFactoryBean);
        Object colorFactoryBean2 = ac.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean == colorFactoryBean2);

        Object prototypeBean = ac.getBean("&colorFactoryBean");

        System.out.println("prototypeBean:" + prototypeBean.getClass());


    }


    private void printBeanName(ApplicationContext ac) {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig2.class);

        ConfigurableEnvironment env = ac.getEnvironment();

        String property = env.getProperty("os.name");
        System.out.println("os.name:" + property);

        Map<String, Person> beanNamesForType = ac.getBeansOfType(Person.class);
        for (Person value : beanNamesForType.values()) {
            System.out.println(value);
        }
    }

    @Test
    public void test01() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = ac.getBean(Person.class);
        System.out.println(bean);

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void test02() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig2.class);
        System.out.println("容器创建完成。。。");

        Person person = (Person) ac.getBean("person");
        Person person2 = (Person) ac.getBean("person");
        System.out.println(person == person2);
    }

}
