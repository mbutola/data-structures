
package com.msb.lrg.problems.practice.package0;

import java.util.ArrayList;
import java.util.List;

/*

To do Inorder Traversal without using Stack or Recursion, the standard approach is:

Morris Inorder Traversal
	(Also called Threaded Binary Tree traversal)
	It runs in
		Time: O(n)
		Space: O(1) (no stack, no recursion)
	Idea Behind Morris Traversal
		For each node, you do:
			If node has no left child, print it and go right.
			If node has left child, find its inorder predecessor (rightmost node of left subtree).
				If predecessor's right pointer is null, make it point to current → create a temporary thread and go left.
				If predecessor’s right pointer is pointing to current, remove thread, print current, go right.
	Visualization
		Inorder = Left → Node → Right
		For every node with left subtree:
			You temporarily link the predecessor’s right to the current node.
			This allows you to return without stack/recursion.

 */
public class Problem7 {

	public static void main(String[] args) {
		Integer[] input = {1,2,3,4,5,null,8,null,null,6,7,null,null,9};
		TreeNode root = buildTreeRecursive(input, 0);
		List<Integer> inOrder = inorderTraversal(root);
		System.out.println("Result :: " + inOrder);
		inOrder = inorderTraversalMorris(root);
		System.out.println("Result :: " + inOrder);
	}
	
	public static List<Integer> inorderTraversalMorris(TreeNode root) {
		List<Integer> inOrder = new ArrayList<>();
		
		TreeNode current = root;
		while(current != null) {
			if(current.left == null) {
				inOrder.add(current.val);
				current = current.right;
			} else {
				TreeNode pre = current.left;
				while(pre.right != null && pre.right != current) {
					pre = pre.right;
				}
				
				if(pre.right == null) {
					pre.right = current;
					current = current.left;
				} else {
					pre.right = null;
					inOrder.add(current.val);
					current = current.right;
				}
			}
		}
		
		return inOrder;
    }	

	public static List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> inOrder = new ArrayList<>();
		inOrder(root, inOrder);
		return inOrder;
    }	
	
	public static void inOrder(TreeNode tree, List<Integer> inOrder) {
		if(tree == null)
			return;
		inOrder(tree.left, inOrder);
		inOrder.add(tree.val);
		inOrder(tree.right, inOrder);
	}
	
	public static TreeNode buildTreeRecursive(Integer[] nodes, int node) {
		if(node >= nodes.length)
			return null;
		
		if(nodes[node] != null) {
			TreeNode current = new TreeNode(nodes[node]);
			current.left = buildTreeRecursive(nodes, 2*node+1);
			current.right = buildTreeRecursive(nodes, 2*node+2);
			return current;
		}
		return null;
	}
	
	

//	public static TreeNode buildTreeQueue(int[] nodes) {
//		
//		for(int i=0; i<nodes.length; i++) {
//			
//		}
//		
//	}
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val=val; }
     TreeNode(Integer val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
}
