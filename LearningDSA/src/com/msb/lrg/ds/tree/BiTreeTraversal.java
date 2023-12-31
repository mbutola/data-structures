package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeTraversal {

	public static void main(String[] args) {
		Node root = BiTreeTraversal.createTree();
		Utility.printBinaryTree(root);
		System.out.printf("O/P In Order   : ");
		BiTreeTraversal.inOrder(root);
		System.out.printf("\nO/P Pre Order  : ");
		BiTreeTraversal.preOrder(root);
		System.out.printf("\nO/P Post Order : ");
		BiTreeTraversal.postOrder(root);
	}
	
	public static void inOrder(Node root) {
		if(root != null) {
			inOrder(root.left);
			System.out.printf("%2s", root.data);
			inOrder(root.right);
		}
	}

	public static void preOrder(Node root) {
		if(root != null) {
			System.out.printf("%4s", root.data);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public static void postOrder(Node root) {
		if(root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.printf("%4s", root.data);
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
		root.right.left = temp; 
		temp = new Node(50);
		root.right.right = temp; 

		return root;
	}
}

