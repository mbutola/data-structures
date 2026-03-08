package com.msb.lrg.problems.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Use Case 5: Building a Reactive Pipeline (Supply → Transform → Merge → Handle)
 * Problem
 * 		Simulate a mini reactive system:
 * 		Fetch product info
 * 		Fetch discount asynchronously
 * 		Combine and compute final price
 * 		Handle failures gracefully
 */
public class ReactivePipelineExample {
	
	/*
	 * Explanation
	 * 		Two parallel futures (price + discount).
	 * 		thenCombineAsync() merges results when both are ready.
	 * 		handle() catches errors and applies fallback.
	 * 		Uses async pool for non-blocking combination.
	 * Key Learning:
	 * 		Combine async data sources
	 * 		Add resilience
	 * 		Stay fully non-blocking
	 */
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		double result = CompletableFuture.supplyAsync(() -> getProductPrice("Apple"), pool)
										.thenCombine(CompletableFuture.supplyAsync(() -> getDiscount(), pool), 
												(price,discount) -> price*(1 - discount))
										.handle((res, ex) -> {
											if(ex != null) {
												System.out.println("Error :: " + ex.getMessage());
												return 999.9;
											}
											return res;
										})
										.join();
		System.out.println("Price :: " + result);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	
    static double getProductPrice(String item) {
        sleep(800);
        return 1200.0;
    }

    static double getDiscount() {
        sleep(600);
        if (Math.random() < 0.3) throw new RuntimeException("Discount service down");
        return 0.10;
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (Exception e) {} }
}
