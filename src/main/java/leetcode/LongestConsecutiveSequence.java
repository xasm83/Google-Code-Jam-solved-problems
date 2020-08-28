package leetcode;

import java.util.*;

//https://leetcode.com/problems/longest-consecutive-sequence/submissions/
//union find
//idea - if there are consequtive elements in set you can get surrounding ones by increasing or decreasing current one by 1
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length < 2) return nums.length;
        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(set::add);

        int maxLength = 1;
        while (!set.isEmpty()) {
            int item = set.iterator().next();
            set.remove(item);
            int currentLength = getLongestSequenceForItem(set, item);
            if (currentLength > maxLength) maxLength = currentLength;
        }
        return maxLength;
    }

    int getLongestSequenceForItem(Set<Integer> set, int item) {
        int minItem = item - 1;
        int maxItem = item + 1;
        int totalLength = 1;

        if (set.remove(minItem)) {
            totalLength += getLongestSequenceForItem(set, minItem);
        }
        if (set.remove(maxItem)) {
            totalLength += getLongestSequenceForItem(set, maxItem);
        }

        return totalLength;
    }

    public int longestConsecutiveLeetCodeVer(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
