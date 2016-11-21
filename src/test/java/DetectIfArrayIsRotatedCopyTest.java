import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DetectIfArrayIsRotatedCopyTest {
    @Test
    public void testNormalArray() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3};
        assertEquals(true, DetectIfArrayIsRotatedCopy.detect(a, b));
    }

    @Test
    public void testSingleItemRotation() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 1};
        assertEquals(true, DetectIfArrayIsRotatedCopy.detect(a, b));
    }

    @Test
    public void testSingleTwoItemRotation() {
        int[] a = new int[]{1, 2};
        int[] b = new int[]{2, 1};
        assertEquals(true, DetectIfArrayIsRotatedCopy.detect(a, b));
    }


    @Test
    public void testNotRotatedArrays() {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{5, 7, 8};
        assertEquals(false, DetectIfArrayIsRotatedCopy.detect(a, b));
    }


    @Test
    public void testNotRotatedArraysDifferentSize() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{5, 1};
        assertEquals(false, DetectIfArrayIsRotatedCopy.detect(a, b));
    }


    @Test
    public void testNotRotatedArraysDifferentSizeLikeRotated() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{4, 1, 2, 3, 5};
        assertEquals(false, DetectIfArrayIsRotatedCopy.detect(a, b));
    }

    @Test
    public void testNotRotatedArraysLikeRotated() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{4, 1, 2, 7};
        assertEquals(false, DetectIfArrayIsRotatedCopy.detect(a, b));
    }

    @Test
    public void testRotatedWithDupes() {
        int[] a = new int[]{1, 1, 2, 4};
        int[] b = new int[]{1, 2, 4, 1};
        assertEquals(true, DetectIfArrayIsRotatedCopy.detect(a, b));
    }
}
