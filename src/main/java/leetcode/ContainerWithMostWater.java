package leetcode;
//https://leetcode.com/problems/container-with-most-water/submissions/
//two pointers, start wide, move the one with shortest one as the next one might be higher
public class ContainerWithMostWater {
        public int maxArea(int[] height) {
            int maxarea = 0, l = 0, r = height.length - 1;
            while (l < r) {
                maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
                if (height[l] < height[r])
                    l++;
                else
                    r--;
            }
            return maxarea;
        }
    }
