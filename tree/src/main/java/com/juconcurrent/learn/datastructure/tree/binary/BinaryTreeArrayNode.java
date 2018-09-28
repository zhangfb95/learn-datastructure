package com.juconcurrent.learn.datastructure.tree.binary;

/**
 * @author zhangfb
 */
public class BinaryTreeArrayNode {

    private int data;
    private int left;
    private int right;

    public BinaryTreeArrayNode(int data, int left, int right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
