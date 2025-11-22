package main.java.tree;

import tree.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTreePreOrder {

    private int idx = 0;
    private Map<Integer, Integer> lookup = new HashMap<>();

    private TreeNode build(int[] preorder, int start, int end) {
        if (start >= end) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[idx++]);

        int rIndex = lookup.get(node.getVal());
        node.setLeft(build(preorder, start, rIndex));
        node.setRight(build(preorder, rIndex + 1, end));

        return node;
    }

    public TreeNode buildFromPreorder(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            lookup.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length);
    }

    public static void main(String... strings) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        BuildTreePreOrder buildTree = new BuildTreePreOrder();
        TreeNode root = buildTree.buildFromPreorder(preorder, inorder);
    }
}
