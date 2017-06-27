package wallethub;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TwoSumTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenNoSolutionShouldThrow() {
        TwoSum.twoSum(new int[]{1, 2}, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenOneElementShouldThrow() {
        TwoSum.twoSum(new int[]{1}, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyArrayShouldThrow() {
        TwoSum.twoSum(new int[]{}, 5);
    }

    @Test
    public void whenArrayShouldFindPair() {
        int[] result = TwoSum.twoSum(new int[]{6, 1, 2, 4}, 3);
        assertArrayEquals(new int[]{1, 2}, result);
    }
}
