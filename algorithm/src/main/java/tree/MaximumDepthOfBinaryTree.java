package tree;

import tree.common.TreeNode;

/**
 * 104. Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its maximum depth.
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Example 1:
 * https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 */
public class MaximumDepthOfBinaryTree {
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 1 + maxDepth(root.getLeft());
        int right = 1 + maxDepth(root.getRight());

        return Math.max(left, right);
    }

    public static void main(String... args) {
        Integer[] nodes = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode seven = new TreeNode(7);
        TreeNode fifteen = new TreeNode(15);
        TreeNode twenty = new TreeNode(20);
        twenty.setLeft(fifteen);
        twenty.setRight(seven);
        TreeNode nine = new TreeNode(9);
        TreeNode three = new TreeNode(3);
        three.setLeft(nine);
        three.setRight(twenty);

        System.out.println(maxDepth(three));
    }
}
