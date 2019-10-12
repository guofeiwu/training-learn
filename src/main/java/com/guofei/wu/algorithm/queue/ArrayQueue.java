package com.guofei.wu.algorithm.queue;

import java.util.Scanner;

/**
 * @Author Mason
 * @Description
 * @Date 2019/10/11 15:42
 **/
public class ArrayQueue {
    public static void main(String[] args) {
        QueueArray queue = new QueueArray(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("l(list)");
            System.out.println("a(add)");
            System.out.println("g(get)");
            System.out.println("h(head)");
            System.out.println("e(exit)");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'l':
                    queue.list();
                    break;
                case 'a':
                    queue.add(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int i = queue.get();
                        System.out.println(i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.head();
                        System.out.println(head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }


        }

    }
}

class QueueArray {
    private int maxSize;

    /**
     * 表示头结点的上一个结点
     */
    private int front;

    /**
     * 尾结点
     */
    private int rear;

    private int[] table;


    public QueueArray(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.table = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return front == rear;
    }


    public void add(int n) {
        if (isFull()) {
            System.out.println("队列已经满...");
            return;
        }
        rear++;
        this.table[rear] = n;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return this.table[++front];
    }


    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return this.table[front + 1];
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("无数据...");
            return;
        }
        for (int i = 0; i < table.length; i++) {
            System.out.printf("数据为%d=%d\n", i, table[i]);
        }

    }


}
