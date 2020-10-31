package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
//https://leetcode.com/problems/maximum-gap/submissions/
//radix sort, selection sort, get count, merge previous count, fill by index
public class MaxGap {

    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int max = Collections.max(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int digits = (int) Math.log10(max) + 1;
        int exp = 1;
        while (digits > 0) {
            sortDigit(exp, nums);
            digits--;
            exp *= 10;
        }
        int maxg = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > maxg) maxg = nums[i] - nums[i - 1];
        }
        return maxg;
    }


    void sortDigit(int exp, int[] nums) {
        int[] sorted = new int[nums.length];
        int[] counts = new int[10];

        for (int num : nums) {
            counts[getDigit(exp, num)]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] == 0 ? counts[i - 1] : counts[i] + counts[i - 1];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            int index = counts[getDigit(exp, nums[i])]--;
            sorted[index - 1] = nums[i];
        }
        System.arraycopy(sorted, 0, nums, 0, nums.length);
    }

    int getDigit(int exp, int number) {
        return (number / exp) % 10;
    }
}
