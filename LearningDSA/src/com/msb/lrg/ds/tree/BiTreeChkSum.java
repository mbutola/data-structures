package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeChkSum {

	public static void main(String[] args) {
		Node root = BiTreeChkSum.createTree();
		Utility.printBinaryTree(root);

		System.out.print("O/P : " + BiTreeChkSum.chkSum(root));
	}

	public static boolean chkSum(Node root) {
		if (root == null)
			return true;
		
		if(root.left == null && root.right == null)
			return true;

		int sum = 0;
		if(root.left != null)
			sum+=root.left.data;
		if(root.right != null)
			sum+=root.right.data;

		return (sum == root.data) && 
					chkSum(root.left) &&
					chkSum(root.right);

	}
	
	public static Node createTree() {
		Node root = new Node(20);
		Node temp = null;
		
		temp = new Node(8);
		root.left = temp;
		temp = new Node(12);
		root.right = temp;

		temp = new Node(3);
		root.right.left = temp;
		temp = new Node(9);
		root.right.right = temp;

		return root;
	}
}
