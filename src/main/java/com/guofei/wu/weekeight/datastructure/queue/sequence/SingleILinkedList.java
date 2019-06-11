package com.guofei.wu.weekeight.datastructure.queue.sequence;

import com.guofei.wu.weekeight.datastructure.queue.Node;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/24 15:46
 * @description 单链表 不带头结点，无尾指针
 */
public class SingleILinkedList<T> implements ILinkedList<T> {

    private Node<T> headNode;


    public SingleILinkedList(Node<T> head) {
        this.headNode = head;
    }


    @Override
    public boolean isEmpty() {
        return this.headNode == null;
    }

    @Override
    public int length() {
        int length = 0;
        Node<T> p = this.headNode;
//        while (p != null) {
//            length++;
//            p = p.next;
//        }
        if (p != null) {
            length++;
            while (p.next != null) {
                length++;
                p = p.next;
            }
        }
        return length;
    }

    @Override
    public T get(int index) {
        int count = 0;
        // 头结点不为空并且下标大于0
        if (this.headNode != null && index >= 0) {
            Node<T> p = this.headNode;
            // 循环 当count == index 时
            while (p != null && count < index) {
                p = p.next;
                count++;
            }
            if (p != null) {
                return p.data;
            }
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        int count = 0;
        // 头结点不为空并且下标大于0
        if (this.headNode != null && index >= 0) {
            Node<T> p = this.headNode;
            // 循环 当count == index 时
            while (p != null && count < index) {
                p = p.next;
                count++;
            }
            if (p != null) {
                T old = p.data;
                p.data = data;
                return old;
            }
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {

        if (data == null) {
            return false;
        }
        // 头部插入
        if (this.headNode == null || index <= 0) {
//            headNode = new Node<T>(data, null);
//            headNode.next = headNode;
            headNode = new Node<T>(data, headNode);
        } else {
            // 在尾部或中间插入
            int count = 0;
            Node<T> p = this.headNode;
            //找到要插入结点位置的前一个结点
            while (p.next != null && count < index - 1) {
                p = p.next;
                count++;
            }
            //尾部添加和中间插入属于同种情况,当p为尾部结点时p.next=null
            p.next = new Node<T>(data, p.next);

        }
        return true;
    }

    @Override
    public boolean add(T data) {
        if (data == null) {
            return false;
        }
        if (this.headNode == null) {
            headNode = new Node<T>(data, headNode);
        } else {
            Node<T> p = this.headNode;
            // 查找最后一个结点
            while (p.next != null) {
                p = p.next;
            }
            if (p != null) {
                p.next = new Node<T>(data, p.next);
            }
        }
        return true;
//        return add(Integer.MAX_VALUE, data);
    }

    @Override
    public T remove(int index) {
        int length = length();
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        T old = null;
        // 若删除的是头结点，将头结点指向原来头结点的下一个结点
        if (index == 0) {
            old = this.headNode.data;
            this.headNode = this.headNode.next;
        } else {
            int count = 0;
            Node<T> p = this.headNode;
            //找到要删除结点位置的前一个结点
            while (p.next != null && count < index - 1) {
                p = p.next;
                count++;
            }
            if (p != null) {
                old = p.next.data;
                // 要删除的结点的前一个结点指向要删除结点的下一个结点
                p.next = p.next.next;
            }
        }
        return old;
    }

//    @Override
//    public boolean removeAll(T data) {
//        boolean isRemove = false;
//        if (data == null) {
//            return isRemove;
//        }
//        // 删除的是头结点
//        if (this.headNode != null && this.headNode.data.equals(data)) {
//            this.headNode = this.headNode.next;
//            isRemove = true;
//        }
//        // 非头结点
//        Node<T> p = this.headNode;
//        Node<T> next = p.next;
//        // 遍历
//        while (next != null) {
//            if (next.data.equals(data)) {
//                // 指向被删结点的下一个结点
//                p.next = next.next;
//                // 指向被删结点的下一个结点的下一个结点
//                next = p.next;
//                isRemove = true;
//            } else {
//                // 未删除 指向下一个结点
//                p = next;
//                next = next.next;
//            }
//        }
//        return isRemove;
//    }

    @Override
    public boolean removeAll(T data) {
        if (data == null) {
            return false;
        }
        int count = 0;
        boolean isRemove = false;
        Node<T> p = this.headNode;
        while (p != null) {
            // 当结点不为null时，比较data，若没有进行移除则count++，p指向p.next;
            // 若执行了删除则重置count为0，并将p指向第一个元素
            if (p.data.equals(data)) {
                this.remove(count);
                isRemove = true;
            }
            // 若没有进行移除则count++，p指向p.next;
            if (!isRemove) {
                count++;
                p = p.next;
            } else {
                // 若执行了删除则重置count为0，并将p指向第一个元素
                count = 0;
                p = this.headNode;
                isRemove = false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        if (this.headNode != null) {
            this.headNode = null;
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            return false;
        }
        Node<T> p = this.headNode;
        // 每个结点进行循环
        while (p != null) {
            if (p.data.equals(data)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }
}
