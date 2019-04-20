package com.guofei.wu.weekthirteen;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/27
 * @since 2018/8/27
 */
public class ImplementsRunnableWay implements Runnable {
    private String name;

    public ImplementsRunnableWay() {
    }

    public ImplementsRunnableWay(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "  ImplementsRunnableWay 方式创建线程");
    }
}
