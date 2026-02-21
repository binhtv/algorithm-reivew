package tree.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
