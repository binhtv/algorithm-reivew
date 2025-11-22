package main.java.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without duplicate characters
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 */
public class LongestSubstrWithoutRepeatingChar {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }

        final Map<Character, Integer> indexMap = new HashMap<>(s.length());
        int maxCount = 0, left = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(indexMap.containsKey(c)) {
                left = Math.max(left, indexMap.get(c) + 1);
            }
            indexMap.put(c, i);
            maxCount = Math.max(maxCount, i - left + 1);
        }

        return maxCount;
    }
}
