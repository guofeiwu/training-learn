package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.aop.MathCalculator;
import com.guofei.wu.springannotation.config.MainConfigAOP;
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
public class MainConfig_AOP {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigAOP.class);


    /**
     * 1、业务逻辑类和切面类都加入到spring容器中，使用@Aspect告诉Spring哪个是切面类
     * 2、在切面的通知方法上面加上通知注解，告诉Spring容器，使用到切入点表达式
     * 3、开启注解的AOP模式：@EnableAspectJAutoProxy
     */
    @Test
    public void test1() {
        MathCalculator bean = ac.getBean(MathCalculator.class);
        bean.div(1,0);

    }
}
