package main.java.find.union;

import java.util.Arrays;

public class FindUnionOptimized {
    private final int[] parent;
    private final int[] rank;

    public FindUnionOptimized(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if(rootU == rootV) {//u & v is already in the same set
            return false;
        }

        if(rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if(rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootU] = rootV;
            rank[rootV]++;
        }

        return true;
    }

    public int find(int v) {
        if(parent[v] != v) {
            parent[v] = find(parent[v]);
        }

        return parent[v];
    }

    @Override
    public String toString() {
        return "FindUnion{" +
                "parents=" + Arrays.toString(parent) +
                ", rank=" + Arrays.toString(rank) +
                '}';
    }
}
