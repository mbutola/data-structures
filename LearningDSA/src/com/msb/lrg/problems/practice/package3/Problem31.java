package com.msb.lrg.problems.practice.package3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Minimum Cost to Connect Sticks — LeetCode 1167
	You are given sticks with different lengths.
	You can connect any two sticks at a time, and the cost of connecting them is the sum of their lengths.
	The new stick (with combined length) goes back and can be connected again.
	
	👉 Goal: Connect all sticks into one with minimum total cost.
	Example 1 :
		Input: arr[] = [4, 3, 2, 6]
		Output: 29
		Explanation: First connect 2 and 3 to get [4, 5, 6] with a cost of 5, then connect 4 and 5 to get [9, 6] with a cost of 9, and finally connect 9 and 6 to get one rope with a cost of 15, giving a total minimum cost of 29. Any other order, such as connecting 4 and 6 first, results in a higher total cost of 38.
	Example 2 :
		Input: arr[] = [4, 2, 7, 6, 9]
		Output: 62 
		Explanation: First, connect ropes 4 and 2, which makes the array [6, 7, 6, 9]. Cost of this operation 4 + 2 = 6. Next, add ropes 6 and 6, which results in [12, 7, 9]. Cost of this operation 6 + 6 = 12. Then, add 7 and 9, which makes the array [12,16]. Cost of this operation 7 + 9 = 16. And finally, add these two which gives [28]. Hence, the total cost is 6 + 12 + 16 + 28 = 62.
	Example 3 :
		Input: arr[] = [10]
		Output: 0
		Explanation: Since there is only one rope, no connections are needed, so the cost is 0.
	Constraints:
		1 ≤ arr.size() ≤ 105
		1 ≤ arr[i] ≤ 104	

 */
public class Problem31 {

	public static void main(String[] args) {
		int[] arr = {4, 3, 2, 6};
		int res = minCost(arr);
		System.out.println("Result :: " + res);
	}

    public static int minCost(int[] arr) {
    	
    	if(arr == null || arr.length <= 1) return 0;

    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	for(int cost : arr) {
    		pq.offer(cost);
    	}

    	int totalCost = 0;
    	while(pq.size() > 1) {
    		int first = pq.poll();
    		int second = pq.poll();
    		int cost = first + second;
    		totalCost += cost;
    		pq.offer(cost);
    	}
    	
    	return totalCost;
    }

}
