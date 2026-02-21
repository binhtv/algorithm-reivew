package treasure.island;

import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland {
    static boolean isSafe(int i, int j, int n, int m, int[][] map) {
        return i >= 0 && i < n && j >= 0 && j < m && map[i][j] != -1;
    }

    static int calculateCost(int[][] map) {
        if (map == null || map.length == 0) {
            return -1;
        }

        int n = map.length, m = map[0].length, cost = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        map[0][0] = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cost++;
            while (size > 0) {
                size--;
                int[] pos = queue.poll();
                for (int[] dir : dirs) {
                    int i = pos[0] + dir[0], j = pos[1] + dir[1];
                    if (isSafe(i, j, n, m, map)) {
                        if (map[i][j] == 1) {
                            return cost;
                        }
                        map[i][j] = -1;
                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String... strings) {
        //0: safe to sail
        //-1: obstacle
        //1: treasure
        int[][] map = new int[][]{
                {0, 0, 0, 0},
                {-1, 0, -1, 0},
                {0, 0, 0, 0},
                {1, -1, -1, 0}
        };

        System.out.println(calculateCost(map));
    }
}
