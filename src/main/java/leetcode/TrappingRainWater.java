package leetcode;

public class TrappingRainWater {
    //https://leetcode.com/problems/trapping-rain-water/submissions/

    public int trap(int[] height) {
        int total = 0;
        int start = 0;
        int end = 0;

        while (start < height.length - 1) {
            int localMax = 0;
            for (int i = start + 1; i <= height.length - 1; i++) {
                int localHeight = height[i];
                if (height[start] < localHeight) {
                    end = i;

                    break;
                } else if (localMax <= localHeight) {
                    localMax = localHeight;
                    end = i;
                }
            }
            System.out.println("Start: " + start + "end " + end);
            total = total + add(start, end, height);
            start = end;
        }
        return total;
    }

    int add(int start, int end, int[] height) {
        int sum = 0;
        int level = Math.min(height[start], height[end]);
        for (int i = start + 1; i < end; i++) {
            sum += level - height[i];
        }
        return sum;
    }
}