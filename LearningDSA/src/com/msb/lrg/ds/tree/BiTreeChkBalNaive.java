package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeChkBalNaive {

	public static void main(String[] args) {
		Node root =  BiTreeChkBalNaive.createTree();
		Utility.printBinaryTree(root);
		
        System.out.print("O/P : " + BiTreeChkBalNaive.isBal(root));
	}

	public static boolean isBal(Node root) {
		
		if(root == null)
			return true;
		
		if(root.left == null && root.right == null)
			return true;
		
		int lh = height(root.left);
		int rh = height(root.left);
				
		return Math.abs(lh - rh) < 1 &&
				isBal(root.left) &&
				isBal(root.right);
	}
	
	public static int height(Node root) {
		
		if(root == null)
			return 0;
		
		return 1 + Math.max(height(root.left), height(root.right));
	}
		
	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(5);
		root.left = temp;
		temp = new Node(30);
		root.right = temp;

		temp = new Node(15);
		root.right.left = temp;
		temp = new Node(20);
		root.right.right = temp;

		return root;

	}
}
