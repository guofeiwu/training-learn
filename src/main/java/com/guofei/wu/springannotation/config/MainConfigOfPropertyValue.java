package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 属性赋值
 *
 * @date 2020-01-10 22:38
 * @since v3.0
 */

@Configuration
@PropertySource(value = {"classpath:person.properties"})
public class MainConfigOfPropertyValue {

    @Bean
    public Person person() {
        return new Person();
    }
}
