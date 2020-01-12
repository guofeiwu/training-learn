package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.OtherColor;
import com.guofei.wu.springannotation.bean.Yellow;
import com.guofei.wu.springannotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-11 19:52
 * @since v3.0
 */
@Configuration
@ComponentScan(
        value = {"com.guofei.wu.springannotation.dao",
                "com.guofei.wu.springannotation.controller",
                "com.guofei.wu.springannotation.service",
        "com.guofei.wu.springannotation.bean"})
@Import(value = {OtherColor.class})
public class MainConfig_Autowired {


    /**
     * 一、 @Autowired 自动装配
     * 1、默认是通过类型去获取bean applicatonContext.getBean(BookDao.class);
     * 2、若是存在类型一样的多个bean，会将属性名称作为组件id在容器中查找
     * applicationContext.getBean("bookDao")
     * 3、可以通过@Qualifier 指定要装配的bean
     * 4、使用了自动注入，就必须把Bean给注入，否则回报错，可以通过设置require= false
     * 5、使用@Primary ：spring 默认使用首选Bean，也可以继续是用@Qualifier指定,@Qualifier>@Primary
     * <p>
     * 二、使用@Resource（JSR250） @Inject（JSR330）Java规范
     *
     * @return
     * @Resource 可以和@Autowired一样有自动装配的功能；默认是按照组件名称进行装配的；
     * 不支持@Primary和@Autowired的require=false的功能
     * @Inject 需要倒入javax.inject包，和@Autowired功能一样，但是没有require=false的功能；
     *
     * 三、可以将@Autowired标注在方法上，构造函数，字段，参数，注解上
     */

    @Primary
    @Bean(value = "bookDao2")
    public BookDao bookDao() {
        BookDao bookDao = new BookDao();
        bookDao.setLabel(2);
        return bookDao;
    }
}
