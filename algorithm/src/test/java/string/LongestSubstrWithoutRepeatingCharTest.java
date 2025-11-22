package test.java.string;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static main.java.string.LongestSubstrWithoutRepeatingChar.lengthOfLongestSubstring;
import static org.hamcrest.MatcherAssert.assertThat;

public class LongestSubstrWithoutRepeatingCharTest {
    @Test
    public void testLongestSubstrWithoutRepeatingChar() {
        assertThat(lengthOfLongestSubstring("abcabcbb"), CoreMatchers.is(3));
        assertThat(lengthOfLongestSubstring("bbbbb"), CoreMatchers.is(1));
        assertThat(lengthOfLongestSubstring("pwwkew"), CoreMatchers.is(3));
    }
}
