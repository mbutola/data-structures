package com.msb.lrg.problems.practice.package0;

/*

LeetCode (142, Medium) Linked List Cycle II

	Given the head of a linked list, return the node where the cycle begins. If 
	there is no cycle, return null.
	There is a cycle in a linked list if there is some node in the list that can be 
	reached again by continuously following the next pointer. Internally, 
	pos is used to denote the index of the node that tail's next pointer is 
	connected to (0-indexed). It is -1 if there is no cycle. Note that pos is 
	not passed as a parameter.
	Do not modify the linked list.
	Example 1:
		Input: head = [3,2,0,-4], pos = 1
		Output: tail connects to node index 1
		Explanation: There is a cycle in the linked list, where tail connects to the second node.
	Example 2:
		Input: head = [1,2], pos = 0
		Output: tail connects to node index 0
		Explanation: There is a cycle in the linked list, where tail connects to the first node.
	Example 3:
		Input: head = [1], pos = -1
		Output: no cycle
		Explanation: There is no cycle in the linked list.
	Constraints:
		The number of the nodes in the list is in the range [0, 104].
		-105 <= Node.val <= 105
		pos is -1 or a valid index in the linked-list.

 */
public class Problem8 {

	public static void main(String[] args) {
		ListNode _3 = new ListNode(-4);
		ListNode _2 = new ListNode(0);
		ListNode _1 = new ListNode(2);
		ListNode _0 = new ListNode(3);
		_0.next = _1;
		_1.next = _2;
		_2.next = _3;
		_3.next = _1;
		
		ListNode res = detectCycle(_0);
		System.out.println("Result :: " + res.val);
	}
	
	public static ListNode detectCycle(ListNode head) {
		
		ListNode slow = head;
		ListNode fast = head;
		while(slow.next != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		
    		if(slow.val == fast.val)
    			break;
        }
		
		if(fast == null || fast.next == null)
			return null;
        
		slow = head;
        while(slow != fast) {
    		slow = slow.next;
    		fast = fast.next;
        }
        return slow;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}



