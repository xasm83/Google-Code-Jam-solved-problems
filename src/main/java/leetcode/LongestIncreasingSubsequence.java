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

//store max subsequence length(lis) for current element, for each next element look for lis value with elements smaller than current
class LongestIncreasingSubsequence2 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] lisForIndex = new int[nums.length];
        lisForIndex[0] = 1;
        int maxLisIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            lisForIndex[i] = getPreviousLisLengthForElement(lisForIndex, nums, i);
            maxLisIndex = lisForIndex[maxLisIndex] < lisForIndex[i] ? i : maxLisIndex;
            System.out.println("M " + maxLisIndex);
        }
        System.out.println(Arrays.toString(lisForIndex));
        return lisForIndex[maxLisIndex];
    }

    int getPreviousLisLengthForElement(int[] lisForIndex, int[] nums, int start) {
        int maxLisIndex = -1;
        for (int i = start - 1; i >= 0; i--) {
            if (nums[i] < nums[start]) {
                maxLisIndex = maxLisIndex < 0 || lisForIndex[maxLisIndex] < lisForIndex[i] ? i : maxLisIndex;
            }
        }
        return maxLisIndex >= 0 ? lisForIndex[maxLisIndex] + 1 : 1;
    }
}
