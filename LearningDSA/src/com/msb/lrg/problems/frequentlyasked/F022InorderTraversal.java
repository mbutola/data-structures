package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*

🌳 Inorder Traversal (Left → Root → Right)
	🧠 One-line idea
		👉 Go left → visit node → go right
	🧩 Example
	      1
	       \
	        2
	       /
	      3
	👉 Inorder:
		[1, 3, 2]
	⚙️ Approach 1: Recursive (simplest)
		🔄 Logic
			inorder(node):
			    inorder(left)
			    visit node
			    inorder(right)

 */
public class F022InorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        System.out.println(inorderTraversal(root)); // [1,3,2]
        System.out.println(inorderTraversal2(root)); // [1,3,2]
    }
    
    static List<Integer> inorderTraversal(TreeNode root){
    	List<Integer> res = new ArrayList<>();
    	inorder(root, res);
    	return res;
    }    
    
    static void inorder(TreeNode root, List<Integer> res){
    	if(root == null)
    		return;
    	
   		inorder(root.left, res);
    	res.add(root.val);
   		inorder(root.right, res);
    }
    
    static class TreeNode {
    	int val;
    	TreeNode left, right;
    	
    	TreeNode(int val){
    		this.val = val;
    	}
    }
	
    /*
	⚙️ Approach 2: Iterative (using stack)
	🔄 Idea
		👉 Simulate recursion using stack
     */
    static List<Integer> inorderTraversal2(TreeNode root){
    	List<Integer> res = new ArrayList<>();
    	TreeNode curr = root;
    	Stack<TreeNode> stack = new Stack<>();
    	
    	while(curr != null || !stack.isEmpty()) {
    		while(curr != null) {
    			stack.push(curr);
    			curr = curr.left;
    		}
    		
    		curr = stack.pop();
    		res.add(curr.val);
    		
    		curr = curr.right;
    	}
    	
    	return res;
    }
}
