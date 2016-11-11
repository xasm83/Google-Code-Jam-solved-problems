package leetcode;
//https://leetcode.com/problems/search-in-rotated-sorted-array/

import java.util.Arrays;

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        if (nums.length == 2) {
            int result = binarySearch(nums, 0, 1, target);
            return result;
        }

        int pivotIndex = getPivotIdex(nums);
        if (pivotIndex > 0) {
            int firstPivotResult = binarySearch(nums, 0, pivotIndex, target);
            return firstPivotResult >= 0 ? firstPivotResult : binarySearch(nums, pivotIndex + 1, nums.length - 1, target);
        } else {
            int result = binarySearch(nums, 0, nums.length - 1, target);
            return result;
        }
    }

    private int getPivotIdex(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (!(Integer.compare(nums[i - 1], nums[i]) == (Integer.compare(nums[i], nums[i + 1])))) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (nums[start] > nums[end]) {
            return binarySearchDesc(nums, start, end, target);
        }
        int result = Arrays.binarySearch(nums, start, end + 1, target);
        return result >= 0 ? result : -1;
    }

    private int binarySearchDesc(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int splitIndex = start + (end - start) / 2;
            if (nums[splitIndex] == target) {
                return splitIndex;
            }
            if (nums[splitIndex] < target) {
                end = splitIndex - 1;
            } else {
                start = splitIndex + 1;
            }
        }
        return -1;
    }
}