package com.guofei.wu.springannotation.test;

import com.guofei.wu.springannotation.bean.Black;
import com.guofei.wu.springannotation.config.MainConfig_Autowired;
import com.guofei.wu.springannotation.dao.BookDao;
import com.guofei.wu.springannotation.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:40
 * @since v3.0
 */
public class MainConfig_autowired {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig_Autowired.class);

    @Test
    public void test1() {
        // printBeanName(ac);
        BookService bookService = (BookService) ac.getBean("bookService");
        bookService.print();

//        Black bean = ac.getBean(Black.class);
//        System.out.println(bean);
//        bean.print();


    }

    private void printBeanName(ApplicationContext ac) {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
