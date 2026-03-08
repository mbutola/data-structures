package com.msb.lrg.problems.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

//@FunctionalInterface
//interface ThrowingConsumer<T>{
//	void accept(T t) throws Exception;
//}

/*
 * Problem 1: Thread-Safe Cache with Auto-Compute (using ConcurrentHashMap.computeIfAbsent)
 * Scenario
 * 		You want to build a thread-safe cache that automatically computes a value the first time it’s 
 * 		requested — like memoization for expensive operations.
 * 		Multiple threads may request the same key simultaneously, but the computation should only happen once.
 */
public class ThreadSafeCache {

	@FunctionalInterface
	interface ThrowingConsumer<T>{
		void accept(T t) throws Exception;
	}
	
	/*
	 * Explanation
	 * 		computeIfAbsent() ensures that only one thread executes the function for a missing key.
	 * 		Others wait until the computation completes and reuse the same result.
	 * 		Perfect for caching expensive or I/O-bound computations.
	 *	Advanced idea: ConcurrentHashMap’s atomic operations eliminate explicit locking.
	 */
	
	private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
	
	String getData(String key) {
		return cache.computeIfAbsent(key, this::expensiveOperation);
	}
	
	private String expensiveOperation(String key) {
		System.out.println("Called expensiveOperation ..." );
		try {
			Thread.sleep(2000);
		}catch(InterruptedException ie) {}
		return "value_for_" + key;
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadSafeCache store = new ThreadSafeCache();
		Runnable task = () -> System.out.println(Thread.currentThread().getName() + " -> " + store.getData("apple"));  
		
		
		List<Thread> threads = new ArrayList<>();
		for(int i=0; i<10; i++) {
			threads.add(new Thread(task));
		}
		
		threads.forEach(Thread::start);
		
//		for(Thread t : threads)
//			t.join();

		/*
		 * Catch Inside Lambda
		 */
//		threads.forEach(t -> {
//			try{
//				t.join();
//			} catch(InterruptedException e) {
//				Thread.currentThread().interrupt();
//			}
//		});

		/*
		 * Define a Utility to Wrap Checked Exceptions
		 */
//		threads.forEach(sneaky(Thread::join));
	

	}

	static <T> Consumer<T> sneaky(ThrowingConsumer<T> tc){
		return t -> {
			try {
				tc.accept(t);
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

}
