package tree;

public class BST {
    private int val;
    private BST left;
    private BST right;

    public BST(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public void insert(int val) {
        BST p = this;
        while (p != null) {
            if(p.val > val) {
                if (p.left == null) {
                    p.left = new BST(val);
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new BST(val);
                    break;
                }
                p = p.right;
            }
        }
    }

    public BST search(int val) {
        BST p = this;
        while (p != null) {
            if(p.val == val) {
                break;
            }
            if(p.val > val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        return p;
    }

    /**
     * Calculate the distance between a node to another node with given value
     * @param node
     * @param val
     * @return the distance if found, otherwise -1
     */
    public int getDistance(BST node, int val) {
        int cost = 0;
        BST p = node;

        while (p != null) {
            if(p.val > val) {
                cost++;
                p = p.left;
            } else if(p.val < val) {
                cost++;
                p = p.right;
            } else {
                break;
            }
        }

        return p == null ? -1 : cost;
    }

    /**
     * Find lowest common ancestor
     * @param node1
     * @param node2
     * @return
     */
    public BST lca(int node1, int node2) {
        if(search(node1) == null || search(node2) == null) {
            return null;
        }

        BST p = this;
        while(p != null) {
            if(p.val < node1 && p.val < node2) {
                p = p.right;
            } else if(p.val > node1 && p.val > node2) {
                p = p.left;
            } else {
                break;
            }
        }

        return p;
    }

    @Override
    public String toString() {
        return "BST{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public static void main(String...strings) {
        BST bst = new BST(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(3);
        bst.insert(-18);
        bst.insert(-10);
//        System.out.println(bst);
//        System.out.println(bst.search(-18));
        System.out.println(bst.lca(2, -10));
    }
}
