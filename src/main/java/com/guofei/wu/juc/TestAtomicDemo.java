package com.guofei.wu.juc;

/**
 * @version v1.0
 * @since 2018/12/25
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        int i = 10;
        int temp = i;
        i = i + 1;
        i = temp;
        System.out.println(i);
    }

}