package leetcode;
//https://leetcode.com/problems/maximum-product-subarray/
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = Integer.MIN_VALUE;
        int[] dpPositive = new int[nums.length];
        int[] dpNegative = new int[nums.length];
        dpPositive[0] = nums[0];
        dpNegative[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dpNegative[i] = Math.min(nums[i],
                    Math.min(dpPositive[i - 1] * nums[i], dpNegative[i - 1] * nums[i]));
            dpPositive[i] = Math.max(nums[i],
                    Math.max(dpPositive[i - 1] * nums[i], dpNegative[i - 1] * nums[i]));
        }
        max = dpPositive[0];
        for (int i = 1; i < nums.length; i++) {
            if (dpPositive[i] > max) {
                max = dpPositive[i];
            }
        }
        return max;
    }
}