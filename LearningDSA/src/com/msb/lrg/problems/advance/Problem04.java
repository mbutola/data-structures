package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/*

⭐ Problem 4 — Async Map-Reduce Sum
	🧩 Problem
		Given list of IDs:
			Fetch value for each ID async
			Run all in parallel
			Sum results
		Classic async MapReduce.
	🧠 Step-by-Step Explanation
		Map each id → async future
		allOf waits all tasks
		Collect results via join
		Reduce (sum)
	This is async parallel map-reduce.

 */
public class Problem04 {

	static ExecutorService pool = Executors.newFixedThreadPool(3);
	
	public static void main(String[] args) {
        System.out.println(asyncSum(List.of(1,2,3,4)));
        pool.shutdown();
	}

	public static int asyncSum(List<Integer> list) {

//		List<CompletableFuture<String>> futures = List.of(
//				CompletableFuture.supplyAsync(() -> fetch("Payment")),
//				CompletableFuture.supplyAsync(() -> fetch("Inventory")),
//				CompletableFuture.supplyAsync(() -> fetch("Shipping"))
//			);
//
//		CompletableFuture<Void> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
//
//		CompletableFuture<List<String>> results = all.thenApply(v ->
//															futures.stream()
//																.map(CompletableFuture::join)
//																.collect(Collectors.toList()));
//
//		results.join()
//				.stream()
//				.forEach(System.out::println);
		
		List<CompletableFuture<Integer>> futures = 
								list.stream()
										.map(id -> 
											CompletableFuture.supplyAsync(() -> fetch(id))
										).toList();
		
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

		return futures.stream()
					.mapToInt(CompletableFuture::join)
					.sum();
	}

    static int fetch(int id){
        sleep(300);
        return id*10;
    }
    
    static void sleep(long ms){
        try{Thread.sleep(ms);}catch(Exception ignored){}
    }
}
