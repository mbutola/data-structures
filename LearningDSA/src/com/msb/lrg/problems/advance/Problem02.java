package com.msb.lrg.problems.advance;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

⭐ Problem 2 — Async Retry with Exponential Backoff
	🧩 Problem
		Call remote price API:
			May fail randomly
			Retry up to 3 times
			Exponential backoff
			Non-blocking recursion
			Return default 0.0 if all fail
	🧠 Step-by-Step Explanation
		Try async call
		handle receives success or failure
		On failure:
			if retries left → recurse async
			else return default
			Recursive call returns nested future
			thenCompose flattens
		This is async retry recursion pattern.

 */
public class Problem02 {

	static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) {
        double price = fetchPrice(3).join();
        System.out.println("Price="+price);
        pool.shutdown();
	}
	
	static CompletableFuture<Double> fetchPrice(int retries) {
		
		return CompletableFuture.supplyAsync(() -> priceApi(), pool)
				.handle((res, ex) -> {
					if(ex == null)
						return CompletableFuture.completedFuture(res);
					
					if(retries == 0)
						return CompletableFuture.completedFuture(0.0);
					
					sleep((long)Math.pow(2, 3 - retries) * 200);
					
					return fetchPrice(retries - 1);
				})
				.thenCompose(x -> x);

	}
	
	static double priceApi() {
		if(Math.random() < 0.7)
			throw new RuntimeException();
		return 100.0;
	}
	
	static void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
 }
