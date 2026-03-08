package com.msb.lrg.problems.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Real-world analogy
 * 		Imagine a group of hikers who agree:
 * 		“No one moves to the next checkpoint until everyone arrives.”
 * 		Each hiker calls await() at the barrier.
 * 		Once everyone reaches the checkpoint, they all continue together to the next leg.
 */
public class CyclicBarrierExample {

	public static void main(String[] args) {
		int numWorkers = 3;
		Runnable barrierAction = () -> System.out.println("\nAll workers reached barrier. Proceeding together ...\n");
		CyclicBarrier barrier = new CyclicBarrier(numWorkers, barrierAction);
		
//		for(int i=0; i<3; i++) {
//			int workerId = i;
//			new Thread(() -> {
//				try {
//					System.out.println("Worker " + workerId + " started phase 1 work...");
//					Thread.sleep(1000*workerId);
//					
//					System.out.println("Worker " + workerId + " waitung at barrier...");
//					barrier.await();
//					
//					System.out.println("Worker " + workerId + " started phase 2 work...");
//				} catch(InterruptedException  | BrokenBarrierException e) {}
//				
//				
//			}).start();
//		}

        ExecutorService executor = Executors.newFixedThreadPool(numWorkers);

        for (int i = 1; i <= numWorkers; i++) {
            int workerId = i;
            executor.submit(() -> {
                try {
                    for (int phase = 1; phase <= 2; phase++) {
                        System.out.println("Worker " + workerId + " doing phase " + phase);
                        Thread.sleep(1000 * workerId);
                        
                        System.out.println("Worker " + workerId + " waiting at barrier ");
                        barrier.await(); // synchronize before next phase
                        
                        System.out.println("Worker " + workerId + " moving to next phase ");
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
	}
}
