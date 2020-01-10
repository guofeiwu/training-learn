package com.guofei.wu.springannotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 21:31
 * @since v3.0
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取环境变量
        Environment environment = context.getEnvironment();

        String property = environment.getProperty("os.name");

        if (property.contains("Windows")) {
            return true;
        }
        return false;
    }
}
