package com.msb.lrg.problems.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * Problem 4: Thread-Safe Cache with ReadWriteLock
 * Problem Explanation
 * 		You want a cache:
 * 			Reads happen frequently, writes are rare.
 * 			Need concurrency-safe access with minimal blocking.
 */
public class ThreadSafeCacheWithLock<K,V> {
	
	Map<K,V> cache = new HashMap<>();
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public V get(K key) {
		lock.readLock().lock();
		try {
			return cache.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}
 
	public void put(K key, V value) {
		lock.writeLock().lock();
		try {
			cache.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	/*
	 * Explanation
	 * 		Multiple readers can proceed simultaneously.
	 * 		Only one writer locks out both readers and writers.
	 * 		Better scalability than synchronized.
	 * Highlights: Manual fine-grained locking strategy.
	 */
	public static void main(String[] args) {
		ThreadSafeCacheWithLock<String, String> cache = new ThreadSafeCacheWithLock<>();

		Runnable writer = () -> cache.put("user", "Alice");
		Runnable reader = () -> System.out.println("Read :: " + cache.get("user"));
		
		new Thread(writer).start();
		new Thread(reader).start();
	}

}
