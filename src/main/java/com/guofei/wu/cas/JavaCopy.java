package com.guofei.wu.cas;

/**
 * @version v1.0
 * @since 2018/12/18
 */
public class JavaCopy {

    public static void main(String... args) {
        int a = 1;
        int b = a;
        a = 2;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
        System.out.println("a:" + a);
        System.out.println("========================");
        User user = new User("jack",11);
        System.out.println(user);
        User user1 = user;
        User user2 = user;


        user = new User("tom",12);
        System.out.println("user1:"+user1);
        System.out.println("user2:"+user2);
        System.out.println("user:"+user);

    }


  private static class User{
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
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
                  "name='" + name + '\'' +
                  ", age=" + age +
                  '}';
      }
  }


}
