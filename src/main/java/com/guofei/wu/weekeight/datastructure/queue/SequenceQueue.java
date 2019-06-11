package com.guofei.wu.weekeight.datastructure.queue;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/23 14:50
 * @description
 */
public class SequenceQueue<T> implements IQueue<T>, Serializable {

    private static final long serialVersionUID = -2209569306893243348L;
    private static final int DEFAULT_SIZE = 10;

    private T[] elementData;

    private int front, rear;

    private int size;

    public SequenceQueue() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
        front = rear = 0;
    }

    public SequenceQueue(int capacity) {
        elementData = (T[]) new Object[capacity];
        front = rear = 0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean add(T data) {
        // 判断队列是否已经满了
        if (this.front == (this.rear + 1) % elementData.length) {
            ensureCapacity(size * 2 + 1);
        }
        // 添加数据
        elementData[this.rear] = data;
        // 将队尾指向下一空元素的位置
        this.rear = (this.rear + 1) % elementData.length;
        size++;
        return true;
    }


    /**
     * @param newCapacity
     * @return void
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 扩容
     */
    private void ensureCapacity(int newCapacity) {
        // 无需扩容
        if (newCapacity < size) {
            return;
        }
        T[] old = elementData;
        elementData = (T[]) new Object[newCapacity];
        int j = 0;
        // 复制元素
        for (int i = this.front; i != this.rear; i = (i + 1) % old.length) {
            elementData[j++] = old[i];
        }
        //恢复front,rear指向
        this.front = 0;
        this.rear = j;
    }

    @Override
    public boolean offer(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        if (this.front == (this.rear + 1) % this.elementData.length) {
            throw new IllegalArgumentException("队列已满!");
        }
        this.elementData[rear] = data;
        this.rear = (this.rear + 1) % elementData.length;
        size++;
        return false;
    }

    @Override
    public T peek() {
        return elementData[front];
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("队列为空!");
        }
        return peek();
    }

    @Override
    public T poll() {
        // 获取队首元素
        T temporary = this.elementData[this.front];
        //
        this.front = (this.front + 1) % this.elementData.length;
        size--;
        return temporary;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        return poll();
    }

    @Override
    public void clearQueue() {
        // 将每个位置都置为null
        for (int i = this.front; i != this.rear; i = (i + 1) % this.elementData.length) {
            elementData[i] = null;
        }
        size = 0;
        this.front = this.rear = 0;
    }
}
