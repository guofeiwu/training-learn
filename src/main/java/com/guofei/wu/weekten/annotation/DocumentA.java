package com.guofei.wu.weekten.annotation;

import java.lang.annotation.*;

/**
 * 文档注解A  含注解@Documented  含有文档注解的会生成到javadoc中
 *
 * @author Mason
 * @author Mason
 * @version 2018/8/8
 * @since 2018/8/8
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentA {
}
