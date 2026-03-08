package com.msb.lrg.problems;

import java.util.ArrayDeque;
import java.util.Queue;

public class Utility {

	public static void printTree(TreeNode root) {
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				TreeNode node = queue.poll();
				System.out.print(node.val + "\t");
				if(node.left != null) {
					queue.offer(node.left);
					System.out.print("left\t");
				}
				if(node.right != null) {
					queue.offer(node.right);
					System.out.print("right\t");
				}
				size--;
			}
			System.out.println("");	
		}
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int val){
			this.val = val;
		}
	}
}
