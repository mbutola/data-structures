package com.msb.lrg.problems.practice.package4;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
LRU Cache :: LeetCode (146, Medium)
	Design a data structure that follows the constraints of a Least Recently Used 
	(LRU) cache.
	Implement the LRUCache class:
		LRUCache(int capacity) Initialize the LRU cache with positive size 
		capacity.
		int get(int key) Return the value of the key if the key exists, 
		otherwise return -1.
		void put(int key, int value) Update the value of the key if the key 
		exists. Otherwise, add the key-value pair to the cache. If the number of 
		keys exceeds the capacity from this operation, evict the least recently 
		used key.
	The functions get and put must each run in O(1) average time complexity.
	Example 1:
		Input
			["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
			[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
		Output
			[null, null, null, 1, null, -1, null, -1, 3, 4]
		Explanation
			LRUCache lRUCache = new LRUCache(2);
			lRUCache.put(1, 1); // cache is {1=1}
			lRUCache.put(2, 2); // cache is {1=1, 2=2}
			lRUCache.get(1);    // return 1
			lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
			lRUCache.get(2);    // returns -1 (not found)
			lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
			lRUCache.get(1);    // return -1 (not found)
			lRUCache.get(3);    // return 3
			lRUCache.get(4);    // return 4
	Constraints:
		1 <= capacity <= 3000
		0 <= key <= 104
		0 <= value <= 105
		At most 2 * 105 calls will be made to get and put.

 */
public class Problem41 {

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		System.out.print("null, ");
		cache.put(1,1);
		System.out.print("null, ");
		cache.put(2,2);
		System.out.print("null, ");
		System.out.print(cache.get(1) + ", ");
		cache.put(3,3);
		System.out.print("null, ");
		System.out.print(cache.get(2) + ", ");
		cache.put(4,4);
		System.out.print("null, ");
		System.out.print(cache.get(1) + ", ");
		System.out.print(cache.get(3) + ", ");
		System.out.print(cache.get(4) + ", ");
	}

	static class LRUCache {

		int capacity;
		Map<Integer, Node> map;
		DLL order;

	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	    	map = new HashMap<>(capacity);
	    	order = new DLL();
	    }
	    
	    public int get(int key) {

	    	if(!map.containsKey(key)) {
	    		return -1;
	    	}
	    	
	    	Node node = map.get(key);
	    	order.remove(node);
	    	order.addToHead(node);
	    	
	    	return node.value;

	    }
	    
	    public void put(int key, int value) {
	    	
	    	if(map.containsKey(key)) {
		    	order.remove(map.get(key));
	    	} else {
		    	if(map.size() >= capacity) {
		    		Node toRemove = order.removeLast();
		    		map.remove(toRemove.key);
		    		order.remove(toRemove);
		    	}
	    	}
	    	Node newNode = new Node(key, value); 
    		map.put(key, newNode);
	    	order.addToHead(newNode);
	    }

	}
	
	static class DLL {
		int size;
		int key, value;
		Node head, tail;
		
		DLL(){
			size = 0;
	    	head = new Node(-1,-1);
	    	tail = new Node(-1,-1);
	    	head.next = tail;
	    	tail.prev = head;
		}
		
		public void addToHead(Node node) {
			node.next = head.next;
			node.prev = head.next.prev;
			head.next.prev = node;
			head.next = node;
			size++;
		}
		
		public Node removeLast() {
			if(tail.prev == head)
				return null;
			Node toRemove = tail.prev;
			return remove(toRemove);
		}
		
		public Node remove(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
			return node;
		}
		
		public int size() {
			return size;
		}
		
	}

	static class Node{
		int key, value;
		Node next, prev;
		Node(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
	
//	static class LRUCache {
//		
//		int capacity;
//		Map<Integer, Integer> map;
//		Set<Integer> used;
//		
//	    public LRUCache(int capacity) {
//	        this.capacity = capacity;
//	    	map = new HashMap<>(capacity);
//	    	used = new LinkedHashSet<>();
//	    }
//	    
//	    public int get(int key) {
//
//	    	if(!map.containsKey(key)) {
//	    		return -1;
//	    	}
//	    	
//	    	used.remove(key);
//	    	used.add(key);
//	    	
//	    	return map.get(key);
//
//	    }
//	    
//	    public void put(int key, int value) {
//	    	
//	    	if(map.containsKey(key)) {
//		    	used.remove(key);
//	    	} else {
//		    	if(map.size() >= capacity) {
//		    		int toRemove = used.iterator().next();
//		    		map.remove(toRemove);
//		    		used.remove(toRemove);
//		    	}
//	    	}
//    		map.put(key, value);
//	    	used.add(key);
//	    }
//	}

}
