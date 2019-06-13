package com.guofei.wu.weekten.annotation;

import com.google.j2objc.annotations.LoopTranslation;

import java.lang.annotation.*;

/**
 * @version 2018/8/8
 * @since 2018/8/8
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DocumentC {
    String name() default "abc";
}