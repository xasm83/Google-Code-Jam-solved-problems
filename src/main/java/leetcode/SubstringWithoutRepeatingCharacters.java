package leetcode;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
//two pointers sliding window  array of char index
public class SubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;


        int start = 0;
        int end = 0;
        int max = 1;
        boolean[] seen = new boolean[128];

        while (end < s.length()) {

            char next = s.charAt(end);
            if (seen[next]) {
                seen[s.charAt(start)] = false;
                start++;
            } else {
                seen[next] = true;
                max = Math.max(max, end - start + 1);
                end++;
            }
        }
        return max;
    }
}
