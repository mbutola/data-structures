package com.msb.lrg.ds.greedy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanCoding {

	public static void main(String[] args) {

		char[] charArray = {'a', 'd', 'e', 'f'};
		int[] freqArray = {30, 40, 80, 60};
		
		HuffmanCoding.printHuffmanCode(charArray, freqArray);
		
	}

	static void printHuffmanCode(char[] charArray, int[] freqArray) {
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>((i1,i2) -> i1.freq - i2.freq);
		
		for (int i = 0; i < charArray.length; i++) {
			queue.add(new Node(charArray[i], freqArray[i], null, null));
		}
		
		Node left = null;
		Node right = null;
				
		while(queue.size() > 1) {
			left = queue.poll();
			right = queue.poll();
			queue.add(new Node('$', left.freq + right.freq, left, right));
		}
		
		printRec(queue.peek(), "");
	}
	
	static void printRec(Node huffmanTree, String str) {
		
		Node current = huffmanTree;
		
		if(current.ch != '$') {
			System.out.println(current.ch + " : " + str);
			return;
		}
		
		printRec(current.left, str.concat("0"));
		printRec(current.right, str.concat("1"));
		
	}
	
	
	static class Node {
		char ch;
		int freq;
		Node left;
		Node right;
		public Node(char ch, int freq, Node left, Node right) {
			super();
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
	}

}
