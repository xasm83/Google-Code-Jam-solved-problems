package leetcode;
//https://www.youtube.com/watch?v=oDhu5uGq_ic
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
//also kadanes algo and DP for variations + equeation optimization for DP see video
public class BestTimeBuyAndSellStocks {
        public int maxProfit(int[] prices) {
            int profit=0;
            for(int i=0;i<prices.length-1;i++){
                if (prices[i]<prices[i+1]) profit += prices[i+1]-prices[i];
            }
            return profit;
        }
    }

