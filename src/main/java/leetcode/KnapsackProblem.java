package leetcode;

import java.util.Arrays;

//https://www.educative.io/courses/algorithms-coding-interviews-java/q2mVgvQ6K2p#coding-exercise
//we check all weight elements for a given weight threshold, for each element we either include or exclude it,
//we exclude if current weight threshold smaller than elements weight,
//if include check max i-1 element and current
public class KnapsackProblem {
    public static int Knapsack(int profits[], int lp, int weights[], int lw, int capacity) {
        int totalWeight = capacity;


//      max profit for a given weight using elements up to index
        int[][] profitsSums = new int[weights.length + 1][totalWeight + 1];
        for (int index = 1; index <= weights.length; index++) {
            for (int weight = 1; weight <= totalWeight; weight++) {

                if (weight < weights[index - 1]) {
                    profitsSums[index][weight] = profitsSums[index - 1][weight];
                } else {
                    profitsSums[index][weight] =
                            Math.max(profitsSums[index - 1][weight],
                                    profitsSums[index - 1][weight - weights[index - 1]] + profits[index - 1]);
                }
            }
        }
        return profitsSums[weights.length][totalWeight];
    }
}