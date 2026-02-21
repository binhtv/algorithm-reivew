package array;

/**
 * You and your fiend are gardeners, and you take care of your plants. The plants are plated in a row, and each of them
 * needs a specific amount of water. You are about to water them using water cans. To avoid mistakes like applying too much
 * water, or not watering a plant at all, you have decided to:
 * - water the plants in the order in which they appear: you will water from left to right, and your friend will water from right to left;
 * - water each plant if you have sufficient water for it, otherwise refill your watering can;
 * - water each plant in one go, i.e. without taking a break to fill the watering can in the middle of watering a single plant.
 * This means that you may sometimes have to refill your watering can before or after watering a plan, even though it's not completely empty
 *
 * You start with watering the first plant and your friend start with watering the last plant. You and your friend are watering
 * the plants simultaneously (when you are watering the first plant, your friend is watering the last one; when you are watering
 * the second plant, your friend is watering the penultimate one; and so on). That means that you will meet in the middle of the row of plants.
 * If there is an unwatered plant there, and you and your friend together have enough water for it, you can water it without refilling your watering cans;
 * otherwise, only one of you should refill.
 *
 * At the beginning you both start with empty watering cans. How many times will you and your friend need to refill your watering cans in order to
 * water all the plants in the row?
 *
 * Write a function:
 * that, given an array of plants of N integers (for the amount of water needed by each plant), and variables capacity1 and capacity2 (for the capacity
 * of your watering can and your friend's), returns the number of times you and your friend will have to refill your watering cans to water all the plants.
 * For example, given plants=[2,4,5,1,2], capacity1 = 5 and capacity2 = 7, the function should return 3
 *
 * Assume that:
 * - N is an integer within the range [1...1000]
 * - each element of array plants is an integer within the range [1...1000000]
 * - capacity 1 and capacity2 are integers within the range [1...1,000,000,000]
 * - both of the watering cans are large enough to fully water any single plant
 */
public class WateringPlants {
    public static int solution(int[] plants, int capacity1, int capacity2) {
        if (plants.length == 1) {
            return 1;
        }
        int i = 0, j = plants.length - 1, refill = 2;
        int tmp1 = capacity1, tmp2 = capacity2;
        for (; i <= j; i++, j--) {
            tmp1 -= plants[i];
            tmp2 -= plants[j];

            if (i + 1 == j - 1) {
                if (plants[i + 1] > (tmp1 + tmp2)) {
                    refill++;
                }
                break;
            }

            if (tmp1 < plants[i + 1]) {
                refill++;
                tmp1 = capacity1;
            }
            if (tmp2 < plants[j - 1]) {
                refill++;
                tmp2 = capacity2;
            }
        }

        return refill;
    }

    public static void main(String... strings) {
        int[] plants = new int[]{2, 4, 5, 1, 2};
        int capacity1 = 5, capacity2 = 7;

        System.out.println(solution(plants, capacity1, capacity2));
        System.out.println(solution(new int[]{1, 1, 1, 1, 1, 1, 1}, 5, 7));
        System.out.println(solution(new int[]{5, 5, 5, 5, 5, 10, 7, 7, 7, 7, 7}, 5, 7));
        System.out.println(solution(new int[]{5}, 5, 7));
        System.out.println(solution(new int[]{}, 5, 7));
    }
}
