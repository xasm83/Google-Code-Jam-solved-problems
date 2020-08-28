package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public String reverseWords(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        String[] words = reversed.trim().split(" ");
        List<String> wds = Arrays.stream(words)
                .map(str -> new StringBuilder(str.trim()).reverse().toString())
                .filter(ss -> ss.length() > 0)
                .collect(Collectors.toList());
        return String.join(" ", wds);
    }
}
