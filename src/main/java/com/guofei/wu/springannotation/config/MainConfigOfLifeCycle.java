package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.Car;
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
 *
 * @author guofei.wu
 * @version v3.0
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
}
