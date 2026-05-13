package interview;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	
	Map<Integer, Node> map = new HashMap<>();
	int capacity;
	Node head, tail;
	
	
	LRUCache(int capacity){
		/*
		 * Set capacity
		 * Create head tail with id value as 0
		 * set head tail pointers
		 */
		
	}
	
	public int get() {
		/*
		 * If deosn't exists return -1
		 * otherwise remove and add Node to queue to move front
		 */
		return 0;
	}
	
	public void put(int key, int value) {
		/*
		 * if Node exists
		 * 	move it to head
		 * else if hashmap size is equal to capacity 
		 * 	remove from tail and hashmap
		 * 	add element to hashmap and queue head
		 * Else
		 * 	add elememt to queue and hashmap
		 */
	}
	
	private void insert(Node node) {
		//insert the node at the head heead.next
	}
	
	private void remove(Node node) {
		//remove the last element tail.prev
	}

	public static void main(String[] args) {
	}
	
	static class Node {
		int id;
		int value;
		Node next, prev;		
	}

}
