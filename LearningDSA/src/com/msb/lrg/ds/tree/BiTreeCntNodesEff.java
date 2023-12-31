package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeCntNodesEff {

	public static void main(String[] args) {
		Node root = BiTreeCntNodesEff.createTree();
		Utility.printBinaryTree(root);

		System.out.println("O/P : " + BiTreeCntNodesEff.count(root));
	}

	public static int count(Node root) {
		
		int lh = 0;
		Node curr = root;
		while(curr != null) {
			lh++;
			curr = curr.left;
		}
		
		int rh = 0;
		curr = root;
		while(curr != null) {
			rh++;
			curr = curr.right;
		}
		
		if(lh == rh) {
			return (int)Math.pow(2, lh) - 1;
		}else {
			return 1 + count(root.left) + count(root.right);
		}
		
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
		root.right.left = temp;
		temp = new Node(70);
		root.right.right = temp;

		temp = new Node(80);
		root.left.left.left = temp;
		temp = new Node(90);
		root.left.left.right = temp;
		temp = new Node(100);
		root.left.right.left = temp;
		temp = new Node(110);
		root.left.right.right = temp;
		temp = new Node(120);
		root.right.left.left = temp;
		
		return root;
	}
}
