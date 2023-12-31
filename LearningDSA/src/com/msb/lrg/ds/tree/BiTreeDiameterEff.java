package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeDiameterEff {

	public static int max_d = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Node root =  BiTreeDiameterEff.createTree();
		Utility.printBinaryTree(root);
		
		BiTreeDiameterEff.getHeight(root);
        System.out.println("O/P : " + max_d);
	}
	
	public static int getHeight(Node root) {
		
		if(root == null)
			return 0;
		
		int lh = BiTreeDiameterEff.getHeight(root.left);
		int rh = BiTreeDiameterEff.getHeight(root.right);

		max_d = Math.max(max_d, 1+lh+rh);
		
		return 1 + Math.max(lh, rh);
	}
	
	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(20);
		root.left = temp;
		temp = new Node(60);
		root.right = temp;

		temp = new Node(30);
		root.left.left = temp;
		temp = new Node(80);
		root.left.right = temp;

		temp = new Node(40);
		root.left.left.left = temp;
		temp = new Node(50);
		root.left.left.right = temp;
		temp = new Node(90);
		root.left.right.right = temp;

		temp = new Node(60);
		root.left.left.left.left = temp;
		temp = new Node(18);
		root.left.right.right.right = temp;

		return root;
	}
}
