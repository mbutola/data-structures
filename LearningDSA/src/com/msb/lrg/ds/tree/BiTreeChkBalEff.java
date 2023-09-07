package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeChkBalEff {

	public static void main(String[] args) {
		Node root =  BiTreeChkBalEff.createTree();
		Utility.printBinaryTree(root);
		boolean res = (BiTreeChkBalEff.isBalance(root) != -1) ? true : false;
        System.out.print("O/P : " + res);
	}

	public static int isBalance(Node root) {
		
		if(root == null)
			return 0;
		
		int lh = isBalance(root.left);
		if(lh == -1)
			return -1;
		
		int rh = isBalance(root.right);
		if(rh == -1)
			return -1;
		
		if(Math.abs(lh-rh) > 1)
			return -1;
		else
			return 1 + Math.max(lh, rh);					
	}
	
	public static Node createTree() {
		Node root = new Node(8);
		Node temp = null;
		
		temp = new Node(12);
		root.left = temp;
		temp = new Node(15);
		root.right = temp;

		temp = new Node(13);
		root.left.left = temp;
		temp = new Node(14);
		root.left.right = temp;
		temp = new Node(16);
		root.right.right = temp;
		temp = new Node(17);
		root.right.right.right = temp;

		return root;

	}
}
