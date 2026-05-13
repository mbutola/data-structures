package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class F024SameTree {

	public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        System.out.println(isSameTree(p, q)); // true
        System.out.println(isSameTreeIter(p, q)); // true
        System.out.println(isSameTreeBFS(p, q)); // true
	}

	/*
		DFS Iterative
			Uses:
				Stack
			Behavior:
				LIFO (Last In First Out)
			Traversal style:
				Go deep first
		BFS
			Uses:
				Queue
			Behavior:
				FIFO (First In First Out)
			Traversal style:
				Level by level
	 */
	static boolean isSameTreeBFS(TreeNode p, TreeNode q){
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(p,q));
		
		while(!queue.isEmpty()) {
		
			Pair curr = queue.poll();
			
			TreeNode first = curr.first;
			TreeNode second = curr.second;
			
			if(first == null &&  second == null)
				continue;
				
			if(first == null || second == null)
				return false;
			
			if(first.val != second.val)
				return false;
				
			queue.add(new Pair(first.left, second.left));
			queue.add(new Pair(first.right, second.right));
		}
		
		return true;
	}

	static boolean isSameTreeIter(TreeNode p, TreeNode q){
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(p,q));
		
		while(!stack.isEmpty()) {
		
			Pair curr = stack.pop();
			
			TreeNode first = curr.first;
			TreeNode second = curr.second;
			
			if(first == null &&  second == null)
				continue;
				
			if(first == null || second == null)
				return false;
			
			if(first.val != second.val)
				return false;
				
			stack.add(new Pair(first.left, second.left));
			stack.add(new Pair(first.right, second.right));
		}
		
		return true;
	}
	
	static boolean isSame(Pair pair) {
		if(pair.first == null &&  pair.second == null)
			return true;
			
		if(pair.first == null || pair.second == null)
			return false;
		
		if(pair.first.val != pair.second.val)
			return false;
		
		return true;
	}
	
	static class Pair {
		TreeNode first;
		TreeNode second;
		
		Pair(TreeNode first, TreeNode second){
			this.first = first;
			this.second = second;
		}
	}
	
	static boolean isSameTree(TreeNode p, TreeNode q){
		
		if(p == null && q == null)
			return true;
			
		if(p == null || q == null)
			return false;
		
		if(p.val != q.val)
			return false;
		
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int val){
			this.val = val;
		}
	}

}
