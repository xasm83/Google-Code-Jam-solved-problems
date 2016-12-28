package leetcode;
//https://leetcode.com/problems/shortest-palindrome/
//append chars in front to get shortest palindrome

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end && i - (len - 1) / 2 == 0) {
                end = i + len / 2;
            }
        }
        StringBuilder sb = new StringBuilder(s.substring(end + 1, s.length()));
        return sb.reverse() + s;
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
