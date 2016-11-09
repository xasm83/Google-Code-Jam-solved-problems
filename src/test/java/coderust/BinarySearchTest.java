package coderust;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTest {


    @Test
    public void shouldFindIndexForTwoItems() {
        int[] sortedArray = {-2, 1};
        int index = BinarySearch.binarySearch(1, sortedArray);
        assertEquals(index, 1);
    }

    @Test
    public void shouldFindIndexForOneItem() {
        int[] sortedArray = {-2};
        int index = BinarySearch.binarySearch(-2, sortedArray);
        assertEquals(index, 0);
    }

    @Test
    public void shouldNotFindIndexForOneItem() {
        int[] sortedArray = {-2};
        int index = BinarySearch.binarySearch(2, sortedArray);
        assertEquals(index, -1);
    }

    @Test
    public void shouldNotFindIndexForOneItemWhichSmaller() {
        int[] sortedArray = {-2};
        int index = BinarySearch.binarySearch(-20, sortedArray);
        assertEquals(index, -1);
    }

    @Test
    public void shouldNotFindIndexForTwoItems() {
        int[] sortedArray = {-2, 1};
        int index = BinarySearch.binarySearch(0, sortedArray);
        assertEquals(index, -1);
    }


    @Test
    public void shouldNotFindIndexForEmpty() {
        int[] sortedArray = {};
        int index = BinarySearch.binarySearch(1, sortedArray);
        assertEquals(index, -1);
    }

    @Test
    public void shouldFindIndexForOdd() {
        int[] sortedArray = {-2, -1, 0};
        int index = BinarySearch.binarySearch(0, sortedArray);
        assertEquals(index, 2);
    }

    @Test
    public void shouldFindIndexForEven() {
        int[] sortedArray = {-2, -1, 0, 1, 55};
        int index = BinarySearch.binarySearch(0, sortedArray);
        assertEquals(index, 2);
    }

    @Test
    public void shouldFindLastElement() {
        int[] sortedArray = {-2, -1, 0, 1, 55};
        int index = BinarySearch.binarySearch(55, sortedArray);
        assertEquals(index, 4);
    }
}
