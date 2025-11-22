package string.palindrom;

public class LongestPalindromicSubstring {
    private int expandFromTheCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int l1 = expandFromTheCenter(s, i, i);
            int l2 = expandFromTheCenter(s, i, i + 1);
            int l = Math.max(l1, l2);
            if (l > end - start) {
                start = i - (l - 1) / 2;
                end = i + l / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static void main(String... strings) {
        String str = "cbbd";
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(str));
    }
}
