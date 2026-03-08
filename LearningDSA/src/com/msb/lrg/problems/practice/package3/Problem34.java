package com.msb.lrg.problems.practice.package3;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem34 {

	public static void main(String[] args) {
//		int[] arr = {10, 20, 30, 40, 50, 25};
		int[] arr = {-8, 8, 0, -9, 4, -2, 5, -1};
		Node root = null;
		for(int i = 0; i < arr.length; i++) {
			root = insertData(root, arr[i]);
		}
		printTree(root);
		inOrder(root);
	}
	
	static Node insertData(Node node, int data) {
		
		if(node == null)
			return new Node(data);
		
		Node curr = node;
		if(curr.data > data) {
			node.left = insertData(node.left, data);
		} else if(curr.data < data) {
			node.right = insertData(node.right, data);
		} else {
			return node;
		}
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		
		int balance = height(node.left) - height(node.right);
		
		if(balance > 1 && node.left.data > data) {
			return rightRotate(node);
		}

		if(balance > 1 && node.left.data < data) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if(balance < -1 && node.right.data > data) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		if(balance < -1 && node.right.data < data) {
			return leftRotate(node);
		}
		
		return node;
	}
	
	static Node leftRotate(Node x) {
		Node y = x.right;
		Node temp = y.left;
		
		x.right = temp;
		y.left = x;
		
		x.height = 1 + Math.max(height(x.left), height(x.right)); 
		y.height = 1 + Math.max(height(y.left), height(y.right));
		
		return y;
	}
	
	static Node rightRotate(Node x) {
		Node y = x.left;
		Node temp = y.right;
		
		x.left = temp;
		y.right = x;
		
		x.height = 1 + Math.max(height(x.left), height(x.right)); 
		y.height = 1 + Math.max(height(y.left), height(y.right));
		
		return y;
	}
	
	static int height(Node node) {
		if(node == null)
			return -1;
		
		return node.height;
	}
	
	static class Node {
		int data, height;
		Node left, right;
		
		Node(){}
		
		Node(int val){
			this.data = val;
		}
		
		Node(int val, Node left, Node right){
			this.data = val;
			this.left = left;
			this.right = right;
		}
	}

	static void printTree(Node root) {
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				Node node = queue.poll();
				System.out.print(node.data + "\t");
				if(node.left != null) {
					queue.offer(node.left);
					System.out.print("left\t");
				}
				if(node.right != null) {
					queue.offer(node.right);
					System.out.print("right\t");
				}
				size--;
			}
			System.out.println("");	
		}
	}

	static void inOrder(Node node) {
		if(node != null) {
			inOrder(node.left);
			System.out.print(node.data + "  ");
			inOrder(node.right);
		}
	}
	
}
