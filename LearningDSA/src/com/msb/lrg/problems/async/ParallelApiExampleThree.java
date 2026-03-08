package com.msb.lrg.problems.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Use Case 1: Parallel API Calls + Combine Results
 * Problem
 * 		You have to call two APIs:
 * 			getUserProfile(userId)
			getUserOrders(userId)
		Both are slow I/O tasks, and you want to run them in parallel, then combine results.
 */
public class ParallelApiExampleThree {

	/*
	 * Explanation
	 * 		Both API calls run concurrently.
	 * 		thenCombine() waits for both futures and merges results.
	 * 		join() blocks only when needed.
	 */
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> getUserProfile("0123"), executor);
		CompletableFuture<String> orderFuture = CompletableFuture.supplyAsync(() -> getUserOrders("0123"), executor);
		
		CompletableFuture<String> combined = userFuture.thenCombine(orderFuture, 
																	(s1,s2) -> s1 + " has " + s2);
		
		String result = combined.join();
		executor.shutdown();
		
		System.out.println("Result :: " + result);

	}

	static String getUserProfile(String userId) {
		sleep(1000);
		return "User[" + userId + "]";
	}
	
	static String getUserOrders(String userId) {
		sleep(1200);
		return "3 orders";
	}
	
	static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
