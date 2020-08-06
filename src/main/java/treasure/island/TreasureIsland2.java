package treasure.island;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TreasureIsland2 {
    static boolean isSafe(int i, int j, int n, int m, int[][] map) {
        return i >= 0 && i < n && j >= 0 && j < m && map[i][j] != -1;
    }

    static Queue<int[]> getSources(int[][] map) {
        Queue<int[]> sources = new ArrayDeque<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2) {
                    sources.offer(new int[]{i, j});
                }
            }
        }

        return sources;
    }

    static int calculateCost(int[][] map) {
        if (map == null || map.length == 0) {
            return -1;
        }

        int n = map.length, m = map[0].length, cost = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> sources = getSources(map);
        while (!sources.isEmpty()) {
            int size = sources.size();
            cost++;
            while (size > 0) {
                size--;
                int[] pos = sources.poll();
                for (int[] dir : dirs) {
                    int i = pos[0] + dir[0], j = pos[1] + dir[1];
                    if (isSafe(i, j, n, m, map)) {
                        if (map[i][j] == 1) {
                            return cost;
                        }
                        map[i][j] = -1;
                        sources.offer(new int[]{i, j});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String... strings) {
        //0: safe to sail
        //2: source
        //-1: obstacle
        //1: treasure
        int[][] map = new int[][]{
                {2, 0, 0, 2, 2},
                {-1, 0, -1, 0, -1},
                {0, 0, 0, 0, 1},
                {1, -1, -1, 0, 0},
                {1, -1, -1, -1, 0}
        };

        System.out.println(calculateCost(map));
    }
}
