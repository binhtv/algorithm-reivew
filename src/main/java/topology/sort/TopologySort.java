package topology.sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Kahn's algorithm
 * Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.
 * Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)
 * Step-3: Remove a vertex from the queue (Dequeue operation) and then.
            Increment count of visited nodes by 1.
            Decrease in-degree by 1 for all its neighboring nodes.
            If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
 * Step 5: Repeat Step 3 until the queue is empty.
 */
public class TopologySort {
    private List<Integer>[] graph;
    private int n;//Number of vertices

    public TopologySort(int n) {
        this.n = n;
        this.graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            this.graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        this.graph[u].add(v);
    }

    public List<Integer> topologySort() {
        int[] indegree = new int[n];
        for(int i = 0; i < n; i++) {
            for(int v : this.graph[i]) {
                indegree[v]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            int v = queue.poll();
            result.add(v);
            for(int i : this.graph[v]) {
                indegree[i]--;
                if(indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            count++;
        }
        if(count != n) {
            return null;
        }

        return result;
    }

    public boolean isCyclic() {
        return false;
    }

    public static void main(String...strings) {
        TopologySort g = new TopologySort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        System.out.println(g.topologySort());
    }
}
