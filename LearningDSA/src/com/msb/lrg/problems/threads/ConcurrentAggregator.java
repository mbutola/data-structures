package com.msb.lrg.problems.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Problem 3: Aggregating Data from Multiple Threads Using ConcurrentHashMap.merge()
 * Scenario
 * 		You have multiple threads processing logs or transactions, each updating a shared summary map.
 * 		Use thread-safe aggregation without locks.
 */
public class ConcurrentAggregator {
	
	/*
	 * Explanation
	 * 		merge(key, value, remappingFunction) updates atomically.
	 * 		If key absent → insert new.
	 * 		If present → apply Integer::sum.
	 * 		Multiple threads safely update totals without any explicit locks.
	 * Advanced idea: functional aggregation across threads with built-in atomic merge.
	 */

	public static void main(String[] args) throws InterruptedException {

		ConcurrentHashMap<String, Integer> sales = new ConcurrentHashMap<>();
		
		Runnable task = () -> {
			List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange");
			for(String fruit : fruits) {
				sales.merge(fruit, 1, Integer::sum);
			}		
		};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		for(int i=0; i<5; i++) {
			executor.submit(task);
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
		
		System.out.println(sales);

	}

}
