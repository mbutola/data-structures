package com.msb.lrg.problems.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Problem 2: Producer–Consumer with BlockingQueue
 * Scenario
 * 		Implement a producer-consumer pattern where multiple producers generate data and multiple consumers 
 * 		process it safely using a queue.
 */
public class ProducerConsumer {

	/*
	 * Explanation
	 * 		ArrayBlockingQueue automatically handles synchronization.
	 * 		put() blocks when queue is full; take() blocks when empty.
	 * 		No wait/notify required!
	 *	Advanced idea: eliminates race conditions and ensures natural backpressure.
	 */
	public static void main(String[] args) {
		
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

		Runnable producer = () -> {
			for(int i=1; i <= 10; i++) {
				try {
					queue.put(i);
					System.out.println(Thread.currentThread().getName() + " produced " + i);
					Thread.sleep(200);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}			
			}
		};
		
		Runnable consumer = () -> {
			while(true) {
				try {
					Integer val =  queue.take();
					System.out.println(Thread.currentThread().getName() + " consumed " + val);
					Thread.sleep(400);
				} catch(InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
		};
		
		new Thread(producer, "Producer 1").start();
		new Thread(producer, "Producer 2").start();
		new Thread(consumer, "Consumer 1").start();
		new Thread(consumer, "Consumer 2").start();

	}

}
