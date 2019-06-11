package com.guofei.wu.weekeight.datastructure.queue.stack;

import org.junit.Test;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/27 10:05
 * @description 栈测试
 */
public class Main {
    @Test
    public void testSeqStack() {
        SeqStack<String> seqStack = new SeqStack<>(1);
        seqStack.push("A");
        seqStack.push("B");
        if (!seqStack.isEmpty()) {
            System.out.println(seqStack.peek());
        }
    }


    @Test
    public void testLinkStack() {
        LinkStack<String> linkStack = new LinkStack<>();
        System.out.println(linkStack.isEmpty());
        linkStack.push("A");
        linkStack.push("B");
        linkStack.push("C");
        int size = linkStack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(linkStack.pop());
        }
        System.out.println(linkStack.size());

    }
}
