package com.msb.lrg.problems.threads;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Example 1 — Classic Producer-Consumer with wait / notify
 * This is the canonical use case.
 * Problem:
 * 		You have a shared buffer.
 * 		Producer adds items into the buffer.
 * 		Consumer removes items.
 * 		They must not step on each other.
 */
public class ProducerConsumerTwo {
	
	private final Queue<Integer> buffer = new LinkedList<>();
	private final int CAPACITY = 5;
 
	/*
	 * Step-by-step Explanation
	 * 		Both threads share a buffer object.
	 * 		Both produce() and consume() synchronize on the same lock: buffer.
	 * 		If buffer full → producer waits (buffer.wait()).
	 * 		If buffer empty → consumer waits.
	 * 		When producer adds → notifyAll() wakes consumers.
	 * 		When consumer removes → notifyAll() wakes producers.
	 * 		Only one thread can hold the buffer lock at a time.
	 */
	public static void main(String[] args) {
		ProducerConsumerTwo pc = new ProducerConsumerTwo();
		
		Thread producer = new Thread(() -> {
											try { 
												pc.produce();
											} catch(InterruptedException e) {}});
		Thread consumer = new Thread(() -> {
											try { 
												pc.consume();
											} catch(InterruptedException e) {}});
		producer.start();
		consumer.start();
	}
	
	public void produce() throws InterruptedException{
		int value = 0;
		while(true) {
			synchronized (buffer) {
				if(buffer.size() == CAPACITY)
					buffer.wait();
				buffer.offer(value);
				System.out.println("Produced :: " + value);
				value++;
				buffer.notifyAll();
			}
			Thread.sleep(500);
		}
	}
	
	public void consume() throws InterruptedException{
		while(true) {
			synchronized (buffer) {
				if(buffer.size() == 0)
					buffer.wait();
				int value = buffer.poll();
				System.out.println("Consumed :: " + value);
				buffer.notifyAll();
			}
			Thread.sleep(500);
		}
	}
	

}
