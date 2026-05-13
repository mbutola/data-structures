package com.msb.lrg.problems.design;

import java.util.HashMap;
import java.util.Map;

/*
 
✅ Problem Statement — LRU Cache
	Design a data structure that supports:
		get(key) → return value if present, else -1
		put(key, value) → insert/update value
	Constraints:
		Cache has fixed capacity
		When full → remove Least Recently Used (LRU) item
	Both operations must run in:
		O(1)
✅ Key Idea
	We need:
		Fast lookup → HashMap
		Maintain usage order → Doubly Linked List
	Why?
		| Structure | Purpose               |
		| --------- | --------------------- |
		| HashMap   | O(1) find node        |
		| DLL       | O(1) move/remove node |
✅ Approach
	Maintain:
		HashMap<key, Node>
		Doubly Linked List
	DLL Order:
		Head → Most Recently Used
		Tail → Least Recently Used
	get(key)
		If key not present → return -1
		Else:
			Move node to front (MRU)
			Return value
	put(key, value)
		If key exists:
			Update value
			Move to front
		If key new:
			If capacity full:
				Remove LRU (tail.prev)
				Remove from map
				Insert new node at front
				Add to map
✅ Time Complexity
	| Operation | Complexity |
	| --------- | ---------- |
	| get       | O(1)       |
	| put       | O(1)       |
	Because:
	HashMap lookup → O(1)
	DLL insert/remove → O(1)
✅ Space Complexity
	O(capacity)
✅ How to Improve Design (FAANG Follow-ups)
	Interviewers often ask:
	1️ Thread-Safe LRU
		Use:
			ConcurrentHashMap
			ReentrantLock
	2️ Distributed LRU Cache
		Use:
			Consistent Hashing
			Sharded cache nodes
		Example:
			Redis Cluster
			Memcached Cluster
	3️ Memory Efficient LRU
		Use:
			Segmented LRU
			Clock Algorithm
			TinyLFU
		Used in:
			Caffeine cache
			Redis modern eviction
	4️ Hybrid LRU + LFU
		Production systems use:
			LRU for recency
			LFU for popularity
	5️ Real-World Improvements
		Add:
			TTL expiry
			Cache warming
			Admission control
			Eviction policy tuning
		⭐ Interview Golden Explanation
		Say:
			I will use HashMap for O(1) lookup and Doubly Linked List to maintain usage order.
			Every access moves node to front.
			When capacity exceeded, remove tail node.
			This is perfect FAANG coding-round answer

⭐ FAANG Interview Insight (Very Important)
	If interviewer asks:
		How to improve LRU?
		Best answer:
			Use TinyLFU admission + Segmented LRU eviction
 
 */

public class A1LRUDesign {

	public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1

        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // -1

        cache.put(4, 4); // evicts key 1
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
	}
	
	static class LRUCache {
		Map<Integer, Node> map;
		DLL usage; 
		int capacity;
		
		LRUCache(int capacity) {
			map = new HashMap<>(capacity);
			usage = new DLL();
			this.capacity = capacity;
		}
		
		public void put(int key, int val) {
			Node n;
			if(map.containsKey(key)) {
				n = map.get(Integer.valueOf(key));
				usage.remove(n);
				n.val = val;
			} else if(map.size() == capacity) {
				n = usage.removeLast();
				map.remove(n.key);
				n = new Node(key, val);	
			} else {
				n = new Node(key, val);
			}
			map.put(key, n);
			usage.add(n);

		}
		
		public int get(int key) {
			if(!map.containsKey(key))
				return -1;
			
			Node n = map.get(key);
			usage.remove(n);
			usage.add(n);
			return n.val;
		}
		
	}
	
	static class DLL {
		
		Node head;
		Node tail;
		
		DLL(){
			head = new Node();
			tail = new Node();
			head.next = tail;
			tail.prev = head;
		}
		
		public void add(Node n) {
			n.next = head.next;
			head.next.prev = n;
			head.next = n;
			n.prev = head;
		}
		
		public Node remove(Node n) {
			n.prev.next = n.next;
			n.next.prev = n.prev;
			return n;
		}

		public Node removeLast() {
			
			if(tail.prev == head)
				return null;

			return remove(tail.prev);

		}
	}
	
	static class Node {
		int key;
		int val;
		Node next;
		Node prev;
		
		Node(){
		}
		
		Node(int key, int val){
			this.key = key;
			this.val = val;
			next = null;
			prev = null;
		}
	}

