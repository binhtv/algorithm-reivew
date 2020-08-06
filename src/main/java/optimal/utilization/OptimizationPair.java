package optimal.utilization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class OptimizationPair {
    public static void main(String... strings) {
//        int[][] a = new int[][]{{1, 2}, {2, 4}, {3, 6}};
//        int[][] b = new int[][]{{1, 2}};
        int[][] a = new int[][]{{1, 3}, {2, 5}, {3, 7}, {4, 10}};
        int[][] b = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int target = 10;
        int min = Integer.MIN_VALUE;

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                int cost = a[i][1] + b[j][1];
                if (cost <= target) {
                    if (min <= cost) {
                        if (min < cost) {
                            result.clear();
                        }
                        min = cost;
                        result.add(new int[]{a[i][0], a[j][0]});
                    }

                }
            }
        }

        Iterator<int[]> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.print(Arrays.toString(iterator.next()));
        }
    }
}
