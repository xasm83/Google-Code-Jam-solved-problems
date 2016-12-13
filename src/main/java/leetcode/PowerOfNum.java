package leetcode;

//https://leetcode.com/problems/powx-n/
public class PowerOfNum {
    public double myPow(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }

        if (n == 0) {
            return 1;
        }

        boolean odd = false;
        boolean negative = false;
        double result = x * x;

        if (n == Integer.MIN_VALUE) {
            n++;
            result *= x;
        }

        if (n < 0) {
            negative = true;
            n = -n;
        }

        if (n == Integer.MAX_VALUE) {
            n--;
            result *= x;
        }

        if (n % 2 == 1) {
            odd = true;
            n++;
        }

        int currentPower = 2;
        int tempPower = 2;
        double tempMultiplier = x * x;
        while (currentPower != n) {
            currentPower += tempPower;
            result *= tempMultiplier;
            if (currentPower + tempPower * 2 <= n && currentPower + tempPower * 2 > 0) {
                tempPower *= 2;
                tempMultiplier *= tempMultiplier;
            } else {
                tempMultiplier = x * x;
                tempPower = 2;
            }
        }

        if (odd) {
            result = result / x;
        }
        return negative ? 1 / result : result;
    }
}
