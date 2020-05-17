package com.guofei.wu.weekeight.datastructure.queue.stack;

/**
 * @author guofei.wu
 * @email
 * @date 2018/7/27 9:46
 * @description 栈的顶级接口
 */
public interface Stack<T> {
    /**
     * 栈是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * data元素入栈
     *
     * @param data
     */
    void push(T data);

    /**
     * 返回栈顶元素,未出栈
     *
     * @return
     */
    T peek();

    /**
     * 出栈,返回栈顶元素,同时从栈中移除该元素
     *
     * @return
     */
    T pop();
}
