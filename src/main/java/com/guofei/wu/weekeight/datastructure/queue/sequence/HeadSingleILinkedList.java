package com.guofei.wu.weekeight.datastructure.queue.sequence;

import com.guofei.wu.weekeight.datastructure.queue.Node;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/25 14:46
 * @description 带有头结点并含有尾指针
 */
public class HeadSingleILinkedList<T> implements ILinkedList<T> {


    private Node<T> rear;

    private Node<T> headNode;

    public HeadSingleILinkedList() {
        this.headNode = new Node<T>(null);
        this.rear = this.headNode;
    }


    @Override
    public boolean isEmpty() {
        return headNode.next == null;
    }

    @Override
    public int length() {
        int length = 0;
        Node<T> p = this.headNode;
        while (p.next != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if (index >= 0) {
            Node<T> p = this.headNode.next;
            if (p != null) {
                int count = 0;
                // 查看要获取的结点
                while (p != null && count < index) {
                    p = p.next;
                    count++;
                }
                if (p != null) {
                    return p.data;
                }
            }
        }
        return null;
    }

    @Override
    public T set(int index, T data) {

        int length = length();
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        Node<T> p = this.headNode.next;
        int count = 0;
        while (p != null && count < index) {
            p = p.next;
            count++;
        }
        if (p != null) {
            T old = p.data;
            p.data = data;
            return old;
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("下标越界");
        }

        int count = 0;
        Node<T> p = this.headNode;
        // 查找插入位置的前一个结点
        while (p.next != null && count < index) {
            p = p.next;
            count++;
        }
        Node<T> n = new Node<T>(data, p.next);
        p.next = n;
        if (p == this.rear) {
            this.rear = n;
        }
        return true;
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
//        this.rear.next = new Node<T>(data);
//        this.rear = this.rear.next;
        Node<T> n = new Node<T>(data);
        // 设置当前最后一个结点的下一个结点
        this.rear.next = n;
        // 设置最后一个结点
        this.rear = this.rear.next;
        return true;
    }

    @Override
    public T remove(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        int count = 0;
        Node<T> p = this.headNode;
        // 查找要删除结点的前一个结点
        while (p.next != null && count < index) {
            p = p.next;
            count++;
        }
        if (p != null) {
            T old = p.next.data;
            // 删除的是最后一个
            if (p.next.next == null) {
                this.rear = p;
                p.next = null;
            } else {
                // 设置指针指向
                p.next = p.next.next;
            }
            return old;
        }
        return null;
    }

//    @Override
//    public boolean removeAll(T data) {
//        if (data == null) {
//            throw new NullPointerException("数据不能为空!");
//        }
//        boolean isRemove = false;
//        // 含有空的头结点
//        int count = 0;
//        Node<T> p = this.headNode;
//        while (p.next != null) {
//            if (p.next.data.equals(data)) {
//                remove(count);
//                isRemove = true;
//            }
//            if (!isRemove) {
//                p = p.next;
//                count++;
//            } else {
//                count = 0;
//                 p = this.headNode;
//                isRemove = false;
//            }
//        }
//        return isRemove;
//    }


    @Override
    public boolean removeAll(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }

        boolean isRemove = false;
        // 要删除的前一个结点
        Node<T> p = this.headNode;
        // 要删除的结点
        Node<T> next = p.next;

        while (next != null) {
            if (next.data.equals(data)) {
                p.next = next.next;
                next = p.next;
                isRemove = true;
                // 如果是尾结点
                if (next == this.rear) {
                    // 将尾指针指向前一个
                    this.rear = p;
                }
            } else {
                p = next;
                next = next.next;
            }
        }


        return isRemove;
    }

    @Override
    public void clear() {
        this.headNode.next = null;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new NullPointerException("数据不能为空!");
        }
        Node<T> p = this.headNode.next;
        while (p != null) {
            if (p.data.equals(data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    public Node<T> getHeadNode() {
        return headNode;
    }

    /**
     * 反转单链表
     */
    @Override
    public void reserve() {
        // 当前节点
        Node<T> temp = this.headNode.next;

        if (temp == null) {
            // 无需翻转
            return;
        }
        // 当前节点的下一个节点
        Node next;
        // 新节点
        Node<T> newNode = null;
        // 当前节点不为空
        while (temp != null) {
            // 保存下一个节点
            next = temp.next;
            // 当前节点的下个节点指向上一个节点
            temp.next = newNode;
            // 新节点指向当前节点
            newNode = temp;
            // 当前节点指向下一个节点
            temp = next;
        }
        // 头结点的下一个节点指向新节点
        this.headNode.next = newNode;
    }

    public void list() {
        Node<T> temp = this.headNode.next;
        while (temp != null) {
            System.out.println("value:" + temp.data);
            temp = temp.next;
        }
    }


}
