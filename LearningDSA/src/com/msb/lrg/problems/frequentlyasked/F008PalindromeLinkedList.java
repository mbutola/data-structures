package com.msb.lrg.problems.frequentlyasked;

/*

	🔁 Palindrome Linked List (reverse second half)
	🧠 One-line idea
		👉 Find the middle, reverse the second half, then compare both halves
	🧩 Problem
		1 → 2 → 2 → 1  → true  
		1 → 2 → 3 → 2 → 1 → true  
		1 → 2 → 3 → false
	⚙️ Steps
		1️⃣ Find middle (slow & fast pointers)
			slow moves 1 step  
			fast moves 2 steps
			👉 When fast ends → slow is at middle
		2️⃣ Reverse second half
			Reverse list from slow.next
		3️⃣ Compare both halves
			Head vs reversed half
		4️⃣ (Optional) Restore list
	🔄 Example
		1 → 2 → 2 → 1
	Split:
		1 → 2 | 2 → 1
	Reverse second:
		1 → 2
	Compare:
		1==1 ✔  
		2==2 ✔

 */
public class F008PalindromeLinkedList {

	public static void main(String[] args) {
		int[] arr1 = {1, 2, 2, 1};
        ListNode list1 = createList(arr1);

        printList(list1);
        System.out.println("Is Palindrome? " + isPalindrome(list1));

        int[] arr2 = {1, 2, 3};
        ListNode list2 = createList(arr2);

        printList(list2);
        System.out.println("Is Palindrome? " + isPalindrome(list2));

	}
	
	static boolean isPalindrome(ListNode head) {
		
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		ListNode secondHalf = reverse(slow.next);
		ListNode firstHalf = head;
		
		while (secondHalf != null) {
			if(secondHalf.val != firstHalf.val) return false;
			secondHalf = secondHalf.next;
			firstHalf = firstHalf.next;
		}
		
		return true;
	}
	
	static ListNode reverse(ListNode node) {
		
		ListNode prev = null;
		ListNode curr = node;
		
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
		
		ListNode(int val){
			this.val = val;
		}
	}
	
	static ListNode createList(int[] arr) {
		
		ListNode dummy = new ListNode(0);
		ListNode curr = dummy;
		
		for(int num : arr) {
			curr.next  = new ListNode(num);
			curr = curr.next;
		}
		
		return dummy.next;
	}

	static void printList(ListNode list) {
		while(list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}
	}
}
