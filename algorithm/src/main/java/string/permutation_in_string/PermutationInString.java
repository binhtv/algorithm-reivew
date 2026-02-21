package string.permutation_in_string;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 * <br/>
 * <br/>
 * <p><strong>Example 1:</strong></p>
 * Input: s1 = "ab" s2 = "eidbaooo"<br/>
 * Output: True<br/>
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <br/>
 * <br/>
 * <p><strong>Example 2:</strong></p>
 * Input:s1= "ab" s2 = "eidboaoo"<br/>
 * Output: False<br/>
 * <br/>
 * <p><strong>Constraints</strong></p>
 * <ul>
 *     <li>The input strings only contain lower case letters.</li>
 *     <li>The length of both given strings is in range [1, 10,000].</li>
 * </ul>
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return false;
        }

        int l1 = s1.length(), l2 = s2.length();
        int[] count = new int[26];
        for (int i = 0; i < l1; i++) {
            count[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < l2; i++) {
            count[s2.charAt(i) - 'a']--;
            if (i - l1 >= 0) {
                count[s2.charAt(i - l1) - 'a']++;
            }
            if (allZero(count)) {
                return true;
            }
        }

        return false;
    }

    private boolean allZero(int[] arr) {
        for (int i : arr) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String... strings) {
        System.out.println(new PermutationInString().checkInclusion("ab", "eidbaooo"));
        System.out.println(new PermutationInString().checkInclusion("ab", "eidbpaooo"));
    }
}
