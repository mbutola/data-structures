package com.msb.lrg.problems.async;

import java.util.concurrent.CompletableFuture;

/*
 * Use Case 2: Dependent Async Tasks (Chaining Futures)
 * Problem
 * 		You must:
 * 			Fetch a user
 * 			Then fetch their address after user is available
 * 			Then format the result.
 * 		Each step depends on the previous.
 */
public class ChainedFutureExample {
	
	/*
	 * One-line difference (memorize this)	
		 	thenApply → map
			thenCompose → flatMap
	 * Explanation
	 * 		thenApply() chains dependent transformations synchronously.
	 * 		Each transformation receives the previous stage’s result.
	 * 		Entire pipeline is non-blocking except the final join().
	 * Key Learning: Build sequential dependency chains without blocking threads.
	 */
	public static void main(String[] args) {
		CompletableFuture.supplyAsync(() -> getUser("U100"))
						.thenCompose(user -> CompletableFuture.supplyAsync(() -> getAddress(user)))
						.thenApply(address -> "Delivery Address : " + address )
						.thenAccept(System.out::println)
						.join();
		
	}

	static String getUser(String id) {
		sleep(1000);
		return "Alice";
	}
	
	static String getAddress(String user) {
		sleep(1200);
		return user + "'s home, New Delhi";
	}
	
	/*
		Option 1: Propagate the InterruptedException (BEST if possible)
			When to use
				Your method can declare throws InterruptedException
				Let the caller decide what to do
				Code example
					public void process() throws InterruptedException {
					    // Blocking call
					    Thread.sleep(1000);
					
					    // More work
					    System.out.println("Processing completed");
					}
				Caller
					public static void main(String[] args) {
					    try {
					        new Worker().process();
					    } catch (InterruptedException e) {
					        System.out.println("Thread interrupted, stopping");
					        Thread.currentThread().interrupt(); // optional but recommended
					    }
					}
			✔ Clean
			✔ Correct
			✔ Best practice when allowed
		✅ Option 2: Restore the interrupt flag (MOST COMMON)
			When to use
				You cannot throw InterruptedException
				Typical in Runnable, Callable, loops, frameworks
				Code example
					public void run() {
					    try {
					        Thread.sleep(1000);
					    } catch (InterruptedException e) {
					        // Restore interrupt status
					        Thread.currentThread().interrupt();
					        return; // stop execution
					    }
					
					    System.out.println("This will not execute if interrupted");
					}
				✔ Interrupt is preserved
				✔ Higher-level code can see it
	 */
	static void sleep(long ms) { try { Thread.sleep(ms); } catch (InterruptedException e) {}}

}
