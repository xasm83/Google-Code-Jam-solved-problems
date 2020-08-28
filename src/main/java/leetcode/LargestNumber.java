package leetcode;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
//https://leetcode.com/problems/largest-number/submissions/
//custom per digit compare if a string is shorted it should be compared with the rest of digits of second string
public class LargestNumber {
    public static String largestNumber(int[] nums) {
        NavigableSet<Integer> candidates = new TreeSet<>(LargestNumber::compare);
        candidates.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        if (candidates.isEmpty() || candidates.stream().reduce(Integer::sum).get() == 0) return "0";
        StringBuilder sb = new StringBuilder();
        candidates.descendingIterator().forEachRemaining(sb::append);
        System.out.println(sb);
        return sb.toString();
    }

    private static int compare(Integer one, Integer another) {
        String oneString = String.valueOf(one);
        String anotherString = String.valueOf(another);

        if (oneString.length() == 0) return -1;

        int len = Math.min(oneString.length(), anotherString.length());
        for (int i = 0; i < len; i++) {
            if (oneString.charAt(i) != anotherString.charAt(i)) return oneString.charAt(i) - anotherString.charAt(i);
        }
        if (anotherString.length() == oneString.length()) return -1;
        return anotherString.length() > oneString.length() ? compare(one, Integer.valueOf(anotherString.substring(len))) :
                compare(Integer.valueOf(oneString.substring(len)), another);
    }
}
