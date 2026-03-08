package com.msb.lrg.problems.threads;

/*
 * Example 3 — Simple Barrier (Threads Wait for Each Other)
 * Problem: You want N threads to wait until all are ready, then proceed.
 */
public class BarrierExample {

	private final int totalThreads;
	private int waiting = 0;
	private int batch = 0;
	
	BarrierExample(int totalThreads){
		this.totalThreads = totalThreads;
	}
	
	public synchronized void await() throws InterruptedException{
		waiting++;
		if(waiting < totalThreads) {
			wait();
		}else {
			waiting = 0;
			batch++;
			notifyAll();
		}
	}

	/*
	 * Explanation
	 * 		Each thread calls await() when ready.
	 * 		First two wait.
	 * 		When the third arrives → notifyAll() wakes everyone.
	 * 		All continue together — like a CyclicBarrier.
	 */
	public static void main(String[] args) {
		BarrierExample be = new BarrierExample(3);
		
		for(int i=0; i < 6; i++) {
			new Thread(() -> {
						try {
							System.out.println(Thread.currentThread().getName() + " ready");
							be.await();
							System.out.println(Thread.currentThread().getName() + ", batch :: " + be.batch + " started work");
						} catch(InterruptedException e) {}
					}).start();
		}

	}

}
