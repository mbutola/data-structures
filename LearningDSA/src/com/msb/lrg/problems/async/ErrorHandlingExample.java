package com.msb.lrg.problems.async;

import java.util.concurrent.CompletableFuture;

/*
 * Use Case 4: Error Handling & Recovery
 * 	Problem
 * 		One async operation fails. You want to:
 * 		Catch the exception
 * 		Log it
 * 		Return a fallback value (like a cached response)
 */
public class ErrorHandlingExample {

	/*
	 * Explanation
	 * 		exceptionally() acts like a catch block for async tasks.
	 * 		You can return a safe fallback or rethrow an exception.
	 * Key Learning: Robust fault tolerance for async pipelines.
	 */
	public static void main(String[] args) {
		String result = CompletableFuture.supplyAsync(() -> fetchData())
										.exceptionally(ex -> {
											System.out.println("Error :: " + ex.getMessage());
											return "CachedData";})
										.join();
		System.out.println("Result :: " + result);								
		
	}
	
    static String fetchData() {
        throw new RuntimeException("Service Unavailable");
    }
}
