package wallethub;

import java.util.HashMap;
import java.util.Map;

//hash map single pass - O(n) complexity, space complexity - O(n)
//we can do array sort + binary search for O(1) space complexity and O(nlogn) computational complexity
public class TwoSum {

    /**
     * @return array with indexes of complimentary elements
     * @throws IllegalArgumentException when no solution
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution");
    }
}
