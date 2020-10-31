package geeks;

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class MaximizeCutSegments {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String num = reader.readLine();
        int cases = Integer.parseInt(num);
        for (int i = 0; i < cases; i++) {
            String lenString = reader.readLine();
            int len = Integer.parseInt(lenString.trim());
            String numStr = reader.readLine();
            String[] numStrs = numStr.split(" ");
            int[] nums = Arrays.stream(numStrs)
                    .mapToInt(Integer::parseInt).toArray();
            solve(len, nums);
        }
    }

    private static void solve(int len, int[] nums) {


        int x = nums[0];
        int y = nums[1];
        int z = nums[2];

        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        if (x <= len) dp[x] = 1;
        if (y <= len) dp[y] = 1;
        if (z <= len) dp[z] = 1;

        for (int i = 0; i <= len; i++) {
            if (i >= x) {
                dp[i] = Math.max(dp[i], dp[i - x] + 1);
            }

            if (i >= y) {
                dp[i] = Math.max(dp[i], dp[i - y] + 1);
            }

            if (i >= z) {
                dp[i] = Math.max(dp[i], dp[i - z] + 1);
            }
        }
        System.out.println(dp[len]);
    }
}