package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_LifeCycyle {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器初始化...");

        // ac.getBean("car");

        ac.close();
        System.out.println("容器关闭...");
    }
}
