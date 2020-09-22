package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class GraphTraversal {
    private int n;
    private List<Integer>[] graph;

    public GraphTraversal(int n) {
        this.n = n;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v1, int v2) {
        graph[v1].add(v2);
    }

    public List<Integer> dfs() {
        List<Integer> result = new ArrayList<>();
        int[] visited = new int[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visited[curr] == 0) {
                visited[curr] = 1;
                result.add(curr);
            }

            for (int i : graph[curr]) {
                if (visited[i] == 0) {
                    stack.push(i);
                }
            }
        }

        return result;
    }

    public void dfs(int v, int visited[], List<Integer> result) {
        visited[v] = 1;
        result.add(v);
        for (int i : graph[v]) {
            if (visited[i] == 0) {
                dfs(i, visited, result);
            }
        }
    }

    public static void main(String... strings) {
        int n = 10;
        GraphTraversal graphTraversal = new GraphTraversal(n);
        graphTraversal.addEdge(0, 1);
        graphTraversal.addEdge(0, 2);
        graphTraversal.addEdge(0, 3);
        graphTraversal.addEdge(1, 4);
        graphTraversal.addEdge(2, 5);
        graphTraversal.addEdge(3, 4);
        graphTraversal.addEdge(4, 7);
        graphTraversal.addEdge(5, 4);
        graphTraversal.addEdge(5, 6);
        graphTraversal.addEdge(5, 8);
        graphTraversal.addEdge(7, 9);
        graphTraversal.addEdge(8, 9);
        System.out.println(graphTraversal.dfs().stream().map(v -> String.valueOf(v)).collect(Collectors.joining(" -> ")));
        List<Integer> result = new ArrayList<>();
        int[] visited = new int[n];
        graphTraversal.dfs(0, visited, result);
        System.out.println(result.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(" -> ")));
    }
}
