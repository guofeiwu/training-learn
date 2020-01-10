package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.Black;
import com.guofei.wu.springannotation.bean.Color;
import com.guofei.wu.springannotation.bean.ColorFactoryBean;
import com.guofei.wu.springannotation.bean.Person;
import com.guofei.wu.springannotation.condition.MacCondition;
import com.guofei.wu.springannotation.condition.MyImportBeanDefinitionRegistrar;
import com.guofei.wu.springannotation.condition.MyImportSelector;
import com.guofei.wu.springannotation.condition.WindowsCondition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 21:01
 * @since v3.0
 */
@Configuration
@Conditional(value = {MacCondition.class})
@Import({Color.class, Black.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {


    /**
     * bean的注册方式
     * <p>
     * 1、通过@Controller@Service 等注解
     * 2、通过@Bean注解
     * 3、使用@Import注解
     * 1)、@Import(需要倒入的组建)
     * 2）、ImportSelector，返回需要导入的组件的全类名
     * 3)、@ImportBeanDefinitionRegistrar:通过ImportBeanDefinitionRegistrar.registerBeanDefinitions() 手动注册bean到容器中
     * 4、通过FactoryBean获取bean对象
     * 1) 默认获取的是工厂Bean调用getObject创建的对象
     * 2） 需要获取工厂bean，需要在给id前面加一个&
     * &colorFactoryBean
     */


    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }


    /**
     * @return
     * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * @see ConfigurableBeanFactory#SCOPE_SINGLETON
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST request 同一个请求中，放到request中
     * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     * session中
     * <p>
     * prototype： 多实例 实在bean获取中的时候去创建对象的
     * <p>
     * singleton： 单实例 在容器初始化的时候就放入到容器中
     * <p>
     * request 同一个请求中，放到request中
     * <p>
     * session 同一个 放到同一个session中
     * <p>
     * 懒加载：lazy 只有当bean第一被获取的时候才创建
     */
//    @Scope(value = "prototype")
    @Scope(value = "singleton")
    @Lazy
    @Bean
    public Person person() {
        System.out.println("向容器中添加bean。。。");
        return new Person("Jack", 20);
    }


    /**
     * @return
     * @Conditional
     */
    @Conditional(value = WindowsCondition.class)
    @Bean("bill")
    public Person person01() {
        return new Person("Bill Gate", 22);
    }

    @Conditional(value = MacCondition.class)
    @Bean("mac")
    public Person person02() {
        return new Person("Alice mac", 22);
    }


}
