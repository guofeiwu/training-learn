package com.guofei.wu.springannotation.ext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-20 20:21
 * @since v3.0
 */
@Configuration
@ComponentScan(value = {"com.guofei.wu.springannotation.ext"})
public class ExtConfig {

    @Bean
    public Today today() {
        return new Today();
    }
}
