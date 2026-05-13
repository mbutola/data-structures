package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Queue;

import com.msb.lrg.problems.frequentlyasked.F022InorderTraversal.TreeNode;

/*

🌳 Max Depth of Binary Tree (height)
	🧠 One-line idea
		👉 Depth = 1 + max(depth of left, depth of right)
	🧩 Problem
		👉 Find the maximum depth (height) of a binary tree
	🔍 Example
		      3
		     / \
		    9  20
		       / \
		      15  7
	👉 Output:
		3
	⚙️ Approach 1: DFS (recursive)
		🔄 Logic
			If node is null → depth = 0  
			Else:
			    depth = 1 + max(left, right)

 */
public class F023MaxDepthTree {

	public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(maxDepth(root)); // 3
        System.out.println(maxDepthBFS(root)); // 3
	}
	
	static int maxDepthBFS(TreeNode root) {
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int depth = 0;
		
		while(!queue.isEmpty()) {
			depth++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();
				if(curr.left != null)
					queue.offer(curr.left);
				if(curr.right != null)
					queue.offer(curr.right);
			}
		}
		
		return depth;
	}
	
	static int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		
		return 1 + Math.max(leftDepth, rightDepth);
	}

    static class TreeNode {
    	int val;
    	TreeNode left, right;
    	
    	TreeNode(int val){
    		this.val = val;
    	}
    }
}
