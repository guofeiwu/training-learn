package com.guofei.wu.weekeight.datastructure.queue;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/23 11:41
 * @description 队列抽象数据类型
 */
public interface IQueue<T> {

    /**
     * @param
     * @return int
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 队列长度
     */
    int size();

    /**
     * @param
     * @return boolean
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 队列是否为空
     */
    boolean isEmpty();

    /**
     * @param data
     * @return boolean
     * @author guofei.wu
     * @date 2018/7/23
     * @Desc data 入队， 添加成功返回true，失败为false，可扩容
     */
    boolean add(T data);

    /**
     * @param data
     * @return boolean
     * @author guofei.wu
     * @date 2018/7/23
     * @desc offer 方法可插入一个元素,这与add 方法不同，
     * 该方法只能通过抛出未经检查的异常使添加元素失败。
     * 而不是出现异常的情况，例如在容量固定（有界）的队列中
     * NullPointerException:data==null时抛出
     * illegalArgumentException:队满,使用该方法可以使Queue的容量固定
     */
    boolean offer(T data);

    /**
     * @param
     * @return T
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 返回队头元素, 不执行删除操作, 若队列为空, 返回null
     */
    T peek();

    /**
     * @param
     * @return T
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 返回队头元素, 不执行删除操作, 若队列为空, 抛出异常:NoSuchElementException
     */
    T element();

    /**
     * @param
     * @return T
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 出队, 执行删除操作, 返回队头元素, 若队列为空, 返回null
     */
    T poll();

    /**
     * @param
     * @return T
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 出队, 执行删除操作, 若队列为空, 抛出异常:NoSuchElementException
     */
    T remove();


    /**
     * @param
     * @return void
     * @author guofei.wu
     * @date 2018/7/23
     * @desc 清空对列
     */
    void clearQueue();

}
