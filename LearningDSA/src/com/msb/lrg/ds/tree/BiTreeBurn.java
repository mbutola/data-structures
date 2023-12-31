package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeBurn {
	
	public static int res = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Node root = BiTreeBurn.createTree();
		Utility.printBinaryTree(root);

		int leaf = 50;
		BiTreeBurn.burn(root, leaf, new Distance(-1));
		System.out.println("O/P : " + res);
	}

	public static int burn(Node root, int leaf, Distance dist ) {
		if(root == null)
			return 0;
		
		if(root.data == leaf) {
			dist.val = 0;
			return 1;
		}
		
		Distance ldist = new Distance(-1);
		Distance rdist = new Distance(-1);
		
		int lh = burn(root.left, leaf, ldist);
		int rh = burn(root.right, leaf, rdist);		
			
		if(ldist.val != -1){
			dist.val = ldist.val + 1;
			res = Math.max(res, rh + dist.val);
		}else if(rdist.val != -1){
			dist.val = rdist.val + 1;
			res = Math.max(res, lh + dist.val);
		}

		return 1 + Math.max(lh, rh);
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
		root.left.left.left = temp;
		temp = new Node(70);
		root.left.left.left.left = temp;

		return root;
	}
	
}

class Distance{
	int val;
	
	Distance(int val){
		this.val = val;
	}
	
}
