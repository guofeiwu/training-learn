package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.Car;
import com.guofei.wu.springannotation.bean.Cat;
import com.guofei.wu.springannotation.bean.LastBoss;
import org.junit.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * bean的生命周期
 * <p>
 * bean实例的创建：
 * 单实例：容器初始化
 * 多实例：第一次获取实例的时候创建
 * <p>
 * bean的初始化方式
 * 单实例： 对象创建完成，bean属性设置值
 * 多实例： 第一次获取实例的时候初始化
 * <p>
 * bean的销毁
 * 单实例：容器关闭的时候销毁
 * --------过程-----
 * car ... constructor...
 * car ... init...
 * 容器初始化...
 * car ... destory...
 * 容器关闭...
 * <p>
 * 多实例：不会自动的销毁，需要自己手动去销毁
 * --------过程-----
 * 容器初始化...
 * car ... constructor...
 * car ... init...
 * 容器关闭...
 * <p>
 * <p>
 * 1、制定init方法和destroy方法
 * 通过@Bean 制定init-method和destroy-method
 * 2、通过使用InitializingBean初始化逻辑  DisposableBean销毁逻辑
 * 3、JSR250：
 * 初始化：@PostConstruct bean创建完成并且赋值后执行
 * 销毁：@PreDestroy bean被销毁之前执行
 * 4、使用 @BeanPostProcessor bean的后置处理器
 * <p>
 * 初始化：postProcessBeforeInitialization 在任何的初始化方法之前 InitializingBean's {@code afterPropertiesSet} or a custom init-method
 * 销毁： postProcessAfterInitialization  在任何的销毁方法之后  like InitializingBean's {@code afterPropertiesSet} or a custom init-method
 *
 * @author guofei.wu
 * @version v3.0
 * @BeanPostProcessor 原理
 * <p>
 * 初始化bean
 * populateBean(beanName, mbd, instanceWrapper); 属性设置
 * exposedObject = initializeBean(beanName, exposedObject, mbd);
 * {
 * wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * // 调用初始化方法
 * invokeInitMethods(beanName, wrappedBean, mbd);
 * wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * }
 * 执行BeanPostProcessor：拿到所有的后置处理器，for循环执行
 * {
 * for (BeanPostProcessor processor : getBeanPostProcessors()) {
 * Object current = processor.postProcessBeforeInitialization(result, beanName);
 * if (current == null) {
 * return result;
 * }
 * result = current;
 * }
 * return result;
 * }
 * }
 * @date 2020-01-10 22:38
 * @since v3.0
 */

@Configuration
@ComponentScan(value = {"com.guofei.wu.springannotation.bean"})
public class MainConfigOfLifeCycle {


    @Bean(initMethod = "init", destroyMethod = "destroy")
//    @Scope("prototype")
    public Car car() {
        return new Car();
    }

    @Bean
    public Cat cat() {
        return new Cat("Jerry");
    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public LastBoss lastBoss() {
        return new LastBoss("lastBigBoss");
    }
}
