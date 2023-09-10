package com.msb.lrg.ds.tree;

import java.util.ArrayList;
import java.util.List;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeSerialize {

	public static int EMPTY = -1;

	public static void main(String[] args) {
		Node root = BiTreeSerialize.createTree();
		Utility.printBinaryTree(root);

		List<Integer> list = new ArrayList<Integer>();
		BiTreeSerialize.serialize(root, list);
		
		System.out.println("O/P : " + list.toString());
	}

	public static void serialize(Node root, List<Integer> list) {
	
		if(root == null) {
			list.add(EMPTY);
			return;
		}
		
		list.add(root.data);
		serialize(root.left, list);
		serialize(root.right, list);
	}
	
	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;

		temp = new Node(20);
		root.left = temp;
		
		return root;
	}
}
