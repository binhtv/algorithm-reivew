package main.java.sorting.quicksort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * <p>
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * <p>
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 * Example 1:
 * <p>
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 * <p>
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 */
public class KCloset {
    public static int[][] heapMethod(int[][] points, int k) {
        if (points == null || points.length == 0) {
            return null;
        }
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(q -> Math.sqrt(q[0] * q[0] + q[1] * q[1])));
        queue.addAll(Arrays.asList(points));

        int[][] result = new int[k][2];
        while (--k >= 0) {
            result[k] = queue.poll();
        }

        return result;
    }

    public static void print(int[][] points) {
        StringBuilder sb = new StringBuilder();
        for (int[] point : points) {
            sb.append(Arrays.toString(point));
            sb.append(",");
        }
        System.out.print("[");
        System.out.print(sb.toString());
        System.out.print("]");
    }

    public static void main(String... strings) {
        int[][] points = new int[][]{{3, 3}, {5, -1}, {-2, 4}, {8, 8}, {-1, 1}, {0, 0}};
        int k = 2;
        print(heapMethod(points, k));
    }
}
