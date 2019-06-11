package com.guofei.wu.weekeight.datastructure.queue.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/27 16:03
 * @description
 */
public class Main {
    @Test
    public void testBinaryTree() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(100);
        bst.insert(60);
        bst.insert(200);
    }


    @Test
    public void testNull() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add(null);
        list.add("B");
        list.add(null);
        list.add("C");
        list.add(null);
        list.add("");
        for (String s :
                list) {
            System.out.println(s);
        }
        System.out.println(list.size());
    }


}
