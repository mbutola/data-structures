package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeMax {

	public static void main(String[] args) {
		Node root =  BiTreeMax.createTree();
		Utility.printBinaryTree(root);
		
        System.out.print("O/P : " + BiTreeMax.getMax(root));
	}
	
	public static int getMax(Node root) {
		
		if(root == null)
			return Integer.MIN_VALUE;
		
		return Math.max(root.data,
						Math.max(getMax(root.left), 
								getMax(root.right)));
		
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(80);
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
