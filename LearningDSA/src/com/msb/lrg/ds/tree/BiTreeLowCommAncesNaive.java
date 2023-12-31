package com.msb.lrg.ds.tree;

import java.util.List;
import java.util.ArrayList;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeLowCommAncesNaive {

	public static void main(String[] args) {
		Node root =  BiTreeLowCommAncesNaive.createTree();
		Utility.printBinaryTree(root);
		int d1 = 20;
		int d2 = 50;
        System.out.println("O/P : " + BiTreeLowCommAncesNaive.getLCA(root, d1, d2));
	}
	
	public static int getLCA(Node root, int d1, int d2) {
		
		List<Integer> p1 = new ArrayList<Integer>(); 
		List<Integer> p2 = new ArrayList<Integer>(); 

		if(!buildPath(root, p1, d1) || !buildPath(root, p2, d2))
			return -1;

		System.out.println(p1.toString());
		System.out.println(p2.toString());
		for (int i = 0; i < p1.size()-1 && i < p2.size()-1; i++) {
			if(p1.get(i+1).intValue() != p2.get(i+1).intValue()) {
				return p1.get(i); 
			}
		}
		return -1;
	}
	
	public static boolean buildPath(Node root, List<Integer> p, int d) {

		if(root == null)
			return false;
		
		p.add(root.data);

		if(root.data == d)
			return true;
		
		if(buildPath(root.left, p, d) || buildPath(root.right, p, d)) {
			return true;
		}
		
		p.remove(p.size()-1);
		
		return false;
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
