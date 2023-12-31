package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeLftViewRecursive {

	public static int level = 0;
	
	public static void main(String[] args) {
		Node root =  BiTreeLftViewRecursive.createTree();
		Utility.printBinaryTree(root);
		
        System.out.print("O/P : ");
        BiTreeLftViewRecursive.printLeft(root, 1);
	}
	
	public static void printLeft(Node root, int height) {
		
		if(root == null)
			return;
		
		if(height > level) {
			System.out.printf("%4s",root.data);
			level++;
		}
		printLeft(root.left, height+1);
		printLeft(root.right, height+1);
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
