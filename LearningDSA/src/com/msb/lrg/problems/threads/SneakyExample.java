package com.msb.lrg.problems.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SneakyExample {

	@FunctionalInterface
	interface ThrowingConsumer<T>{
		void accept(T t) throws Exception;
	}
	
	public static void main(String[] args) {
		
		List<Thread> tasks = new ArrayList<>();
		
		Runnable task = () -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println("Thread " + Thread.currentThread().getName());
		};
		
		for(int i=0; i<3; i++) {
			tasks.add(new Thread(task));
		}
		
		tasks.forEach(Thread::start);
		
		tasks.forEach(sneaky(Thread::join));
		
		System.out.println("All threads joined...");

	}
	
	static <T> Consumer<T> sneaky(ThrowingConsumer<T> tc){
		return t -> {
			try {
				tc.accept(t);
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		};
	}
 
}
