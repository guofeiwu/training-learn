package com.guofei.wu.weekeight.datastructure.queue.stack;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/27 9:47
 * @description 顺序栈
 */
public class SeqStack<T> implements Stack<T>, Serializable {

    private static final long serialVersionUID = -7371602252738710446L;


    private T[] stackTable;


    /**
     * @author guofei.wu
     * @date 2018/7/27
     * 栈的大小
     */
    private int size = 0;

    /**
     * @author guofei.wu
     * @date 2018/7/27
     * 默认大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    public SeqStack() {
        stackTable = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public SeqStack(int capacity) {
        stackTable = (T[]) new Object[capacity];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new NullPointerException("data can\'t be null");
        }

        if (size == stackTable.length) {
            // 扩容
            ensureCapacity(size * 2 + 1);
        }


        // 栈顶添加元素，size ++
        stackTable[size++] = data;
    }

    /**
     * 扩容
     *
     * @param capacity
     * @return void
     * @author guofei.wu
     * @date 2018/7/27
     */
    private void ensureCapacity(int capacity) {
        if (capacity < size) {
            return;
        }

        T[] temp = stackTable;

        stackTable = (T[]) new Object[capacity];

//        for (int i = 0; i < size; i++) {
//            stackTable[i] = temp[i];
//        }
        stackTable = Arrays.copyOf(temp, stackTable.length);
//        System.arraycopy(temp, 0, stackTable, 0, size);

    }

    @Override
    public T peek() {
        if (size != 0) {
            return stackTable[size - 1];
        }
        return null;
    }

    @Override
    public T pop() {
        if (size != 0) {
            --size;
            T value = stackTable[size];
            stackTable[size] = null;
            return value;
        }
        return null;
    }
}
