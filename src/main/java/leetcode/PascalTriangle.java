package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle-ii/
//formula is taken from combination formula replacing k to running j n!/j!(n-j)!
public class PascalTriangle {
    public class Solution {
        public List<Integer> getRow(int n) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            long c = 1;
            for (int j = 0; j <= n; j++) {
                result.add((int) c);
                c *= n - j;
                c /= j + 1;
            }
            return result;
        }
    }
}
