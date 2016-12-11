package leetcode;
//https://leetcode.com/problems/sqrtx/
public class SqrtCalculation {

    public int mySqrt(int x) {
        if (x == 1 || x == 0) {
            return x;
        }

        int high = 46341;//SQRT(Integer.MAX_VALUE)+1
        int low = 1;
        int i = 2;

        while (i != high && i != low) {
            if (i * i == x) {
                return i;
            } else if (i * i > x && i < high) {
                high = i;
                i = high - (high - low) / 2;
            } else if (i > low && i * i < x) {
                low = i;
                i = low + (high - low) / 2;
            }
        }
        return low;
    }
}
