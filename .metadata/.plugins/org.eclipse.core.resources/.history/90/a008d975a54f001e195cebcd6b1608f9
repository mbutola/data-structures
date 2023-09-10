package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeLowCommAncesEff {

	public static void main(String[] args) {
		Node root =  BiTreeLowCommAncesEff.createTree();
		Utility.printBinaryTree(root);
		int d1 = 40;
		int d2 = 50;
        System.out.println("O/P : " + (BiTreeLowCommAncesEff.getLCA(root, d1, d2)).data);
	}
	
	public static Node getLCA(Node root, int d1, int d2) {
		
		if(root == null)
			return null;
		
		System.out.printf("%4s", root.data);
		
		if((root.data == d1) || (root.data == d2))
			return root;
			
		Node lca1 = getLCA(root.left, d1, d2);
		Node lca2 = getLCA(root.right, d1, d2);
		
		if(lca1 != null && lca2 != null)
			return root;
		
		if(lca1 != null)
			return lca1;
		else
			return lca2;
	}
	
	public static boolean search(Node root, int d) {

		if(root == null)
			return false;
		
		return (root.data == d) || search(root.left, d) || search(root.right, d);
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
