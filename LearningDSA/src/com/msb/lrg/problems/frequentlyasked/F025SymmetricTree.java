package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Queue;

import com.msb.lrg.problems.frequentlyasked.F022InorderTraversal.TreeNode;

/*

Symmetric Tree
	Check whether a binary tree is a mirror of itself around its center.
	Also called:
		Mirror Tree
	Example
		Symmetric Tree
		        1
		      /   \
		     2     2
		    / \   / \
		   3   4 4   3
	Answer:
		true
	Not Symmetric
	        1
	      /   \
	     2     2
	      \     \
	       3     3
	Answer:
		false
	Main Idea
		For symmetry:
			left.left  == right.right
			left.right == right.left
		We compare opposite sides.
	Technique: Recursive Mirror Check
		Create helper function:
			isMirror(a, b)
		It checks whether two trees are mirror images.

 */
public class F025SymmetricTree {

	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.left.left.left = new TreeNode(5);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(5);

        System.out.println(isSymmetric(root)); // true
        System.out.println(isSymmetricBFS(root)); // true
	}

	static boolean isSymmetricBFS(TreeNode root){
		if(root == null)
			return true;
		
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(root.left, root.right));
		
		while(!queue.isEmpty()) {
			
			Pair curr = queue.poll();
			TreeNode left = curr.first;
			TreeNode right = curr.second;

			if(left == null && right == null)
				continue;
			
			if(left == null || right == null)
				return false;
			
			if(left.val != right.val)
				return false;
			
			queue.offer(new Pair(left.left, right.right));
			queue.offer(new Pair(left.right, right.left));

		}
		
		return true;
	}
	
	

	static boolean isSymmetric(TreeNode root){
		return isMirror(root.left, root.right);
	}
		
	static boolean isMirror(TreeNode left, TreeNode right){
		if(left == null && right == null)
			return true;
		
		if(left == null || right == null)
			return false;
		
		if(left.val != right.val)
			return false;
			
		return isMirror(left.left, right.right) 
				&& isMirror(left.right, right.left);
	}
	
	static class Pair {
		TreeNode first;
		TreeNode second;
		
		Pair(TreeNode first, TreeNode second){
			this.first = first;
			this.second = second;
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
