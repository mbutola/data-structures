package com.msb.lrg.problems.frequentlyasked;

/*

	📈 HackerRank — Stock Buy Sell (1 transaction)
	👉 This problem is often called:
		“Best Time to Buy and Sell Stock”
	🧠 Problem (simple words)
		Given prices:
			[7, 1, 5, 3, 6, 4]
		👉 Buy once and sell once
		👉 Maximize profit
	✅ Answer
		Buy at 1, Sell at 6 → Profit = 5
	🧠 Core idea
		👉 You don’t try all pairs
		👉 You track:
			Minimum price so far  
			Best profit so far
	⚙️ Step-by-step logic
		For each price:
			1. Update min price  
			2. Calculate profit = current - min  
			3. Update max profit
	🔍 Dry run
		Prices: [7, 1, 5, 3, 6, 4]
	Start:
		min = 7, profit = 0
		1 → min = 1  
		5 → profit = 4  
		3 → profit = 2  
		6 → profit = 5 ✔  

 */
public class F002StockBuySell {

	public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int res = maxProfit(prices);
        System.out.println(res);
    }
	
	public static int maxProfit(int[] prices) {
		
		int minPrice = Integer.MAX_VALUE;
		int maxProfit = Integer.MIN_VALUE;
		
		for(int price : prices) {
			if(price < minPrice) {
				minPrice = price;
			} else {
				int profit = price - minPrice;
				maxProfit = Math.max(maxProfit, profit);
			}
		}
		
		return maxProfit;
	
	}
}
