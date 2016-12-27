package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/combination-sum/
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        List<Integer> item = new ArrayList<>();
        helper(candidates, 0, target, item, res);
        return res;
    }

    public void helper(int[] candidates, int index, int target, List<Integer> item, List<List<Integer>> res) {
        if (target == 0) {
            List<Integer> temp = new ArrayList<>(item);
            res.add(temp);
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) continue;
            if (target - candidates[i] < 0) return;
            item.add(candidates[i]);
            helper(candidates, i, target - candidates[i], item, res);
            item.remove(item.size() - 1);
        }
    }
}
