package com.msb.lrg.problems.design;

import java.util.ArrayDeque;

/*

🧠 FAANG Problem — Design a Web Browser History
	📌 Problem Statement
		Design a browser history system that supports:
			visit(url)
			back(steps)
			forward(steps)
		Example:
			visit("google.com")
			visit("facebook.com")
			visit("youtube.com")
			back(1) → facebook.com
			back(1) → google.com
			forward(1) → facebook.com
			visit("linkedin.com")
			forward(2) → linkedin.com
			back(2) → google.com
	🎯 FAANG Thinking
		This tests:
			Stack design
			State management
			Real world system modeling
		Real world analogy:
			👉 Chrome back / forward buttons
	🧩 Approach (FAANG Expected)
		Use Two Stack Design
		Why?
			Because:
				Back = move current → forward stack
				Forward = move current → back stack
		This is optimal.
		Data Structures
			Stack<String> backStack
			Stack<String> forwardStack
			String currentPage
	⏱ Time Complexity
			| Operation | Complexity |
			| --------- | ---------- |
			| visit     | O(1)       |
			| back      | O(k)       |
			| forward   | O(k)       |
		Where:
			k = steps
		Space Complexity:
			O(n)
	🏗️ How FAANG Improves This Design (IMPORTANT)
		Interviewer next asks:
			👉 How real browser handles history?
			Then you say:
				1️⃣ Use Doubly Linked List Instead of Stack
					Why?
						Efficient navigation
						No stack duplication
						Better memory locality
				2️⃣ Distributed Browser Sync
					Real system:
						History stored in cloud
						Sync across devices
					Design:
						Browser → API → History Service → DB
				3️⃣ Add Persistence
					Currently:
						❌ Memory only
					Improve:
						✔ Store in database
						✔ Crash recovery

 */
public class C1BrowserDesign {

	public static void main(String[] args) {
		Browser browser = new Browser("google.com");
		browser.visit("facebook.com");
		browser.visit("youtube.com");

		System.out.println(browser.back(1));  	// facebook.com
		System.out.println(browser.back(1));  	// google.com
		System.out.println(browser.forward(1)); 	// "facebook.com"
		browser.visit("linkedin.com");
		System.out.println(browser.forward(2)); 	// "linkedin.com"
		System.out.println(browser.back(2));  	// google.com

	}

	static class Browser {
		
		ArrayDeque<String> backwardStack;
		ArrayDeque<String> forwardStack;
		String current;
		
		Browser(String homepage) {
			backwardStack = new ArrayDeque<>();
			forwardStack = new ArrayDeque<>();
			current = homepage;
		}
	
		public void visit(String url) {
			backwardStack.push(current);
			forwardStack.clear();
			current = url;
		}
		
		public String back(int n) {
			while(n-- > 0 && !backwardStack.isEmpty()) {
				forwardStack.push(current);
				current = backwardStack.pop();
			}
			return current;
		}
		
		public String forward(int n) {
			while(n-- > 0 && !forwardStack.isEmpty()) {
				backwardStack.push(current);
				current = forwardStack.pop();
			}
			return current;		
		}
	
	}
	
}
