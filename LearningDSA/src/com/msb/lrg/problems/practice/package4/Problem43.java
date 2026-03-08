package com.msb.lrg.problems.practice.package4;

/*

Reverse Linked List :: LeetCode (206, Easy)
	Given the head of a singly linked list, reverse the list, and return the reversed list.
	Example 1:
		Input: head = [1,2,3,4,5]
		Output: [5,4,3,2,1]
	Example 2:
		Input: head = [1,2]
		Output: [2,1]
	Example 3:
		Input: head = []
		Output: []
	Constraints:
		The number of nodes in the list is the range [0, 5000].
		-5000 <= Node.val <= 5000

 */
public class Problem43 {

	public static void main(String[] args) {
		ListNode _5 = new ListNode(5);
		ListNode _4 = new ListNode(4, _5);
		ListNode _3 = new ListNode(3, _4);
		ListNode _2 = new ListNode(2, _3);
		ListNode head = new ListNode(1, _2);
//		ListNode rev = reverseList(head);
//		while(rev.next != null) {
//			System.out.print(rev.val + " -> ");
//			rev = rev.next;
//		}
//		System.out.print(rev.val);
		ListNode rev = reverseListRecursive(head);
		while(rev.next != null) {
			System.out.print(rev.val + " -> ");
			rev = rev.next;
		}
		System.out.print(rev.val);
	}

	public static ListNode reverseListRecursive(ListNode head) {
		
		if(head == null || head.next == null)
			return head; 
		
		ListNode newHead = reverseListRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	public static ListNode reverseList(ListNode head) {
		ListNode prev = null;
		ListNode curr = head;
		
		while(curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
		
	static class ListNode {
	     int val;
	     ListNode next;
	     ListNode() {}
	     ListNode(int val) { this.val = val; }
	     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
