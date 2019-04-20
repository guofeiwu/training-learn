package com.guofei.wu.weekten.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/8
 * @since 2018/8/8
 */
@DocumentD
public class AnnotationDemo extends A {
    public static void main(String... args) {
        Class<?> annotationDemoClass = AnnotationDemo.class;
        //根据指定注解类型获取该注解
        DocumentC documentC = annotationDemoClass.getAnnotation(DocumentC.class);
        System.out.println("documentC:" + documentC);

        //获取该元素上的所有注解，包含从父类继承
        Annotation[] an = annotationDemoClass.getAnnotations();
        System.out.println("an:" + Arrays.toString(an));
        //获取该元素上的所有注解，但不包含继承！
        Annotation[] an2=annotationDemoClass.getDeclaredAnnotations();
        System.out.println("an2:"+ Arrays.toString(an2));

        //判断注解DocumentA是否在该元素上
        boolean b=annotationDemoClass.isAnnotationPresent(DocumentC.class);
        System.out.println("b:"+b);
    }
}
