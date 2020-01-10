package com.guofei.wu.springannotation.bean;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:16
 * @since v3.0
 */
public class Person {

    private String name;

    private int age;


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
                '}';
    }
}
