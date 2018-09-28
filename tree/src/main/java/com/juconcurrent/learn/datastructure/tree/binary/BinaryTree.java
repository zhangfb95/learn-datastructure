package com.juconcurrent.learn.datastructure.tree.binary;

/**
 * 1. 创建根节点
 * 2. 二叉树添加元素
 * 3. 二叉树删除元素
 * 4. 清空二叉树
 * 5. 获取二叉树高度
 * 6. 获取二叉树节点数
 * 7. 获取节点的父节点
 * <p>
 * 8. 先序遍历
 * 9. 中序遍历
 * 10. 后序遍历
 *
 * @author zhangfb
 */
public class BinaryTree {

    private BinaryTreeNode root;

    public BinaryTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode root) {
        this.root = root;
    }

    public void insertAsLeftNode(BinaryTreeNode node) {
        checkTreeEmpty();
        root.setLeft(node);
    }

    public void insertAsRightNode(BinaryTreeNode node) {
        checkTreeEmpty();
        root.setRight(node);
    }

    public void checkTreeEmpty() {
        if (root == null) {
            throw new IllegalArgumentException("root node can not be null");
        }
    }

    public void deleteNode(BinaryTreeNode node) {
        checkTreeEmpty();
        if (node == null) {
            return;
        }

        deleteNode(node.getLeft());
        deleteNode(node.getRight());
        // 加速GC
        node = null;
    }

    public void clear() {
        if (root != null) {
            deleteNode(root);
        }
    }

    public int getTreeHeight() {
        return getTreeNodeHeight(root);
    }

    public int getTreeNodeHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftNodeHeight = getTreeNodeHeight(node.getLeft());
        int rightNodeHeight = getTreeNodeHeight(node.getRight());
        return 1 + Math.max(leftNodeHeight, rightNodeHeight);
    }

    public int getSize() {
        return getSize(root);
    }

    public int getSize(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        return getSize(node.getLeft()) + getSize(node.getRight()) + 1;
    }

    public BinaryTreeNode getParent(BinaryTreeNode node) {
        if (root == null || node == root) {
            return null;
        }
        return getParent(root, node);
    }

    private BinaryTreeNode getParent(BinaryTreeNode currentNode, BinaryTreeNode node) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.getLeft() == node || currentNode.getRight() == node) {
            return currentNode;
        }

        BinaryTreeNode parent = null;
        if ((parent = getParent(currentNode.getLeft(), node)) != null) {
            return parent;
        } else {
            return getParent(currentNode.getRight(), node);
        }
    }

    /**
     * 先序遍历，先遍历根节点，再遍历左节点，最后遍历右节点
     */
    public void interateFirstOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        operateData(node);
        interateFirstOrder(node.getLeft());
        interateFirstOrder(node.getRight());
    }

    /**
     * 中序遍历，先遍历左节点，再遍历根节点，最后遍历右节点
     */
    public void interateMiddleOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        interateMiddleOrder(node.getLeft());
        operateData(node);
        interateMiddleOrder(node.getRight());
    }

    /**
     * 后序遍历，先遍历左节点，再遍历右节点，最后遍历根节点
     */
    public void interateLastOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        interateLastOrder(node.getLeft());
        interateLastOrder(node.getRight());
        operateData(node);
    }

    private void operateData(BinaryTreeNode node) {
        System.out.println(node.getData() + " ");
    }
}
