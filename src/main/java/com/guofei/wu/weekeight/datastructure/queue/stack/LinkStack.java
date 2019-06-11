package com.guofei.wu.weekeight.datastructure.queue.stack;

import com.guofei.wu.weekeight.datastructure.queue.Node;

import java.io.Serializable;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/27 10:57
 * @description 链式栈(不带头结点)
 */
public class LinkStack<T> implements Stack<T>, Serializable {

    private Node<T> top;

    private int size = 0;

    public LinkStack() {
        this.top = new Node<>(null);
    }


    @Override
    public boolean isEmpty() {
        return top == null || top.data == null;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new NullPointerException("data can\'t be null");
        }
        //调用pop()后top可能为null
        if (this.top == null) {
            this.top = new Node<>(data);
        } else if (this.top.data == null) {
            // 第一次入栈这是设置data
            this.top.data = data;
        } else {
            Node<T> n = new Node<>(data, top);
            this.top = n;
        }
        size++;
    }


    public int size() {
        return size;
    }


    @Override
    public T peek() {
        return (this.top != null && this.top.data != null) ? this.top.data : null;
    }

    @Override
    public T pop() {
        T value = null;
        if (this.top != null && this.top.data != null) {
            value = this.top.data;
        }
        if (this.top.next != null) {
            this.top = this.top.next;
        }
        size--;
        return value;
    }
}
