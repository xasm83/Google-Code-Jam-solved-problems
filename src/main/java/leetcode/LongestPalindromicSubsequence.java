package leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/longest-palindromic-subsequence/
//diff from palindromic substring  is that we continue search even if not  (s.charAt(0)==s.charAt(s.length()-1))
//top down approach, two dimensional array for bottom up approach
public class LongestPalindromicSubsequence {
    Map<String,Integer> map = new HashMap();
    public int longestPalindromeSubseq(String s) {
        return  lps(s);
    }

    int lps(String s){
        if (s.length()<2) {
            map.put(s, s.length());
            return s.length();
        }
        if (map.containsKey(s)) return map.get(s);
        int result;
        if (s.charAt(0)==s.charAt(s.length()-1)){ result = 2 + lps(s.substring(1,s.length()-1));}
        else { result = Math.max(lps(s.substring(1)),lps(s.substring(0,s.length()-1)));}
        map.put(s, result);
        return result;
    }
}
