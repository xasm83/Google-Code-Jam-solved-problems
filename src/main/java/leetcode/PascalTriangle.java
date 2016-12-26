package leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle-ii/
//formula is taken from combination formula replacing k to running i N!/k!(n-k)!
public class PascalTriangle {
    public class Solution {
        public List<Integer> getRow(int rowIndex) {
            ArrayList<Integer> result = new ArrayList<Integer>();

            long c = 1;
            for (int j = 0; j <= rowIndex; j++) {
                result.add((int) c);
                c *= rowIndex - j;
                c /= j + 1;
            }
            return result;
        }
    }
}
