package com.msb.lrg.problems.practice.package4;

/*

Reverse Linked List II :: LeetCode (92, Medium)
	Given the head of a singly linked list and two integers left and right where left <= right, 
	reverse the nodes of the list from position left to position right, and return the reversed list.
	Example 1:
		Input: head = [1,2,3,4,5], left = 2, right = 4
		Output: [1,4,3,2,5]
	Example 2:
		Input: head = [5], left = 1, right = 1
		Output: [5]
	Constraints:
		The number of nodes in the list is n.
		1 <= n <= 500
		-500 <= Node.val <= 500
		1 <= left <= right <= n

 */
public class Problem431 {

	public static void main(String[] args) {
		ListNode _5 = new ListNode(5);
		ListNode _4 = new ListNode(4, _5);
		ListNode _3 = new ListNode(3, _4);
		ListNode _2 = new ListNode(2, _3);
		ListNode head = new ListNode(1, _2);
		ListNode rev = reverseBetween(head, 2, 4);
		while(rev.next != null) {
			System.out.print(rev.val + " -> ");
			rev = rev.next;
		}
		System.out.print(rev.val);
	}

	public static ListNode reverseBetween(ListNode head, int left, int right) {
	    if (head == null || left == right) return head;

	    ListNode dummy = new ListNode(0);
	    dummy.next = head;
	    ListNode prev = dummy;

	    // 1️⃣ Move prev to node before "left"
	    for (int i = 1; i < left; i++) {
	        prev = prev.next;
	    }

	    // 2️⃣ Start reversing
	    ListNode curr = prev.next;

	    for (int i = 0; i < right - left; i++) {
	        ListNode next = curr.next;
	        curr.next = next.next;
	        next.next = prev.next;
	        prev.next = next;
	    }

	    return dummy.next;	}
		
	static class ListNode {
	     int val;
	     ListNode next;
	     ListNode() {}
	     ListNode(int val) { this.val = val; }
	     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

}
