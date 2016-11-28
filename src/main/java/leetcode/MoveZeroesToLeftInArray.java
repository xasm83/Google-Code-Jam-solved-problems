package leetcode;

//https://leetcode.com/problems/move-zeroes/ in place linear time

public class MoveZeroesToLeftInArray {
    public class Solution {
        public void moveZeroes(int[] nums) {
            int positionForSwap = 0;
            for (int i = 0; i < nums.length; i++) {
                boolean needSwap = i > 0 && nums[i - 1] == 0 && nums[i] != 0;
                if (needSwap) {
                    nums[positionForSwap] = nums[i];
                    nums[i] = 0;
                    positionForSwap++;
                } else if (nums[i] != 0) {
                    positionForSwap = i + 1;
                }

            }
        }
    }
}
