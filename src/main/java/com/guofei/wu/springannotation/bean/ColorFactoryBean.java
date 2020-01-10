package com.guofei.wu.springannotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:27
 * @since v3.0
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }


    // 是否是单例的
    @Override
    public boolean isSingleton() {
        return true;
    }
}
