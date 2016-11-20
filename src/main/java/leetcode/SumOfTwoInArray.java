package leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/two-sum/
//nlogn, for o(n) hash map could be used if auxilary space is avail

public class SumOfTwoInArray {
    public static void main(String... args) {
        twoSum(new int[]{0, 0, 3, 4}, 0);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp, 0, temp.length);
        int low = 0;
        int high = temp.length - 1;
        while (low < high) {
            int diff = Math.abs(temp[low] + temp[high] - target);
            if (temp[low] + temp[high] == target) {
                System.out.print(Arrays.toString(temp));
                int firstIndex = indexOf(nums, temp[low]);
                int secondIndex;
                if (temp[low] == temp[high]) {
                    secondIndex = lastIndexOf(nums, temp[high]);
                } else {
                    secondIndex = indexOf(nums, temp[high]);
                }
                return new int[]{firstIndex, secondIndex};

            } else if (temp[low] + temp[high] > target) {
                high = Arrays.binarySearch(temp, low, high, temp[high] - diff);
                if (high < 0) {
                    high = -high - 2;
                }
            } else {
                low = Arrays.binarySearch(temp, low, high, temp[low] + diff);
                if (low < 0) {
                    low = -(low + 1);
                }
            }
        }
        return new int[]{0, 0};
    }

    private static int indexOf(int[] nums, int value) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static int lastIndexOf(int[] nums, int value) {
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == value) {
                index = i;
            }
        }
        return index;
    }
}
