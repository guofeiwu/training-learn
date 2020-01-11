package com.guofei.wu.springannotation.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:16
 * @since v3.0
 */
public class Person {

    /**
     * 使用@Value
     * 1、基本数据
     * 2、spring的spEL #{}
     * 3、通过${} 从配置文件中获取属性值
     */

    @Value("Jack")
    private String name;

    @Value("#{20-8}")
    private int age;

    @Value("${person.nickName}")
    private String nickName;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
