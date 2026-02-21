package math;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 16
 * Output: true
 * Example 2:
 *
 * Input: num = 14
 * Output: false
 *
 * Constraints:
 *
 * 1 <= num <= 2^31 - 1
 */
public class PerfectSquare {
    //A square number is 1 + 3 + 5 + 7 + ...
    private static boolean checkPerfectSquare(int num) {
        int i = 1;
        while(num > 0) {
            num -= i;
            i += 2;
        }

        return num == 0;
    }

    public static void main(String...args) {
        System.out.println(checkPerfectSquare(16));
        System.out.println(checkPerfectSquare(14));
        System.out.println(checkPerfectSquare(Integer.MAX_VALUE));
        System.out.println(checkPerfectSquare(21));
        System.out.println(checkPerfectSquare(49));
    }
}
