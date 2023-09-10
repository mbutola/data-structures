package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeCompCntNaive {

	public static void main(String[] args) {
		Node root = BiTreeCompCntNaive.createTree();
		Utility.printBinaryTree(root);

		System.out.println("O/P : " + BiTreeCompCntNaive.count(root));
	}

	public static int count(Node root) {
		if(root == null)
			return 0;
		
		return 1 + count(root.left) + count(root.right);
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

		return root;
	}
}
