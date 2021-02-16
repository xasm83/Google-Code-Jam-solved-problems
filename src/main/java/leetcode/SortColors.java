package leetcode;

//https://leetcode.com/problems/sort-colors/submissions/
public class SortColors {
    public void sortColors(int[] nums) {
        int[] col = new int[3];
        for (int i = 0; i < nums.length; i++) {
            col[nums[i]]++;
        }
        int current = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < col[i]; j++) {
                nums[current] = i;
                current++;
            }
        }
    }
}