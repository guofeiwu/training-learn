package com.guofei.wu.weeknine.sort.javasortutil;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/1
 * @since 2018/8/1
 */
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public User(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getNameByName(String name) {
        return name;
    }

}
