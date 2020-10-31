package leetcode;

public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            prev = Math.max(nums[i], prev);
            if (prev == 0 && i != nums.length - 1) return false;
            prev -= 1;
        }
        return true;
    }
}