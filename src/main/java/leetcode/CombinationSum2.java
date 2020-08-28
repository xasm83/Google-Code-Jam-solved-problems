package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum2 {
    private static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        helper(0, candidates, 0, target, new ArrayList<>());
        return result;
    }

    private static void helper(int preSum,
                               int[] candidates,
                               int start,
                               int target,
                               List<Integer> partialSolution) {
        if (start >= candidates.length) return;
        int arrayItem = candidates[start];

        if (arrayItem + preSum == target) {
            List<Integer> list = new ArrayList<>(partialSolution);
            list.add(arrayItem);
            result.add(list);
        }

        if (arrayItem + preSum < target) {
            List<Integer> list = new ArrayList<>(partialSolution);
            list.add(   arrayItem);
            helper(preSum + arrayItem, candidates, start, target, list);
        }
        helper(preSum, candidates, start + 1, target, partialSolution);
    }
}