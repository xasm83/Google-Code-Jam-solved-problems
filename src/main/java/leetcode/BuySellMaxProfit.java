package leetcode;
//https://leetcode.com/problems/Best-Time-to-Buy-and-Sell-Stock/

public class BuySellMaxProfit {
    public class Solution {
        public int maxProfit(int[] nums) {
            int minIndex=0;
            int maxIndex=0;
            int nextMinIndex=0;

            if (nums.length==0){
                return 0;
            }

            for (int i=0;i<nums.length;i++){
                if (nums[i]< nums[minIndex] && nums[i]<nums[nextMinIndex]){
                    nextMinIndex=i;
                }

                if (nums[i]-nums[nextMinIndex]>0 && nums[i]-nums[nextMinIndex]>nums[maxIndex]-nums[minIndex]){
                    minIndex=nextMinIndex;
                    maxIndex=i;
                }
            }

            return nums[maxIndex]-nums[minIndex];
        }
    }
}
