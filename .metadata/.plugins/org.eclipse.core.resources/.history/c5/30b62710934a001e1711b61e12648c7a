package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class TreeHeight {

	public static void main(String[] args) {
		Node root =  TreeHeight.createTree();
		Utility.printBinaryTree(root);
        System.out.println("O/P : " + TreeHeight.height(root));
	}
	
	public static int height(Node root) {
		if(root == null)
			return 0;
		
		return 1 + Math.max(height(root.left), height(root.right));
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(8);
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
