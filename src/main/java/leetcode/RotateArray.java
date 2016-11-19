package leetcode;
//https://leetcode.com/problems/rotate-array/
//for lists use double inverse of sublists

public class RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) {
            return;
        }
        int moved = 0;
        int cycleStartIndex = 0;
        int temp1;
        while (moved < nums.length) {
            System.out.print("cycleSatrtIndex" + cycleStartIndex + "\n");
            int currentIndex = cycleStartIndex;
            temp1 = nums[cycleStartIndex];
            do {
                System.out.print("currentIndex" + currentIndex + "\n");
                int nextIndex = (currentIndex + k) % nums.length;
                int temp2 = nums[nextIndex];
                nums[nextIndex] = temp1;
                currentIndex = nextIndex;
                temp1 = temp2;
                moved++;
            } while (currentIndex != cycleStartIndex);
            cycleStartIndex++;
        }
    }
}
