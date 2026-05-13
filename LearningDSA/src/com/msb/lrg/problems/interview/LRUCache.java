package com.msb.lrg.problems.interview;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {
	
	Map<Integer, Node> map;
	int capacity;
	Node head, tail;

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1,10);
		cache.put(2,20);
		System.out.println(cache.get(1)); // 10
		cache.put(3,30);
		System.out.println(cache.get(2)); // -1	
	}
	
	LRUCache(int capacity) {
		map = new HashMap<>(capacity);
		this.capacity = capacity;
		head = new Node(0,0);
		tail = new Node(0,0);
		head.next = tail;
		tail.prev = head;
	}
	
	public void put(int key, int value) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			remove(map.get(key));
			insert(node);
		} else {
			if(map.size() >= capacity) {
				Node last = tail.prev; 
				remove(last);
				map.remove(last.key);
			}
			Node node = new Node(key, value);
			insert(node);
			map.put(key, node);
		}
	}

	public int get(int key) {
		if(!map.containsKey(key)) return -1;
		
		Node node = map.get(key);
		remove(node);
		insert(node);
		return node.val;
	}
	
	public void insert(Node node) {
		head.next.prev = node;
		node.next = head.next;
		head.next = node;
		node.prev = head;
	}
	
	public void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	static class Node {
		Node prev, next;
		int key, val;
		Node (int key, int val){
			this.key = key;
			this.val = val;
		}
	}
}
