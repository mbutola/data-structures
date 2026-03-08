package com.msb.lrg.problems.async;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/*
 * Use Case 3: Combining Multiple Futures (AllOf / AnyOf)
 * Problem
 * 		You have 3 external microservice calls — run all in parallel and wait for all results (or first successful).
 */
public class AllOfExample {

	/*
	 * Explanation
	 * 		CompletableFuture.allOf() waits for all futures to finish.
	 * 		thenApply() collects results once all complete.
	 * 		Ideal for parallel fan-out workloads.
	 * Key Learning: Efficiently aggregate results from parallel async tasks.
	 */
	public static void main(String[] args) {
		List<CompletableFuture<String>> futures = List.of(
														CompletableFuture.supplyAsync(() -> fetch("Payment")),
														CompletableFuture.supplyAsync(() -> fetch("Inventory")),
														CompletableFuture.supplyAsync(() -> fetch("Shipping"))
													);
		//Sequential operation
//		futures.stream()
//			.map(CompletableFuture::join)
//			.collect(Collectors.toList())
//			.forEach(System.out::println);		

		// Paralled operation
		/*
		 	Mental model (remember this)
				toArray() without args gives Object[]
				toArray(T[]) gives typed array
				
			Java 11+ alternative (cleaner)
				If you’re on Java 11+:
					CompletableFuture<?>[] arr =
					    futures.toArray(CompletableFuture[]::new);
		 */
		CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
		
		/*
		 	.map(CompletableFuture::join)
				This is very important.
				It means:
					f -> f.join()
					join():
				Returns the result
				join() does NOT join futures together.
				It means:
					“Join the current thread with the completion of this CompletableFuture.”
		 */
		CompletableFuture<List<String>> results = all.thenApply(v ->
														futures.stream()
															.map(CompletableFuture::join)
															.collect(Collectors.toList()));
		/*
			The KEY idea (read this twice)
			There are two different things here:
				Joining individual futures → get their values
				Joining the result future itself → get the final list
		 */
		results.join()
				.stream()
				.forEach(System.out::println);
	}


    static String fetch(String service) {
        sleep((int) (Math.random() * 1500) + 500);
        return service + " OK";
    }

    static void sleep(long ms) { try { Thread.sleep(ms); } catch (Exception e) {} }

}
