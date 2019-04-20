package com.guofei.wu.weekeight.datastructure.queue;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/24 9:35
 * @description 单链表节点
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

}
