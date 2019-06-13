package com.guofei.wu.generic;

/**
 * @version v1.0
 * @since 2018/12/24
 */
public class GenericTest<K, T> {

    public void show_1(T t) {
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
    //由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
    public <E> void show_3(E t) {
        System.out.println(t.toString());
    }

    //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
    public <L> void show_2(L t) {
        System.out.println(t.toString());
    }

    public void test1(T a) {

    }

    public static <T> int test12(T a) {
        return 0;
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)  // compiler error
                ++count;
        return count;
    }


    public int test2(T t, K k) {

        return (Integer) t + (Integer) k;

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        GenericTest<Integer, Integer> genericTest = new GenericTest<>();
        int i = genericTest.test2(1, 2);
        System.out.println(i);
        genericTest.show_3(1);
        genericTest.show_3("ABC");


    }


}
