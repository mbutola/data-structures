package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BTreeSize {

	public static void main(String[] args) {
		Node root =  BTreeSize.createTree();
		Utility.printBinaryTree(root);
		
        System.out.print("O/P : " + BTreeSize.getSize(root));
	}
	
	public static int getSize(Node root) {
		if(root == null)
			return 0;
		
		return 1 + getSize(root.left) + getSize(root.right);
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
		root.right.right = temp;

		return root;
	}
	
}
