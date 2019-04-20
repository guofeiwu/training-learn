package com.guofei.wu.weekeight.datastructure.queue.sequence;

import com.guofei.wu.weekeight.datastructure.queue.Node;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/26 14:03
 * @description 循环单链表
 */
public class CircularHeadSILinkedList<T> implements ILinkedList<T> {

    /**
     * @author guofei.wu
     * @date 2018/7/26
     * 不带数据头结点
     */
    protected Node<T> head;
    /**
     * @author guofei.wu
     * @date 2018/7/26
     * 指向尾部的指针
     */
    protected Node<T> tail;


    public CircularHeadSILinkedList() {
        // 空的头结点
        this.head = new Node<>(null);
        // 若是空链表 则头结点的下一个结点还是头结点
        this.head.next = head;
        // 尾结点是头结点
        this.tail = head;
    }


    @Override
    public boolean isEmpty() {
        return this.head.next == head;
    }

    @Override
    public int length() {
        int length = 0;
        Node<T> p = this.head;
        while (p.next != this.head) {
            length++;
            p = p.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        int c = 0;
        Node<T> p = this.head;
        // 查找要获取的结点
        while (p.next != this.head && c < index) {
            p = p.next;
            c++;
        }

        if (p != null) {
            return p.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("下标越界");
        }

        int c = 0;
        Node<T> p = this.head;
        // 查找要获取的结点
        while (p.next != this.head && c < index) {
            p = p.next;
            c++;
        }

        if (p != this.head) {
            T oldValue = p.data;
            p.data = data;
            return oldValue;
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("下表越界");
        }
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }

        Node<T> p = this.head;
        int c = 0;
        // 查找要插入位置的前一个位置
        while (p.next != this.head && c < index) {
            p = p.next;
            c++;
        }

        if (p != null) {
            Node<T> n = new Node<>(data, p.next);
            p.next = n;
            if (p == this.tail) {
                this.tail = n;
            }
        }
        return true;
    }

    @Override
    public boolean add(T data) {

        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }

        // 创建新的结点，并将当前tail设置为其下一个结点
//        Node<T> newNode = new Node<>(data);
//        this.tail.next = newNode;
//        this.tail = this.tail.next;

        this.tail.next = new Node<>(data);
        this.tail = this.tail.next;


        return true;
    }

    @Override
    public T remove(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("下表越界");
        }
        Node<T> p = this.head;
        int c = 0;
        // 查找要删除位置的前一个位置
        while (p.next != this.head && c < index) {
            p = p.next;
            c++;
        }

        // 不为空链表
        if (p != this.head) {
            T data = p.next.data;
            // 删掉的是尾结点
            if (p.next == this.tail) {
                // 将上一个结点置为尾结点
                this.tail = p;
            }
            p.next = p.next.next;

            return data;
        }


        // 要删除的node
        Node<T> d = p.next;
        if (d != null && d != this.tail) {
            T data = d.data;

            return data;
        }

        return null;
    }

    @Override
    public boolean removeAll(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        boolean isRemove = false;
        Node<T> front = this.head;
        Node<T> next = front.next;

        while (next != this.head) {
            if (next.data.equals(data)) {
                front.next = next.next;
                next = front.next;
                isRemove = true;
                // 删除的是尾部结点
                if (data.equals(this.tail.data)) {
                    this.tail = front;
                }

            } else {
                front = next;
                next = next.next;
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
        this.head.next=head;
        this.tail=head;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        Node<T> p = this.head.next;
        while (p != this.head) {
            if (p.data.equals(data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }
}
