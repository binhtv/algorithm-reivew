package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LongestSubstringWithoutForbidden {
    public static int longestSubstring(String str, String[] forbidden) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        List<int[]> foundIndices = new ArrayList<>();
        for (String f : forbidden) {
            int idx = 0;
            while ((idx = str.indexOf(f, idx)) != -1) {
                foundIndices.add(new int[]{idx, f.length()});
                idx++;
            }
        }

        int size = foundIndices.size();
        if (size == 0) {
            return str.length();
        }

        Collections.sort(foundIndices, Comparator.comparingInt(x -> x[0]));

        int[] firstIndex = foundIndices.get(0);
        int[] lastIndex = foundIndices.get(size - 1);
        int longest = Math.max(firstIndex[0] + firstIndex[1] - 1, str.length() - lastIndex[0] - 1);
        for (int i = 0; i < size - 1; i++) {
            int[] idx = foundIndices.get(i);
            int[] idx1 = foundIndices.get(i + 1);
            longest = Math.max(longest, idx1[0] + idx1[1] - idx[0] - 2);
        }

        return longest;
    }

    public static void main(String... strings) {
        String s = "asdfoooooooo123atetuwoxlsieiehfoof382323";
        System.out.println(longestSubstring(s, new String[]{"foo", "ate"}));
    }
}
