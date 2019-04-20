package com.guofei.wu.b;

import com.guofei.wu.a.Address;
import com.guofei.wu.a.Student;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/1/7
 */
public class Test {
    @org.junit.Test
    public void test1() {
        Student stu1 = new Student();
        stu1.setNumber(12345);
        Address ad = new Address();
        ad.setAdd("武汉市");
        stu1.setAddress(ad);
        Student stu2 = (Student) stu1.clone();

        System.out.println("学生1:" + stu1.getNumber() + " 地址:" + stu1.getAddress().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + " 地址:" + stu2.getAddress().getAdd());
        stu2.setNumber(54321);

        ad.setAdd("北京市");
        System.out.println("===============");
        System.out.println("学生1:" + stu1.getNumber() + " 地址:" + stu1.getAddress().getAdd());
        System.out.println("学生2:" + stu2.getNumber() + " 地址:" + stu2.getAddress().getAdd());
    }
}
