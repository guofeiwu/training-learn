package com.guofei.wu.springannotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 21:56
 * @since v3.0
 */
public class MyImportSelector implements ImportSelector {

    /**
     * @param importingClassMetadata 标注类@Import注解的类的信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        return new String[]{"com.guofei.wu.springannotation.bean.Blue", "com.guofei.wu.springannotation.bean.Yellow"};
    }
}
