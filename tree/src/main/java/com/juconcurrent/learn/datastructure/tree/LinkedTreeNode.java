package com.juconcurrent.learn.datastructure.tree;

import java.util.List;

/**
 * @author zhangfb
 */
public class LinkedTreeNode {

    private Object data; // 存储的数据
    private LinkedTreeNode parent; // 父节点
    private List<LinkedTreeNode> children; // 子节点列表

    public LinkedTreeNode(Object data, LinkedTreeNode parent,
                          List<LinkedTreeNode> children) {
        this.data = data;
        this.parent = parent;
        this.children = children;
    }
}
