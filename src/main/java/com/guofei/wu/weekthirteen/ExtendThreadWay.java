package com.guofei.wu.weekthirteen;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/27
 * @since 2018/8/27
 */
public class ExtendThreadWay extends Thread {
    public ExtendThreadWay() {
    }

    public ExtendThreadWay(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("实现 Thread 类创建线程！");
    }
}
