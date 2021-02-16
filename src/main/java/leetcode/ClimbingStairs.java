package leetcode;

//https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs {
        public int climbStairs(int n) {
            int[] nums = new int[46];
            nums[0] =0;
            nums[1] =1;
            nums[2] = 2;

            for (int i=3;i<=n;i++){
                nums[i]= nums[i-1]+nums[i-2];
            }
            return nums[n];
        }
    }
