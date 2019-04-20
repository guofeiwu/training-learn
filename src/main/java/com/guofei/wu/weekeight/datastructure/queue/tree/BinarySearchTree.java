package com.guofei.wu.weekeight.datastructure.queue.tree;

import javafx.scene.transform.Rotate;

/**
 * @author guofei.wu
 * @date 2018/7/27 15:12
 * @description
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    /**
     * 根结点
     *
     * @author guofei.wu
     * @date 2018/7/27
     */
    protected BinaryNode<T> root;


    public BinarySearchTree() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public String preOrder() {
        return null;
    }

    @Override
    public String inOrder() {
        return null;
    }

    @Override
    public String postOrder() {
        return null;
    }

    @Override
    public String levelOrder() {
        return null;
    }

    @Override
    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("data can\'t be null");
        }

        root = insert(data, root);


    }

    /**
     * 递归插入数据
     *
     * @param data
     * @param parent
     * @return com.guofei.wu.weekeight.datastructure.queue.tree.BinaryNode<T>
     * @author guofei.wu
     * @date 2018/7/27
     */
    private BinaryNode<T> insert(T data, BinaryNode<T> parent) {

        if (parent == null) {
            parent = new BinaryNode<>(data);
        }
        int comparable = data.compareTo(parent.data);

        if (comparable < 0) {
            parent.left = insert(data, parent.left);
        } else if (comparable > 0) {
            parent.right = insert(data, parent.right);
        } else {
            // 已经有元素
        }
        return parent;
    }

    @Override
    public void remove(T data) {

    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }
}
