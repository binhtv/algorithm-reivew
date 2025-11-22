package string.partition_labels;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part,
 * and return a list of integers representing the size of these parts.
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */
public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.isEmpty()) {
            return result;
        }

        int n = s.length(), maxIdx = -1, startIdx = -1;
        int[] indexes = new int[26];
        for(int i = 0; i < n; i++) {
            indexes[s.charAt(i) - 'a'] = i;
        }

        for(int i = 0; i < n; i++) {
            maxIdx = Math.max(maxIdx, indexes[s.charAt(i) - 'a']);
            if(maxIdx == i) {
                result.add(maxIdx - startIdx);
                startIdx = i;
            }
        }

        return result;
    }

    public static void main(String... strings) {
        String s = "ababcbacadefegdehijhklijhghghiuuuuowowowo";
        System.out.println(partitionLabels(s));
    }
}
