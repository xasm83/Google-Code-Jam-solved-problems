package coderust;

import java.util.Arrays;

//https://www.educative.io/collection/page/5642554087309312/5679846214598656/10003/preview
//arrays should be sorted otherwise the algorithm is not applicable

public class SmallestCommonNumberOfThreeArrays {
    public static int findSmallestCommonNumber(int[] a, int[] b, int[] c) {
        int aCurrent = 0;
        int bCurrent = 0;
        int cCurrent = 0;

        //pick a, check b then check c if items does not match pick a next one from the current array and do a backtrace to a
        //check if a has matching item for a picked one.
        //TODO could be re-written with 3 simple if and withour while true
        while (true) {
            int bInsertionPoint = Arrays.binarySearch(b, bCurrent, b.length, a[aCurrent]);
            if (bInsertionPoint >= 0) {
                int cInsertionPoint = Arrays.binarySearch(c, cCurrent, c.length, a[aCurrent]);
                if (cInsertionPoint >= 0) {
                    return c[cInsertionPoint];
                } else {
                    cCurrent = getNewCurrentFromInsertionPoint(c.length, cInsertionPoint);
                    bInsertionPoint = Arrays.binarySearch(b, bCurrent, b.length, c[cCurrent]);
                    if (bInsertionPoint >= 0) {
                        bCurrent = bInsertionPoint;
                    } else {
                        bCurrent = getNewCurrentFromInsertionPoint(b.length, bInsertionPoint);
                    }
                    aCurrent = getNewACurrent(a, b, aCurrent, bCurrent);
                }
            } else {
                bCurrent = getNewCurrentFromInsertionPoint(b.length, bInsertionPoint);
                aCurrent = getNewACurrent(a, b, aCurrent, bCurrent);
            }
        }
    }

    private static int getNewACurrent(int[] a, int[] b, int aCurrent, int bCurrent) {
        int aInsertionPoint = Arrays.binarySearch(a, aCurrent, a.length, b[bCurrent]);
        if (aInsertionPoint >= 0) {
            aCurrent = aInsertionPoint;
        } else {
            aCurrent = getNewCurrentFromInsertionPoint(a.length, aInsertionPoint);
        }
        return aCurrent;
    }

    private static int getNewCurrentFromInsertionPoint(int length, int insertionPoint) {
        int current = -(insertionPoint + 1);
        if (current == length) {
            throw new RuntimeException("Arrays do not have common items.");
        }
        return current;
    }
}
