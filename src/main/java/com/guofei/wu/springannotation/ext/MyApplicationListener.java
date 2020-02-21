package com.guofei.wu.springannotation.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-22 10:14
 * @since v3.0
 */
@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("event:" + event);
    }
}
