package com.juconcurrent.learn.datastructure.tree.binary;

/**
 * 1. 搜索
 * 2. 插入
 * 3. 删除
 *
 * @author zhangfb
 */
public class BinarySearchTreee {

    private BinaryTreeNode root;

    public BinarySearchTreee(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode search(int data) {
        return search(root, data);
    }

    private BinaryTreeNode search(BinaryTreeNode node, int data) {
        // 节点不存在，则数据不在树中
        if (node == null) {
            return null;
        }

        // 数据相同，当前节点就是查找的节点
        if (node.getData() == data) {
            return node;
        }

        if (data < node.getData()) { // 比节点数据小，则搜索左子树
            return search(node.getLeft(), data);
        } else { // 比节点数据大，则搜索右子树
            return search(node.getRight(), data);
        }
    }

    public void insert(int data) {
        if (root == null) {
            root = new BinaryTreeNode(data, null, null);
            return;
        }

        searchAndInsert(null, root, data);
    }

    private BinaryTreeNode searchAndInsert(BinaryTreeNode parent, BinaryTreeNode current, int data) {
        if (current == null) {
            BinaryTreeNode node = new BinaryTreeNode(data, null, null);
            if (data < parent.getData()) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
            return node;
        }

        if (current.getData() == data) {
            return current;
        } else if (data < current.getData()) {
            return searchAndInsert(current, current.getLeft(), data);
        } else {
            return searchAndInsert(current, current.getRight(), data);
        }
    }

    private BinaryTreeNode searchParent(int data) {
        return searchParent(null, root, data);
    }

    private BinaryTreeNode searchParent(BinaryTreeNode parent, BinaryTreeNode node, int data) {
        if (node == null) { // 比较节点为null，说明没有找到父节点
            return null;
        }

        if (node.getData() == data) { // 数据相同，就是当前节点
            return parent;
        } else if (data < node.getData()) { // 数据比节点数据小，从节点的左子节点查找
            return searchParent(node, node.getLeft(), data);
        } else { // 数据比节点数据大，从节点的右子节点查找
            return searchParent(node, node.getRight(), data);
        }
    }

    public void delete(int data) {
        if (root == null || root.getData() == data) {
            root = null;
        }

        BinaryTreeNode parent = searchParent(data);
        if (parent == null) { // 父节点为空，说明没有找到此节点
            return;
        }

        BinaryTreeNode deleteNode = search(data);
        if (deleteNode == null) { // 没有找到此节点
            return;
        }

        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) { // 左子节点和右子节点都没有，直接删除节点，并去掉父节点对其的关联
            if (parent.getLeft() == deleteNode) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            deleteNode = null;
            return;
        } else if (deleteNode.getLeft() != null && deleteNode.getRight() == null) { // 只有左子节点，没有右子节点。左子节点要继承当前节点
            if (data < parent.getData()) {
                parent.setLeft(deleteNode.getLeft());
            } else {
                parent.setRight(deleteNode.getLeft());
            }
            deleteNode = null;
            return;
        } else if (deleteNode.getRight() != null && deleteNode.getLeft() == null) { // 只有右子节点，没有左子节点。右子节点要继承当前节点
            if (data < parent.getData()) {
                parent.setRight(deleteNode.getRight());
            } else {
                parent.setLeft(deleteNode.getRight());
            }
        } else {
            // 要删除的节点儿女双全，既有左子树又有右子树，需要选一个合适的节点继承，这里使用右子树中最左节点
            BinaryTreeNode copyOfDeleteNode = deleteNode;   // 要删除节点的副本，指向继承节点的父节点
            BinaryTreeNode heresNode = deleteNode.getRight(); // 要继承位置的节点，初始为要删除节点的右子树的树根
            // 右子树没有左孩子了，他就是最小的，直接上位
            if (heresNode.getLeft() == null) {
                // 上位后，兄弟变成了孩子
                heresNode.setLeft(deleteNode.getLeft());
            } else {
                // 右子树有左孩子，循环找到最左的，即最小的
                while (heresNode.getLeft() != null) {
                    copyOfDeleteNode = heresNode;       // copyOfDeleteNode 指向继承节点的父节点
                    heresNode = heresNode.getLeft();
                }
                // 找到了继承节点，继承节点的右子树（如果有的话）要上移一位
                copyOfDeleteNode.setLeft(heresNode.getRight());
                // 继承节点先继承家业，把自己的左右孩子变成要删除节点的孩子
                heresNode.setLeft(deleteNode.getLeft());
                heresNode.setRight(deleteNode.getRight());
            }
            // 最后就是确认位置，让要删除节点的父节点认识新儿子
            if (parent.getLeft() != null && parent.getLeft().getData() == data) {
                parent.setLeft(heresNode);
            } else {
                parent.setRight(heresNode);
            }
        }
    }
}
