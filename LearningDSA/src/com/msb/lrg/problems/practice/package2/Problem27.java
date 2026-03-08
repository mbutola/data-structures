package com.msb.lrg.problems.practice.package2;

import java.awt.geom.CubicCurve2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*

LFU Cache :: LeetCode (460, Hard)
	Design and implement a data structure for a Least Frequently Used (LFU) cache.
	Implement the LFUCache class:
		LFUCache(int capacity) Initializes the object with the capacity of the 
		data structure.
		int get(int key) Gets the value of the key if the key exists in the 
		cache. Otherwise, returns -1.
		void put(int key, int value) Update the value of the key if present, 
		or inserts the key if not already present. When the cache reaches its 
		capacity, it should invalidate and remove the least frequently used key 
		before inserting a new item. For this problem, when there is a tie (i.e., two 
		or more keys with the same frequency), the least recently used key 
		would be invalidated.
		To determine the least frequently used key, a use counter is maintained for 
		each key in the cache. The key with the smallest use counter is the least 
		frequently used key.
		When a key is first inserted into the cache, its use counter is set to 1 (due to 
		the put operation). The use counter for a key in the cache is incremented 
		either a get or put operation is called on it.
		The functions get and put must each run in O(1) average time complexity.
	Example 1:
		Input
			["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
			[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
		Output
			[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
		Explanation
			// cnt(x) = the use counter for key x
			// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
			LFUCache lfu = new LFUCache(2);
				lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
				lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
				lfu.get(1);      // return 1
				                 // cache=[1,2], cnt(2)=1, cnt(1)=2
				lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
				                 // cache=[3,1], cnt(3)=1, cnt(1)=2
				lfu.get(2);      // return -1 (not found)
				lfu.get(3);      // return 3
				                 // cache=[3,1], cnt(3)=2, cnt(1)=2
				lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
				                 // cache=[4,3], cnt(4)=1, cnt(3)=2
				lfu.get(1);      // return -1 (not found)
				lfu.get(3);      // return 3
				                 // cache=[3,4], cnt(4)=1, cnt(3)=3
				lfu.get(4);      // return 4
				                 // cache=[4,3], cnt(4)=2, cnt(3)=3
		 
	Constraints:
		1 <= capacity <= 104
		0 <= key <= 105
		0 <= value <= 109
		At most 2 * 105 calls will be made to get and put.

 */
public class Problem27 {

	public static void main(String[] args) {
		LFUCache cache = new LFUCache(2);

		System.out.print("null,");
		cache.put(1,1); System.out.print("null,");
		cache.put(2,2); System.out.print("null,");
		System.out.print("" + cache.get(1) + ",");
		cache.put(3,3); System.out.print("null,");
		System.out.print("" + cache.get(2) + ",");
		System.out.print("" + cache.get(3) + ",");
		cache.put(4,4); System.out.print("null,");
		System.out.print("" + cache.get(1) + ",");
		System.out.print("" + cache.get(3) + ",");
		System.out.print("" + cache.get(4) + ",");
	}

	static class LFUCache {
		
		int capacity;
		int minFreq;
		Map<Integer, Node> keyMap;
		Map<Integer, DLL> freqMap;

	    public LFUCache(int capacity) {
	    	this.capacity = capacity;
	    	keyMap = new HashMap<>();
	    	freqMap = new HashMap<>();
	    }
	    
	    public int get(int key) {
	    	if(!keyMap.containsKey(key)) return -1;
	    	Node node = keyMap.get(key);
	    	updateFreq(node);
	    	return node.value;
	    }
	
	    public void put(int key, int value) {
	    	if(capacity == 0) return ;
	    	
	    	if(keyMap.containsKey(key)) {
	    		Node node = keyMap.get(key);
	    		node.value = value;
	    		updateFreq(node);
	    		return;
	    	}
	    	
	    	if(keyMap.size() == capacity) {
	    		DLL list = freqMap.get(minFreq);
	    		Node rem = list.removeLast();
	    		keyMap.remove(rem.key);
	    	}
	    	
	    	Node newNode = new Node(key, value);
	    	keyMap.put(key, newNode);
	    	freqMap.computeIfAbsent(newNode.freq, k -> new DLL()).add(newNode);
	    	minFreq = 1;
	    }
	    
	    void updateFreq(Node node) {
	    	int freq = node.freq;
	    	DLL list = freqMap.get(freq);
	    	list.remove(node);
	    	
	    	if(list.size == 0 && freq == minFreq) {
	    		minFreq++;
	    	}

	    	node.freq++;
	    	freqMap.computeIfAbsent(node.freq, k -> new DLL()).add(node);
	    }

	}
	
	static class DLL {
		
		Node head;
		Node tail;
		int size;
		
		DLL(){
			head = new Node(0,0);
			tail = new Node(0,0);
			head.next = tail;
			tail.prev = head;
		}
		
		void add(Node node) {
			node.next = head.next;
			head.next.prev = node;;
			head.next = node;
			node.prev = head;
			size++;
		}
		
		void remove(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
		}
		
		Node removeLast() {
			if(size > 0) {
				Node last = tail.prev;
				remove(last);
				return last;
			}
			return null;
		}
		
	}

	static class Node {
		
		int key;
		int value;
		int freq;
		
		Node prev;
		Node next;
		
		Node(int k, int v){
			this.key = k;
			this.value = v;
			this.freq = 1;
		}
		
	}
}	
	
//	static class LFUCache {
//		
//		int capacity;
//		Map<Integer, Node> map;
//		TreeSet<Node> frequency = new TreeSet<>((a,b) -> 
//											{
//												if(a.count != b.count)
//													return Integer.compare(a.count,b.count);
//												if(a.lastUsed != b.lastUsed)
//													return Long.compare(a.lastUsed,b.lastUsed);
//												return Integer.compare(a.key,b.key);
//											});
//
//	    public LFUCache(int capacity) {
//	    	this.capacity = capacity;
//	        map = new HashMap<>(capacity);
//	    }
//	    
//	    public int get(int key) {
//	    	if(map.containsKey(key)) {
//	    		Node node = map.get(key);
//	    		frequency.remove(node);
//	    		node.count++;
//	    		node.lastUsed = System.currentTimeMillis();
//	    		frequency.add(node);
//	    		return node.value;
//	    	}
//	        return -1;
//	    }
//	    
//	    public void put(int key, int value) {
//
//	    	if(map.containsKey(key)) {
//	    		Node node = map.get(key);
//	    		frequency.remove(node);
//	    		node.value = value;
//	    		node.count++;
//	    		node.lastUsed = System.currentTimeMillis();
//	    		frequency.add(node);
//	    		return;
//	    	}
//
//	    	if(map.size() >= capacity) {
//	    		Node remNode = frequency.first();
//	    		frequency.remove(remNode);
//	    		map.remove(remNode.key);
//	    	}
//		    
//	    	Node newNode = new Node(key, value);
//		    map.put(key, newNode);
//		    frequency.add(newNode);
//	    }
//	    
//	}
//	
//	static class Node {
//		int key;
//		int value;
//		int count = 0;
//		long lastUsed;
//		
//		Node(int key, int value){
//			this.key = key;
//			this.value = value;
//			count++;
//			this.lastUsed = System.currentTimeMillis();
//		}
//		
//		public String toString() {
//			return "id:" + key + ", value:" + value;
//		}
//	}
//
//}
