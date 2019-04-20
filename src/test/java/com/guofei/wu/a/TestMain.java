package com.guofei.wu.a;

import org.junit.Test;


/**
 * @author Mason
 * @version v1.0
 * @since 2019/1/4
 */
public class TestMain {


    public TestMain() {

    }

    public int TestMain(int a, int c) {
        return a + c;
    }


    @Test
    public void test1() {
    }

    @Test
    public void test2() {
        StringBuffer s;

        s = new StringBuffer("Java");

        StringBuffer s1 = s;

        s1.append(" World");

        System.out.println("s1=" + s1.toString());//打印结果为：s1=Java World

        System.out.println("s=" + s.toString());//打印结果为：s=Java World
    }


    @Test
    public void test3() {
        StringBuffer sb = new StringBuffer("init");
        System.out.println(sb.toString());
        add(sb);
        System.out.println(sb.toString());

    }

    private void add(StringBuffer sb) {
        sb.append("_abc");
    }

    @Test
    public void test4() {

        String a = "abcd";

        String b = a.substring(0, 1);
        a += b;

        System.out.println(a + "--" + b);
        System.out.println(a.intern());

        System.out.println(a.concat("fgh"));
    }


    @Test
    public void test5() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));
        System.out.println(g.equals(a + h));
    }


    @Test
    public void test6() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<TestMain> testMainClass = TestMain.class;
        TestMain testMain = testMainClass.newInstance();
        int i = testMain.TestMain(1, 2);
        System.out.println(i);

        TestMain testMain1 = new TestMain();
        Class<? extends TestMain> aClass1 = testMain1.getClass();
        TestMain testMain2 = aClass1.newInstance();
        System.out.println(testMain2.TestMain(1, 3));

        Class<?> aClass = Class.forName("com.ucar.inner.taxation.TestMain");
        TestMain o = (TestMain) aClass.newInstance();
        System.out.println(o.TestMain(2, 3));

    }

    class Address implements Cloneable {
        private String add;

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }

        @Override
        protected Object clone() {
            Address address = null;
            try {
                address = (Address) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return address;
        }
    }

    class Student implements Cloneable {
        private int number;

        private Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        protected Object clone() {
            Student stu = null;
            try {
                stu = (Student) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            Address clone = (Address) address.clone();
            stu.setAddress(clone);
            return stu;
        }
    }

    @Test
    public void test7() {
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


    public static void main(String[] args) {
        System.out.println(new City());
        System.out.println(new Province());
    }


    static class Province{

    }
}

class City {

}

