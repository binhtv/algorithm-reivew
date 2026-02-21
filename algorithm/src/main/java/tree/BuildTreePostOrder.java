package tree;

import tree.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTreePostOrder {

    private int idx = 0;
    private Map<Integer, Integer> lookup = new HashMap<>();

    public BuildTreePostOrder(int end) {
        idx = end;
    }

    private TreeNode build(int[] postorder, int start, int end) {
        if(start > end) {
            return null;
        }

        TreeNode node = new TreeNode(postorder[idx--]);

        int rIndex = lookup.get(node.getVal());
        node.setRight(build(postorder, rIndex + 1, end));
        node.setLeft(build(postorder, start, rIndex - 1));

        return node;
    }

    public TreeNode buildFromPostOrder(int[] postorder, int[] inorder) {
        if(postorder == null || inorder == null || postorder.length == 0 || postorder.length != inorder.length) {
            return null;
        }

        for(int i = 0; i < inorder.length; i++) {
            lookup.put(inorder[i], i);
        }

        return build(postorder, 0, postorder.length - 1);
    }

    public static void main(String... strings) {
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        int[] inorder = {9, 3, 15, 20, 7};

        BuildTreePostOrder buildTree = new BuildTreePostOrder(postorder.length - 1);
        TreeNode root = buildTree.buildFromPostOrder(postorder, inorder);
    }
}
