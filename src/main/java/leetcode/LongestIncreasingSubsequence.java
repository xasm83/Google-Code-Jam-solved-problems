package leetcode;

import java.util.Arrays;
///https://leetcode.com/problems/longest-increasing-subsequence/
//contains a subsequence itself in auxiliary array
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int numsLength = nums.length;
        int[] minimumIndexPerLength = new int[numsLength + 1];
        int[] predecessorIndex = new int[numsLength];

        Arrays.fill(predecessorIndex, 0, numsLength, numsLength);
        Arrays.fill(minimumIndexPerLength, 0, numsLength + 1, numsLength);

        int maxLen = 0;
        for (int i = 0; i < numsLength; i++) {
            int length = getPreviousLength(minimumIndexPerLength, i, nums);
            int index = minimumIndexPerLength[length];

            if (minimumIndexPerLength[length + 1] == numsLength || nums[minimumIndexPerLength[length + 1]] > nums[i]) {
                predecessorIndex[i] = index != numsLength ? index : numsLength;
                minimumIndexPerLength[length + 1] = i;
                maxLen = length + 1 > maxLen ? length + 1 : maxLen;
            }
        }
        return maxLen;
    }

    private int getPreviousLength(int[] minimumIndexPerLength, int i, int[] nums) {
        int start = 0;
        int end = i;
        int currentIndex = 0;
        int value = nums[i];
        while (start <= end) {
            currentIndex = start + (end - start) / 2;
            int indexInNums = minimumIndexPerLength[currentIndex];
            if (indexInNums >= nums.length) {
                if (currentIndex != 0) {
                    end = currentIndex - 1;
                } else {
                    start = currentIndex + 1;
                }
            } else {
                int currentValue = nums[indexInNums];
                if (currentValue == value) {
                    return currentIndex > 0 ? currentIndex - 1 : 0;
                } else if (currentValue > value) {
                    end = currentIndex - 1;
                } else {
                    start = currentIndex + 1;
                }
            }

        }
        if (minimumIndexPerLength[currentIndex] == nums.length) {
            return currentIndex != 0 ? currentIndex - 1 : 0;
        }
        int lastItemInSequenceValue = nums[minimumIndexPerLength[currentIndex]];
        return lastItemInSequenceValue < value ? currentIndex : currentIndex > 0 ? currentIndex - 1 : 0;
    }
}
