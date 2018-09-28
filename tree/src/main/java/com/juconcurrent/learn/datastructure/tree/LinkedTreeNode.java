package com.juconcurrent.learn.datastructure.tree;

import java.util.List;

/**
 * @author zhangfb
 */
public class LinkedTreeNode {

    private Object data;
    private LinkedTreeNode parent;
    private List<LinkedTreeNode> children;

    public LinkedTreeNode(Object data, LinkedTreeNode parent,
                          List<LinkedTreeNode> children) {
        this.data = data;
        this.parent = parent;
        this.children = children;
    }
}
