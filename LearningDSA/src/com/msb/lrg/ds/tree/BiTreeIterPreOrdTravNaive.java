package com.msb.lrg.ds.tree;

import java.util.Stack;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeIterPreOrdTravNaive {

	public static void main(String[] args) {
		Node root = BiTreeIterPreOrdTravNaive.createTree();
		Utility.printBinaryTree(root);

		BiTreeIterPreOrdTravNaive.print(root);	
	}
	
	public static void print(Node root) {
		
		if(root == null)
			return;
		
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node curr = stack.pop();
			System.out.printf("%4s",curr.data);
			if(curr.right != null)
				stack.push(curr.right);
			if(curr.left != null)
				stack.push(curr.left);
		}
		
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;

		temp = new Node(20);
		root.left = temp;
		temp = new Node(30);
		root.right = temp;
		
		temp = new Node(40);
		root.left.left = temp;
		temp = new Node(50);
		root.left.right = temp;
		temp = new Node(60);
		root.right.left = temp;
		temp = new Node(70);
		root.right.right = temp;

		temp = new Node(80);
		root.left.left.left = temp;
		temp = new Node(90);
		root.left.left.right = temp;

		return root;
	}
}
