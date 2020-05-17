package com.guofei.wu.weeknine.sort.javasortutil;

import org.junit.Test;

import java.util.*;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */
public class ArraysUtilTest {
    @Test
    public void testArrays() {
        char[] chars = {'C', 'A', 'M', 'N', 'B'};
        Arrays.sort(chars);
        for (char c :
                chars) {
            System.out.print(c + " ,");
        }
    }


    @Test
    public void testCollections() {
        List<User> users = new ArrayList<>();
        users.add(new User(20));
        users.add(new User(22));
        users.add(new User(10));
        users.add(new User(90));

        // 降序
        Collections.sort(users, (u1, u2) -> {
            if (u1.getAge() > u2.getAge()) {
                return -1;
            } else if (u1.getAge() < u2.getAge()) {
                return 1;
            }
            return 0;
        });

        for (User u :
                users) {
            System.out.println(u);
        }

        List emptyList = Collections.EMPTY_LIST;
        // final  为重写add方法，，直接抛出UnSupportOperationException
        // emptyList.add("A");

        List empty = new ArrayList();
        // false
        System.out.println(empty == emptyList);
        // true
        System.out.println(empty.equals(emptyList));


        Vector<String> v = new Stack<>();
        v.add("A");
        v.add("B");

        Enumeration e = v.elements();
        ArrayList list = Collections.list(e);

        for (Object l :
                list) {
            System.out.println(l);
        }


        Collections.reverse(users);
        for (User u :
                users) {
            System.out.println(u);
        }
    }


}
