package com.guofei.wu.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {

        LogInterceptor logInterceptor = new LogInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);  // 设置超类，cglib是通过继承来实现的
        enhancer.setCallback(logInterceptor);

        UserDao dao = (UserDao) enhancer.create();   // 创建代理类
        dao.update();
        // dao.select();
    }
}