//	static class LRUCache {
//		Map<Integer, Node> map;
//		int capacity;
//
//		Node head;
//		Node tail;
//
//		LRUCache(int capacity) {
//			map = new HashMap<>(capacity);
//			this.capacity = capacity;
//
//			head = new Node();
//			tail = new Node();
//			head.next = tail;
//			tail.prev = head;
//			
//		}
//		
//		public void put(int key, int val) {
//			Node n;
//			if(map.containsKey(key)) {
//				n = map.get(Integer.valueOf(key));
//				remove(n);
//				n.val = val;
//			} else if(map.size() == capacity) {
//				n = removeLast();
//				map.remove(n.key);
//				n = new Node(key, val);	
//			} else {
//				n = new Node(key, val);
//			}
//			map.put(key, n);
//			add(n);
//
//		}
//		
//		public int get(int key) {
//			if(!map.containsKey(key))
//				return -1;
//			
//			Node n = map.get(key);
//			remove(n);
//			add(n);
//			return n.val;
//		}
//		
//		public void add(Node n) {
//			n.next = head.next;
//			head.next.prev = n;
//			head.next = n;
//			n.prev = head;
//		}
//		
//		public Node remove(Node n) {
//			n.prev.next = n.next;
//			n.next.prev = n.prev;
//			return n;
//		}
//
//		public Node removeLast() {
//			
//			if(tail.prev == head)
//				return null;
//
//			return remove(tail.prev);
//
//		}
//	}
	

/*

Normal LRU (HashMap + DLL) is not used directly at scale because:
	❗ Memory overhead
	❗ Poor eviction quality under bursty workloads
	❗ Cache pollution problem
So real systems use memory-efficient or smarter LRU variants.
	We’ll explain deeply:
		1️ Segmented LRU
		2️ Clock Algorithm
		3️ TinyLFU
		4️ How Redis & Caffeine use them
🔥 Why Normal LRU Fails at Scale
	Problems:
	❌ 1. Cache Pollution
		Example:
			Scan workload:
				Read 1M new keys once
			LRU will:
				Evict useful hot keys
				Bad eviction decision.
	❌ 2. Memory Overhead
		DLL node needs:
			prev pointer
			next pointer
			object overhead
		Huge memory waste in:
			billions of keys cache
	❌ 3. Write Amplification
		Every access:
			move node
			update pointers
			High CPU cost.
🥇 1️ Segmented LRU (SLRU)
	Concept
		Split cache into:
			Probation segment
			Protected segment
		Meaning:
			New items → probation
			Frequently used → protected
	How It Works
		Step 1
			New item inserted:
				→ probation segment
		Step 2
			If accessed again:
				→ move to protected segment
		Step 3
		Eviction:
			Evict from probation first
		So:
		Frequently used items survive longer
	Why Better Than LRU
		Normal LRU:
			recent ≠ important
		SLRU:
			frequent + recent = important
🥈 2️ Clock Algorithm (Second Chance)
	This is:
		Memory efficient LRU approximation
	Instead of DLL:
		Use circular array
	Each entry has:
		Reference bit
	How It Works
		Pointer rotates like clock:
			🕐 → 🕑 → 🕒 → 🕓
		When eviction needed:
			Check entry:
				If reference bit = 1
					Set to 0
					Skip
				If reference bit = 0
					Evict
	Why Memory Efficient
		No:
			prev pointer
			next pointer
		Just:
			bit + array slot
		Huge saving.
	Example
		Cache:
			A B C D
		Access:
			A B
		Bits:
			A=1 B=1 C=0 D=0
		Eviction pointer:
			Check A → reset → skip
			Check B → reset → skip
			Check C → evict
	Real Usage
	Used in:
	OS page replacement
	PostgreSQL buffer cache
	Redis approximated LRU
🥉 3️ TinyLFU (Modern State of the Art)
	This is VERY IMPORTANT for FAANG
	TinyLFU solves:
		Cache admission problem
	Meaning:
		Before inserting → decide worth caching?
	Key Idea
		Track frequency using:
			Count-Min Sketch (probabilistic)
		So:
			Low frequency keys → rejected
			Even if recent.
	Why Needed
		LRU problem:
			One-time scan pollutes cache
		TinyLFU:
			Scan keys never enter cache
			Huge improvement.
	How TinyLFU Works
		Two steps:
		1️⃣ Admission policy
			Check:
				freq(newKey) > freq(victim)
			If yes:
				Replace victim
			Else:
				Reject insertion
		2️⃣ Eviction policy
			Usually combined with:
				Segmented LRU
			So modern cache:
				TinyLFU + SLRU
	Example
		Cache:
			A freq=100
			B freq=90
		New key:
			X freq=1
		LRU would:
			Evict A or B
		TinyLFU:
			Reject X
		Much smarter.
⭐ Summary Comparison
	| Algorithm     | Memory   | Accuracy  | Speed  | Used In       |
	| ------------- | -------- | --------- | ------ | ------------- |
	| LRU           | High     | Medium    | Medium | Basic systems |
	| Segmented LRU | Medium   | High      | Medium | DB/cache      |
	| Clock         | Very Low | Medium    | High   | OS/Redis      |
	| TinyLFU       | Medium   | Very High | High   | Caffeine      |

*/
}