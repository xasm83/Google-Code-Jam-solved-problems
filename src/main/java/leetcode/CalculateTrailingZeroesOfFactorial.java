package leetcode;
//https://leetcode.com/problems/factorial-trailing-zeroes/

public class CalculateTrailingZeroesOfFactorial {
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 1) {
            n /= 5;
            count += n;
        }
        return count;
    }
}