package tree;

public class DistanceBetweenTwoNodes {
    public static int distanceBtwTwoNodes(BST bst, int node1, int node2) {
        BST lca = bst.lca(node1, node2);
        if(lca == null) {
            return -1;
        }

        return bst.getDistance(lca, node1) + bst.getDistance(lca, node2);
    }

    public static void main(String... strings) {
        int[] nums = new int[]{2, 1, 3, 7, 9, 12};
        BST bst = null;
        for(int num : nums) {
            if(bst == null) {
                bst = new BST(num);
            } else {
                bst.insert(num);
            }
        }
        System.out.println(distanceBtwTwoNodes(bst, 1, 12));
    }
}
