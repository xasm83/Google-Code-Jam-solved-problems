package leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/jump-game-ii/submissions/
//simple dp using min value so far as array index
public class JumpGame2 {
    public int jump(int[] nums) {
        int[] val = new int[nums.length];
        Arrays.fill(val, Integer.MAX_VALUE);
        val[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            int prev = i - 1 < 0 ? Integer.MAX_VALUE - 1 : val[i - 1];
            val[i] = Math.min(val[i], prev + 1);
            for (int j = 1; j <= nums[i] && j + i < nums.length; j++) {
                val[i + j] = Math.min(val[i + j], val[i] + 1);
            }
        }
        return val[nums.length - 1];
    }
}
