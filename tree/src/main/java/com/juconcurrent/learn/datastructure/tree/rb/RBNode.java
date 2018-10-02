package com.juconcurrent.learn.datastructure.tree.rb;

/**
 * @author zhangfb
 */
public class RBNode<T> {

    private T data;
    public RBNode left;
    public RBNode right;
    public RBNode parent;
    public boolean red;

    public RBNode(T data, RBNode left, RBNode right, RBNode parent, boolean red) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.red = red;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }
}
