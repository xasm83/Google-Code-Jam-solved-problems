package leetcode;
//https://leetcode.com/problems/shortest-palindrome/
//append chars in front to get shortest palindrome


public class ShortestPalindrome {
    String shortestPalindrome(String s) {
        int n = s.length();
        int i = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                //it will match palindrome itself +
                //some extra matches before it gets to the right part of the palindrome
            }
        }
        if (i == n)
            return s;

        String remain_rev = s.substring(i, n);
        remain_rev = new StringBuilder(remain_rev).reverse().toString();
        return remain_rev + shortestPalindrome(s.substring(0, i)) + s.substring(i);
    }
}
