package leetcode;

import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElementInArray {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> min_heap = new PriorityQueue<Integer>();

            for (int i = 0; i < nums.length; i++) {
                min_heap.add(nums[i]);
                if (min_heap.size() > k) {
                    min_heap.remove();
                }
            }
            return min_heap.peek();
        }
    }
}
