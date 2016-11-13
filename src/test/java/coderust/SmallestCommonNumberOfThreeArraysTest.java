package coderust;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmallestCommonNumberOfThreeArraysTest {
    @Test
    public void shouldFindSmallestNumber() {
        int a[] = {1, 3, 15, 22, 38, 69};
        int b[] = {0, 4, 15, 77, 98, 300};
        int c[] = {1, 3, 5, 7, 8, 15};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 15);
    }


    @Test
    public void shouldFindSmallestNumberWithDups() {
        int a[] = {1, 3, 15, 22, 38, 69};
        int b[] = {0, 4, 15, 77, 98, 300};
        int c[] = {1, 3, 5, 5, 8, 15};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 15);
    }

    @Test
    public void shouldFindSmallestNumberWithFirst() {
        int a[] = {1, 3, 15, 22, 38, 69};
        int b[] = {1, 4, 15, 77, 98, 300};
        int c[] = {1, 3, 5, 5, 8, 15};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 1);
    }

    @Test
    public void shouldFindSmallestNumberWithLast() {
        int a[] = {1, 3, 15, 22, 38, 69, 1000};
        int b[] = {0, 4, 15, 77, 98, 300, 1000};
        int c[] = {1, 3, 5, 5, 8, 16, 1000};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 1000);

    }

    @Test
    public void shouldFindSmallestNumberForOneSingle() {
        int a[] = {1};
        int b[] = {1};
        int c[] = {1};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 1);


    }

    @Test
    public void shouldFindSmallestNumberForOneTuple() {
        int a[] = {7, 13};
        int b[] = {7, 9};
        int c[] = {4, 7};
        assertEquals(SmallestCommonNumberOfThreeArrays.findSmallestCommonNumber(a, b, c), 7);

    }
}
