package com.msb.lrg.problems.design;

import java.util.HashMap;
import java.util.Map;

public class O1LRUCacheDesign {
	
	static class LRUCache {

		private Map<Integer, Node> map;
		private int capacity;
		private Node head, tail;
	
		public LRUCache(int capacity) {
			this.capacity = capacity;
			map = new HashMap<>();
	
			// Dummy head & tail
			head = new Node(0, 0);
			tail = new Node(0, 0);
	
			head.next = tail;
			tail.prev = head;
		}
	
		public int get(int key) {
			if (!map.containsKey(key)) {
				System.out.println("GET " + key + " -> -1 (not found)");
				return -1;
			}
	
			Node node = map.get(key);
			remove(node);
			insert(node);
	
			System.out.println("GET " + key + " -> " + node.value);
			return node.value;
		}
	
		public void put(int key, int value) {
			if (map.containsKey(key)) {
				remove(map.get(key));
			}
	
			if (map.size() == capacity) {
				Node lru = tail.prev;
				remove(lru);
				map.remove(lru.key);
				System.out.println("Evicting key: " + lru.key);
			}
	
			Node newNode = new Node(key, value);
			insert(newNode);
			map.put(key, newNode);
	
			System.out.println("PUT " + key + "=" + value);
		}
	
		private void remove(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
	
		private void insert(Node node) {
			node.next = head.next;
			node.prev = head;
	
			head.next.prev = node;
			head.next = node;
		}
	
		// Debug method to print cache state
		public void printCache() {
			Node curr = head.next;
			System.out.print("Cache State: ");
			while (curr != tail) {
				System.out.print("[" + curr.key + ":" + curr.value + "] ");
				curr = curr.next;
			}
			System.out.println();
		}
	}

	static class Node {
		int key, value;
		Node prev, next;

		Node(int k, int v) {
			key = k;
			value = v;
		}
	}

	public static void main(String[] args) {

		LRUCache cache = new LRUCache(3);

		cache.put(1, 10);
		cache.printCache();

		cache.put(2, 20);
		cache.printCache();

		cache.put(3, 30);
		cache.printCache();

		System.out.println(cache.get(1)); // makes 1 most recent
		cache.printCache();

		cache.put(4, 40); // should evict key 2
		cache.printCache();

		System.out.println(cache.get(2)); // should return -1
		cache.printCache();

		cache.put(5, 50); // should evict key 3
		cache.printCache();

		System.out.println(cache.get(3)); // -1
		System.out.println(cache.get(4)); // 40
		System.out.println(cache.get(5)); // 50
		cache.printCache();
	}
}