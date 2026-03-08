package com.msb.lrg.problems.threads;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Example 2 — Thread-safe Task Queue with Multiple Consumers
 * 		Use case: Many consumer threads waiting for work; producer pushes tasks.
 */
public class TaskQueue {

	Queue<Runnable> tasks = new LinkedList<>();
	
	public void addTask(Runnable task) {
		synchronized(tasks){
			tasks.add(task);
			tasks.notifyAll();
		}
	}

	public Runnable getTask() throws InterruptedException{
		synchronized(tasks){
			if(tasks.isEmpty())
				tasks.wait();
			return tasks.poll();
		}
	}

	/*
	 * Explanation
	 * 		getTask() waits if no tasks exist.
	 * 		addTask() adds a task and notify() wakes one worker.
	 * 		Multiple workers can be waiting; we use notify() to wake one at a time.
	 * 		You could use notifyAll() if you expect multiple tasks to be added at once.
	 */
	public static void main(String[] args) {
		
		TaskQueue queue = new TaskQueue();
		
		for(int i=0; i<3; i++) {
			new Thread(() -> {
						try {
							while(true) {
								Runnable task = queue.getTask();
								task.run();
							}
						} catch(InterruptedException e) {}
			}).start();			
		}
		
		for(int i=0; i<10; i++) {
			int taskId = i;
			queue.addTask(() -> System.out.println("Task" + taskId + " executed by " + Thread.currentThread().getName()));
		}

	}

}
