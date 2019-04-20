package com.guofei.wu.weeknine.java8newfeatures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */
public class LambdaExpressions {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        pre(names);
        next(names);
    }


    private static void pre(List<String> names) {

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        print(names);
    }

    private static void next(List<String> names) {
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });

        // 可以去除参数的类型，会根据上下文进行类型推断，body中只含有一行，可以不使用{},
        Collections.sort(names, (a, b) -> a.compareTo(b));
        print(names);
    }

    private static void print(List<String> names) {
        for (String n :
                names) {
            System.out.println(n);
        }
    }
}
