package com.msb.lrg.ds.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeDeserialize {

	public static int EMPTY = -1;
	public static int index = 0;

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(10,20,-1,-1,-1);
		Node root = BiTreeDeserialize.deserialize(list);
		
		Utility.printBinaryTree(root);
	}

	public static Node deserialize(List<Integer> list) {
		
		if(index == list.size())
			return null;
	
		int val = list.get(index);
		index++;
		
		if(val == EMPTY)
			return null;
		
		Node root = new Node(val);
		root.left = deserialize(list);
		root.right = deserialize(list);
	
		return root;
	}
	
	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;

		temp = new Node(20);
		root.left = temp;
		
		return root;
	}
}
