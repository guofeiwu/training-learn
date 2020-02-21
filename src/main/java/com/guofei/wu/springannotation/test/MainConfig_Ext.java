package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_Ext {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ExtConfig.class);
        ac.close();
    }
}
