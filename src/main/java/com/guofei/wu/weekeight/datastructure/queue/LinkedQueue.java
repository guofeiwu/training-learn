package com.guofei.wu.weekeight.datastructure.queue;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/23 18:05
 * @description 链式队列
 */
public class LinkedQueue<T> implements IQueue<T>, Serializable {

    private static final long serialVersionUID = 9075988014516808264L;


    private Node<T> front, rear;

    private int size;
    /**
     * @author guofei.wu
     * @date 2018/7/24
     * @desc 最大容量 default 为100
     */
    private int maxSize = 100;

    public LinkedQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean isEmpty() {
        return front == null && rear == null;
    }

    @Override
    public boolean add(T data) {
        Node<T> node = new Node<T>(data, null);
        // 空队列
        if (this.front == null) {
            this.front = node;
        } else {
            // 非空队列
            this.rear.next = node;
        }
        // 将队尾指向刚插入节点
        this.rear = node;
        size++;
        return true;
    }

    /**
     * @param data
     * @return boolean
     * @author guofei.wu
     * @date 2018/7/24
     * @desc 此添加方法可以固定队列的容量
     */
    @Override
    public boolean offer(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空！");
        }
        if (size > maxSize) {
            throw new IllegalArgumentException("队列容量已满！");
        }
        return add(data);
    }

    @Override
    public T peek() {
        return isEmpty() ? null : this.front.data;
    }

    @Override
    public T element() {
        if (this.front == null) {
            throw new NoSuchElementException("元素不存在！");
        }
        return this.front.data;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T temporary = this.front.data;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        size--;
        return temporary;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("元素不存在！");
        }
        T temp = this.front.data;
        this.front = this.front.next;
        if (this.front == null) {
            this.rear = null;
        }
        size--;
        return temp;
    }

    @Override
    public void clearQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }
}
