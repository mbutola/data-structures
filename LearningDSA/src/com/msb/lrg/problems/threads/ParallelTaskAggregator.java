package com.msb.lrg.problems.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Problem 5: Concurrent Task Result Aggregation Using ConcurrentLinkedQueue + CompletableFuture
 * Scenario
 * 		You have multiple async tasks running in parallel, and you want to collect 
 * 		all results thread-safely as they complete.
 */
public class ParallelTaskAggregator {

	/*
	 * Explanation
	 * 		Each task runs asynchronously using CompletableFuture.runAsync.
	 * 		Results are safely added to a ConcurrentLinkedQueue.
	 * 		CompletableFuture.allOf(...).join() waits until all tasks complete.
	 * Advanced idea: combining concurrency utilities — thread pool + non-blocking collection + futures.
	 */
	public static void main(String[] args) {

		Queue<String> queue = new ConcurrentLinkedQueue<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<CompletableFuture<Void>> futures = new ArrayList<>();
			
		for(int i=1; i<=5; i++) {
			int taskId = i;
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
											try {
												Thread.sleep(500 + taskId*100);
												queue.offer("Task : " 
																+ taskId 
																+ " completed by thread : " 
																+ Thread.currentThread().getName());
											} catch(InterruptedException e) {
												Thread.currentThread().interrupt();
											}
										}, executor);
			futures.add(future);
		}
		
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		executor.shutdown();
		
		System.out.println("Results :: ");
		queue.forEach(System.out::println);
	}

}
