package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.Person;
import com.guofei.wu.springannotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:20
 * @since v3.0
 */
@Configuration
//@ComponentScan(basePackages = {"com.guofei.wu.springannotation"})
//@ComponentScan(value = {"com.guofei.wu.springannotation"},
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
//        })
//@ComponentScan(value = {"com.guofei.wu.springannotation"},
//        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)},
//        useDefaultFilters = false)
@ComponentScans(value = {
        @ComponentScan(value = {"com.guofei.wu.springannotation"},
                useDefaultFilters = false,
                includeFilters = {
//                        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class),
//                        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BookDao.class),
                        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
                }
        )
})
public class MainConfig {


    @Bean
    public Person person() {
        return new Person("tom", 20);
    }

}
