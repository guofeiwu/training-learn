package com.guofei.wu.weekeight.datastructure.queue.sequence;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/24 10:38
 * @description 顺序表
 */
public class SeqList<T> implements ISeqList<T> {

    private Object[] table;

    private int size;

    private static final int DEFAULT_SIZE = 10;

    private float load_factor = 0.75f;

    public SeqList() {
        this.table = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public SeqList(int capacity) {
        this.table = new Object[capacity];
        this.size = 0;
    }

    public SeqList(T[] data) {
        if (data == null) {
            throw new NullPointerException("数组不能为空!");
        }
        size = data.length;
        this.table = new Object[size];
        for (int i = 0; i < size; i++) {
            table[i] = data[i];
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < table.length) {
            return (T) this.table[index];
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index > 0 && index < table.length) {
            // 获取旧值
            T old = (T) this.table[index];
            // 设置新值
            this.table[index] = data;
            return old;
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        // 扩容
        if (size >= table.length * load_factor) {
            ensureCapacity(table.length * 2 + 1);
        }

        if (index < 0 || index >= table.length) {
            throw new IndexOutOfBoundsException("数组下标越界!");
        }
        if (data == null) {
            throw new NullPointerException("data 不能为空!");
        }

//        Object[] temp = Arrays.copyOf(table, table.length);
//        table[index] = data;
//        for (int i = index; i < size; i++) {
//            table[i + 1] = temp[i];
//        }

        for (int i = size - 1; i >= index; i--) {
            table[i + 1] = table[i];
        }
//        System.arraycopy(table, index, table, index + 1,
//                size - index);


        table[index] = data;
        size++;
        return true;
    }

    @Override
    public boolean add(T data) {
//        if (data == null) {
//            throw new NullPointerException("data 不能为空!");
//        }
//        // 扩容
//        if (size >= table.length * load_factor) {
//            ensureCapacity(table.length * 2 + 1);
//        }
//        table[size] = data;
//        size++;
//        return true;
        return add(this.size, data);
    }


    @Override
    public T remove(int index) {

        if (index < 0 || index >= table.length) {
            throw new IndexOutOfBoundsException("数组下标越界!");
        }
        T old = (T) table[index];
        for (int i = index; i < size - 1; i++) {
            table[i] = table[i + 1];
        }
//        int numMoved = size - index - 1;
//        System.arraycopy(table, index + 1, table, index,
//                numMoved);
        this.table[--this.size] = null;
        size--;
        return old;
    }

    @Override
    public boolean remove(T data) {
//        for (int i = 0; i < size; i++) {
//            if (table[i].equals(data)) {
//                remove(i);
//                return true;
//            }
//        }
//        return false;
        return remove(indexOf(data)) != null;

    }

    @Override
    public boolean removeAll(T data) {
        boolean flag = false;
        if (size != 0 && data != null) {
            for (int i = 0; i < size; i++) {
                if (table[i].equals(data)) {
                    remove(i);
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        for (int i = 0; i < size; i++) {
            if (table[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        for (int i = 0; i < size; i++) {
            if (table[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        for (int i = size - 1; i >= 0; i--) {
            if (table[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * @param capacity
     * @return void
     * @author guofei.wu
     * @date 2018/7/24
     * @desc 扩容
     */
    private void ensureCapacity(int capacity) {
        Object[] temp = table;
        table = new Object[capacity];
//        System.arraycopy(temp,0,table,0,temp.length);
        table = Arrays.copyOf(temp, table.length);
    }
}
