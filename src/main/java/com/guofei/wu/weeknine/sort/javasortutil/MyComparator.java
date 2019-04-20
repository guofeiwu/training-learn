package com.guofei.wu.weeknine.sort.javasortutil;


import java.util.Comparator;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/1
 * @since 2018/8/1
 */
public class MyComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        if (o1.getAge() > o2.getAge()) {
            return -1;
        } else if (o1.getAge() < o2.getAge()) {
            return 1;
        }
        return 0;
    }
}
