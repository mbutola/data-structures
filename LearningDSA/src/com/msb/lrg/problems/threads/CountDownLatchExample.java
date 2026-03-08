package com.msb.lrg.problems.threads;

import java.util.concurrent.CountDownLatch;

/*
 * Real-world analogy
 * Imagine a race:
 * 		3 runners are at the starting line.
 * 		The race coordinator (main thread) waits until all runners say “Ready”.
 * 		Only when all 3 are ready (countdown 3 → 0), the coordinator starts the race.
 */
public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException {
		
		int taskCount = 3;
		CountDownLatch latch = new CountDownLatch(taskCount);
		
		for(int i=0; i<3; i++) {
			int workerId = i;
			new Thread(() -> {				
				System.out.println("worker :: " + workerId + " started.");
				try {
					Thread.sleep(500*workerId);
				} catch(InterruptedException e) {}
				System.out.println("worker :: " + workerId + " finished.");
				latch.countDown();
			}).start();
		}
		
		System.out.println("Main thread waiting for workers to finish...");
		latch.await();
		System.out.println("All workers finished. Proceeding ");
	
		/*
		 * Reverse Scenario — Waiting for Start Signal
		 * You can also use CountDownLatch as a start gate..
		 */
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(3);
		
		for(int i=0; i<3; i++) {
			new Thread(() -> {	
				try {
					startSignal.await();
					System.out.println(Thread.currentThread().getName() + " started.");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " finished.");
					doneSignal.countDown();
				} catch(InterruptedException e) {}
			}).start();
		}
		
		System.out.println("Ready...set...");
		Thread.sleep(2000);
		System.out.println("Go...");
		startSignal.countDown();
		
		doneSignal.await();
		System.out.println("All done... ");
		
	
	}

}
