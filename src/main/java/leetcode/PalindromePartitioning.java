package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/submissions/
//recursive, permute previous part result with substring result
//use protective copying for lists to avoid results mix
public class PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        return partitionHelper(new ArrayList<List<String>>(), s);
    }

    private static List<List<String>> partitionHelper(List<List<String>> partialResult, String s) {
        List<List<String>> result = new ArrayList<>();
        if (partialResult.isEmpty()) {
            List<String> firstResult = new ArrayList<>();
            partialResult.add(firstResult);
        }

        for (int maxIndex = 0; maxIndex < s.length(); maxIndex++) {
            String candidate = s.substring(0, maxIndex + 1);
            System.out.println("string  " + s + ",candidate " + candidate + ",list " + partialResult + ",maxIndex " + maxIndex + ",part res " + partialResult + ", res " + result);
            if (isPalindrome(candidate)) {
                List<List<String>> newPartResult = new ArrayList<>();
                partialResult.forEach(list -> {
                    List<String> newList = new ArrayList<>(list);
                    newList.add(candidate);
                    newPartResult.add(newList);
                });
                result.addAll(partitionHelper(newPartResult, s.substring(maxIndex + 1)));

                if (candidate.length() == s.length()) {
                    result.addAll(newPartResult);
                }
            }
        }
        return result;
    }

    private static boolean isPalindrome(String str) {
        return (str.length() == 1) ||
                (str.length() == 2 && str.charAt(0) == str.charAt(1)) ||
                str.length() > 2 && isPalindrome(str.substring(1, str.length() - 1)) && str.charAt(0) == str.charAt(str.length() - 1);
    }
}