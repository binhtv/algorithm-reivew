package minimum.cost;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumCost {
    static int minimumCost(int[] sticks) {
        if(sticks == null || sticks.length == 0) {
            return 0;
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for(int i : sticks) {
            queue.offer(i);
        }

        int result = 0, tmp = 0;
        while(queue.size() > 1) {
            tmp = queue.poll() + queue.poll();
            result += tmp;
            queue.offer(tmp);
        }

        return result;
    }

    public static void main(String... strings) {
        int[] sticks = new int[]{2, 4, 3};

        System.out.println(minimumCost(sticks));
    }
}
