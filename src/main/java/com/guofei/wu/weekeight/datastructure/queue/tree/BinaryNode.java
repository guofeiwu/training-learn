package com.guofei.wu.weekeight.datastructure.queue.tree;

import java.io.Serializable;

/**
 * @author guofei.wu
 * @email guofei_wu@ucarinc.com
 * @date 2018/7/27 14:57
 * @description
 */
public class BinaryNode<T extends Comparable> implements Serializable {

    private static final long serialVersionUID = -289170756271117516L;

    public T data;

    /**
     * @author guofei.wu
     * @date 2018/7/27
     * 左孩子
     */
    public BinaryNode<T> left;

    /**
     * @author guofei.wu
     * @date 2018/7/27
     * 右孩子
     */
    public BinaryNode<T> right;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data, null, null);
    }

    /**
     * 判断是否为叶子结点
     *
     * @author guofei.wu
     * @date 2018/7/27
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

}
