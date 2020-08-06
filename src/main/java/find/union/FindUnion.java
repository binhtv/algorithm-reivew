package find.union;

import java.util.*;

public class FindUnion {
    private int[] ids;

    public FindUnion(int n) {
        ids = new int[n];
        for(int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    public boolean union(int u, int v) {
        int p = find(u);
        int q = find(v);
        if(q == p) { //Already in the same set
            return false;
        }

        ids[p] = q;

        return true;
    }

    public boolean isConnected(int u, int v) {
        System.out.println(find(u));
        System.out.println(find(v));
        return find(u) == find(v);
    }

    private int find(int v) {
        while (v != ids[v]) {
            ids[v] = ids[ids[v]];
            v = ids[v];
        }

        return v;
    }

    @Override
    public String toString() {
        return "FindUnion{" +
                "ids=" + Arrays.toString(ids) +
                '}';
    }


    public static void main(String...strings) {
//        FindUnion fu = new FindUnion(10);
//        System.out.println(fu);
//        fu.union(0, 2);
//        System.out.println(fu);
//        fu.union(0, 3);
//        fu.union(2, 8);
//        System.out.println(fu);
//        System.out.println(fu.isConnected(0, 3));

        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        System.out.println(l1.equals(l2));
    }
}
