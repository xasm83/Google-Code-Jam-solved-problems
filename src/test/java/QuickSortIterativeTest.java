import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortIterativeTest {
    @Test
    public void testNormalArray() {
        int[] nums = new int[]{4, 3, 2, 1};
        int[] result = new int[]{1, 2, 3, 4};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testRandomArray() {
        int[] nums = new int[]{4, 5, 2, 1};
        int[] result = new int[]{1, 2, 4, 5};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testEqualArray() {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int[] result = new int[]{1, 1, 1, 1, 1};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = new int[]{};
        int[] result = new int[]{};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testTwoArray() {
        int[] nums = new int[]{4, 3};
        int[] result = new int[]{3, 4};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testOddArray() {
        int[] nums = new int[]{5, 4, 3, 2, 1};
        int[] result = new int[]{1, 2, 3, 4, 5};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }

    @Test
    public void testEvenArray() {
        int[] nums = new int[]{4, 3, 2, 1};
        int[] result = new int[]{1, 2, 3, 4};
        QuickSortIterative.sort(nums);
        assertTrue(Arrays.equals(nums, result));
    }
}
