package com.guofei.wu.springannotation.config;

import com.guofei.wu.springannotation.aop.LogAspect;
import com.guofei.wu.springannotation.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-12 20:50
 * @since v3.0
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigAOP {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
