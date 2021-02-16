package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
//https://leetcode.com/problems/subsets/submissions/
public class ArraySubset {
    public List<List<Integer>> subsets(int[] nums) {
        int upper = (int) Math.pow(2, nums.length);
        Set<Integer> combinations = new HashSet<>();
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < upper; i++) {
            if (!combinations.contains(i) && !combinations.contains(Integer.reverse(i))) {
                combinations.add(i);
                List<Integer> comb = new LinkedList<Integer>();
                int combCode = i;
                for (int num : nums) {

                    if ((combCode & 1) == 1) {
                        comb.add(num);
                    }
                    combCode = combCode >> 1;
                }
                result.add(comb);
            }
        }
        return result;
    }
}
