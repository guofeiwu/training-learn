package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.config.MainConfigProfile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_Profile {
    // AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigProfile.class);


    /**
     * 选择激活profile的方式：
     * 1、通过命令行参数的方式 -Dspring.profiles.active = test
     * 2、通过代码的方式 若是没有指定的Bean会在任何的环境都会起作用
     *
     * @Profile 注解还可以标注在类上
     */


    @Test
    public void test1() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        // 设置要激活的环境
        ac.getEnvironment().setActiveProfiles("test", "dev");
        // 设置主配置类
        ac.register(MainConfigProfile.class);
        // 启动容器
        ac.refresh();


        String[] beanNamesForType = ac.getBeanNamesForType(DataSource.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

    }

    private void printBeanName(ApplicationContext ac) {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
