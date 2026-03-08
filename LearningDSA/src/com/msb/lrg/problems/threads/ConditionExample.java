package com.msb.lrg.problems.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Example Code — Producer-Consumer Problem
 * Let’s use ReentrantLock + Condition to manage a bounded queue (size = 5).
 * 
	LOCK → protects shared data
	CONDITION → tells whether work can proceed
	WAIT → releases lock and sleeps until condition changes
	NOTIFY → signals waiting threads that condition may be true
	Or in one line:
		Threads acquire a lock, check a condition, wait if false, and get notified when another 
		thread changes the condition.

 */
public class ConditionExample {

	public static void main(String[] args) {
		BoundedBuffer buffer = new BoundedBuffer(5);
		
		new Thread(() -> {
			for(int i=0; i<10; i++) {
				try {
					buffer.put(i);
					System.out.println("Produced :: " + i);
					Thread.sleep(300);
				}catch(InterruptedException e) {};
			}
		}).start();

		new Thread(() -> {
			while(true) {
				try {
					int value = buffer.take();
					System.out.println("Consumed :: " + value);
					Thread.sleep(700);
				}catch(InterruptedException e) {};
			}
		}).start();
	
	}
	
	static class BoundedBuffer {
		
		Lock lock = new ReentrantLock();
		Condition notEmpty = lock.newCondition();
		Condition notFull = lock.newCondition();
		
		Queue<Integer> queue = new LinkedList<>();
		int capacity;
		
		BoundedBuffer(int capacity){
			this.capacity = capacity;
		}
		
		void put(int value) throws InterruptedException {
			lock.lock();
			try {
				if(queue.size() == capacity) {
					notFull.await();
				}
				queue.offer(value);
				notEmpty.signal();
			} finally {
				lock.unlock();
			}
		}
		
		int take() throws InterruptedException {
			lock.lock();
			try {
				if(queue.isEmpty()) {
					notEmpty.await();
				}
				int value = queue.poll();
				notFull.signal();
				return value;
			} finally {
				lock.unlock();
			}
		}
	}
}
