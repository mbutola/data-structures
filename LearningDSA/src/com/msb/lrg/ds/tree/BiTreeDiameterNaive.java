package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeDiameterNaive {

	public static void main(String[] args) {
		Node root =  BiTreeDiameterNaive.createTree();
		Utility.printBinaryTree(root);
		
        System.out.println("O/P : " + BiTreeDiameterNaive.getDiameter(root));
	}
	
	public static int getDiameter(Node root) {
		if(root == null)
			return 0;
		
		int lh = BiTreeDiameterNaive.getHeight(root.left);
		int rh = BiTreeDiameterNaive.getHeight(root.right);

		return Math.max(1+lh+rh, 
				Math.max(getDiameter(root.left),
						getDiameter(root.right)));
	}
	
	public static int getHeight(Node root) {
		if(root == null)
			return 0;
		
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		
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
