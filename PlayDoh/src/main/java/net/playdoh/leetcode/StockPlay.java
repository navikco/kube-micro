package net.playdoh.leetcode;

public class StockPlay {

    public static void main(String[] args) {

        StockPlay stockPlay = new StockPlay();
        System.out.println("MaxProfit  :::>>> " + stockPlay.maxProfit(new int[]{7, 3, 9, 2, 10, 1}));
        System.out.println("MaxMultiDayProfit  :::>>> " + stockPlay.maxMultiDayProfit(new int[]{7, 3, 9, 2, 10, 1}));
    }

    public int maxMultiDayProfit(int[] prices) {

        int profit = 0;
        int low = Integer.MAX_VALUE, high = 0;
        for(int i = 0; i < prices.length; i++) {

            if(prices[i] < low && high == 0)
                low = prices[i];
            else if(prices[i] > high) {
                high = prices[i];
                if(i == prices.length - 1)
                    profit += high - low;
            }
            else {
                profit += high - low;
                high = 0;
                low = prices[i];
            }
        }
        return profit;
    }

    public int maxProfit(int[] prices) {

        int buy = Integer.MAX_VALUE, sell = 0;
        int profit = 0;
        for(int i=0; i<prices.length; i++) {

            if(prices[i] < buy) {
                buy = prices[i];
                sell = 0;
            }
            else if(prices[i] > sell)
                sell = prices[i];
            if(profit < sell - buy)
                profit = sell - buy;
        }
        if(profit < 0)
            return 0;
        else
            return profit;
    }
}