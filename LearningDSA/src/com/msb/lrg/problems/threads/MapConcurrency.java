package com.msb.lrg.problems.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*

ConcurrentHashMap is a thread-safe HashMap designed for high concurrency.
It solves problems of:
	Race conditions
	Data corruption
	Poor performance of synchronized maps
	Fail-fast iteration issues
	Blocking in multi-threaded systems
Let’s understand clearly with problem → solution → example → internals.
✅ Problem 1 — HashMap is NOT thread safe
	Issue
		Multiple threads modifying HashMap can cause:
		Infinite loop (older Java)
		Data corruption
		Lost updates
		Inconsistent reads
✅ Problem 2 — Hashtable / synchronizedMap is slow
	Old solution:
		Collections.synchronizedMap(new HashMap<>())
		OR
		Hashtable
	Issue:
		Single lock for whole map
			→ Poor scalability
		In high traffic systems:
			Huge bottleneck
	✅ ConcurrentHashMap solves this
		It provides:
			Thread safety + High performance
		⭐ How ConcurrentHashMap Solves
			Java 7
				Uses:
					Segment locking
					Map divided into segments:
						Segment1
						Segment2
						Segment3
					Different threads lock different segments.
			Java 8 (IMPORTANT)
				Uses:
					CAS + synchronized on bucket
					No segment class.
					More scalable.
✅ Problem 3 — Atomic operations needed
	Example:
		map.put(key, map.get(key) + 1);
	Issue:
		Not atomic
	ConcurrentHashMap solution:
		map.compute(key, (k,v) -> v == null ? 1 : v + 1);
	Atomic.
✅ Problem 4 — Fail Fast Iterator
	HashMap iterator:
		ConcurrentModificationException
	ConcurrentHashMap:
		Weakly consistent iterator
		No exception
⭐ Advanced Follow-up (Very Important)
	Interviewer may ask:
		How to make this lock-free?
		Correct direction:
			CAS operations
			Atomic index mapping
			Lock striping
			Immutable snapshot list
			RCU pattern

 */
public class MapConcurrency {

	public static void main(String[] args) throws Exception {
		problem();
		solution();

	}

	/*	 
	 Two threads modify same HashMap structure simultaneously.
	HashMap is:
		NOT thread safe
		So internal state becomes corrupted or overwritten.
	✅ Reason 1 — Lost Updates
		Internally both may:
			Compute bucket index
			Modify same array slot
			Overwrite pointer chain
	✅ Reason 2 — Resize Race Condition (Most Important)
		If two threads resize simultaneously:
			Partial copy happens
			Some entries not copied
	✅ Reason 3 — Bucket Linked List Corruption
		Concurrent resize could create cycle
	✅ Reason 4 — Another Important Cause
		Memory visibility issue.
	⭐ Interview Level Answer
	If interviewer asks:
		Why HashMap gives less size in multi-threading?
		Answer:
			Because concurrent modification during resize and bucket update causes lost updates and 
			inconsistent internal state.	 
	 */
	static void problem() throws Exception {
		Map<Integer, Integer> map = new HashMap<>();
        
		Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(i, i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.size()); // May be wrong
		
	}

	static void solution() throws Exception {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        
		Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(i, i);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.size()); // May be wrong
		
	}
}
