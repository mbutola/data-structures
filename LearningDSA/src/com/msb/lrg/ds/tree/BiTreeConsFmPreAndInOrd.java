package com.msb.lrg.ds.tree;

import java.util.Arrays;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeConsFmPreAndInOrd {

	public static int i_pre = 0;
	
	public static void main(String[] args) {
		
		int[] in = new int[] {40,20,60,50,70,10,80,100,30};
		int[] pre = new int[] {10,20,40,50,60,70,30,80,100};
		
		System.out.println("Pre : " + Arrays.toString(pre));
		System.out.println("In  : " + Arrays.toString(in));

		Node root = BiTreeConsFmPreAndInOrd.constructBiTree(pre, in, 0, in.length-1);
		Utility.printBinaryTree(root);
	}

	
	public static Node constructBiTree(int[] pre, int[] in, int s, int f) {
		
		if(s > f) {
			return null;
		}
			
		int data = pre[i_pre++];

		int index = 0;
		for (int i = s; i <= f; i++) {
			if(in[i] == data) {
				index = i;
				break;
			}
		}
		
		Node node = new Node(data);
		node.left = constructBiTree(pre, in, s, index-1);
		node.right = constructBiTree(pre, in, index+1, f);
		
		return node;
	}
}
