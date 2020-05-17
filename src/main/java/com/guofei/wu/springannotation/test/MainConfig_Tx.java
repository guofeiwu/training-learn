package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.tx.TxConfig;
import com.guofei.wu.springannotation.tx.User;
import com.guofei.wu.springannotation.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_Tx {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TxConfig.class);


    @Test
    public void test1() {
        UserService bean = ac.getBean(UserService.class);
        User user = new User();
        user.setName("1号楼哦");
        bean.insertUser(user);
    }
}
