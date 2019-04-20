package com.guofei.wu.weekeight.datastructure.queue;

import java.lang.reflect.ParameterizedType;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/23 17:15
 * @description
 */
public class Main {

    public static void main(String[] args) {
        SequenceQueue<String> list = new SequenceQueue<String>();
        list.add("a");
        list.add("b");
        int size = list.size();
        for (int i = 0; i < 1; i++) {
            System.out.println(list.poll());
        }
    }

}
