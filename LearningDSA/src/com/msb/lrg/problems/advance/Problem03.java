package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/*

⭐ Problem 3 — First Successful Service (Race)
	🧩 Problem
		Three redundant services:
			svcA
			svcB
			svcC
		Return first successful result.
		Failures ignored.
		Classic multi-region race.
	🧠 Step-by-Step Explanation
		Wrap each call → never fail (null fallback)
		Start all in parallel
		anyOf returns first completed
		That is fastest successful service
		This models geo-redundant service race.

 */
public class Problem03 {

	static ExecutorService pool = Executors.newFixedThreadPool(3);
    static String svcA() { sleep(800); return "A"; }
    static String svcB() { sleep(400); throw new RuntimeException(); }
    static String svcC() { sleep(600); return "C"; }
	
	public static void main(String[] args) {	
		 System.out.println("First="+firstSuccess());
	        pool.shutdown();
	}
	
	static String firstSuccess() {
		CompletableFuture<String> fA = safeCall(Problem03::svcA);
		CompletableFuture<String> fB = safeCall(Problem03::svcB);
		CompletableFuture<String> fC = safeCall(Problem03::svcC);
		
		return (String)CompletableFuture.anyOf(fA, fB, fC).join();
	}
	public static CompletableFuture<String> safeCall(Supplier<String> svc) {
		return CompletableFuture.supplyAsync(svc, pool)
					.handle((res, ex) -> (ex == null) ? res : null);
	}
	
	static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	

}
