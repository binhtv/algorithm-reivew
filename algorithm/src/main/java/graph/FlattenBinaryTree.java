package main.java.graph;

import tree.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTree {
    private Queue<TreeNode> q = new LinkedList<>();

    private void helper(TreeNode node) {
        q.offer(node);
        if(node.getLeft() != null) {
            helper(node.getLeft());
        }
        if(node.getRight() != null) {
            helper(node.getRight());
        }
    }

    public void flatten(TreeNode root) {
        if(root == null) {
            return;
        }

        helper(root);
        TreeNode tmp = q.poll();
        TreeNode node = tmp;
        while (!q.isEmpty()) {
            node.setLeft(null);
            node.setRight(q.poll());
            node = node.getRight();
        }

        root = tmp;
    }

    public static void main(String...strings) {
        System.out.println("Test");
        TreeNode tree = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);

        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setRight(node6);

        tree.setLeft(node2);
        tree.setRight(node5);

        FlattenBinaryTree fbt = new FlattenBinaryTree();
        fbt.flatten(tree);
        System.out.println(tree);
    }
}
