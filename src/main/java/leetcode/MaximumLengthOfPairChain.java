package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthOfPairChain {

//    https://leetcode.com/problems/maximum-length-of-pair-chain/submissions/
//    sort by second and scan by first, as first isalways smaller than 2nd it will be found eventually
    public int findLongestChain(int[][] pairs) {
        if (pairs.length < 2) return pairs.length;
        Arrays.sort(pairs, (Comparator<int[]>) (a, b) -> Integer.compare(a[1], b[1]));
        int maxlen = 1;
        int currentIndex = 0;
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[currentIndex][1]<pairs[i][0]) {
                maxlen++;
                currentIndex = i ;
            }
        }
        return maxlen;
    }
}
