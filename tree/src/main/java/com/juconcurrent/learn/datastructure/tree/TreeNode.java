package com.juconcurrent.learn.datastructure.tree;

/**
 * @author zhangfb
 */
public class TreeNode {

    private Object data; // 存储的数据
    private int parent; // 父节点的下标

    public TreeNode(Object data, int parent) {
        this.data = data;
        this.parent = parent;
    }
}
