package com.guofei.wu.weeknine.sort.javasortutil;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/1
 * @since 2018/8/1
 */
public class Student implements Comparable<Student> {
    private Integer id;
    private String name;
    private Integer age;

    public Student(Integer age) {
        this.age = age;
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        }
        return 0;
    }
}
