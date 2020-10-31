package leetcode;

//https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/?currentPage=1&orderBy=hot&query=
//swaps are independent

public class MinimumSwaps {
    public int minSwap(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0, s1 = 1;
        for (int i = 1; i < A.length; ++i) {
            int n2 = Integer.MAX_VALUE, s2 = Integer.MAX_VALUE;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
//                a2 was not swapped so a1 is swapped
                n2 = Math.min(n2, s1);
//               a2 was swapped so a1 is natural
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }
}
