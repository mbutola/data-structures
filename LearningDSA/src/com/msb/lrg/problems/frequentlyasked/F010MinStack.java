package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Stack;

/*

🔄 Queue using Two Stacks
	🧠 One-line idea
		👉 Use one stack for input, one for output — reverse order when needed
	🧩 Problem
		Implement a Queue (FIFO) using Stacks (LIFO)
	⚙️ Data structures
		inStack → for enqueue
		outStack → for dequeue
	🔄 Operations
		➕ Enqueue (push)
			Just push into inStack
		➖ Dequeue (pop)
			If outStack empty:    
				move all elements from inStack → outStackThen pop from outStack
	👀 Peek
		Same as dequeue but don’t remove
	🧠 Why this works
		👉 Moving elements reverses order:
			Stack → reversed → becomes queue order
	🔍 Example
		Enqueue: 1,2,3
		inStack:  [1,2,3]
		outStack: []
		Dequeue:Move → outStack: [3,2,1]
		Pop → 1 ✔

🔁 Stack using Queues (rotate trick)
	🧠 One-line idea
		👉 Push into queue, then rotate it so the newest element comes to the front
	🧩 Goal
		Implement Stack (LIFO) using Queue (FIFO)
	⚙️ Technique: Rotate after push
		Use one queue
		After adding element → rotate previous elements behind it
	🔄 Push operation (key step)
		1. Add element to queue  2. Rotate all previous elements
	🔍 Example
	Push 1:
		[1]
	Push 2:
		Add → [1,2]  Rotate → [2,1]
	Push 3:
		Add → [2,1,3]  Rotate → [3,2,1]
		👉 Now front = top of stack
	⚙️ Operations
		| Operation | Action       |
		| --------- | ------------ |
		| push      | add + rotate |
		| pop       | remove front |
		| top       | peek front   |

📦 Min Stack (get minimum in O(1))
	🧠 One-line idea
		👉 Store the current minimum along with each value (or keep a parallel min stack)
	🧩 Operations
		push(x)
		pop()
		top()
		getMin() → O(1)
	⚙️ Approach 1: Store (value, currentMin) in one stack
		👉 Each entry remembers the minimum so far
	🔄 Logic
		push(x):  
			min = stack empty ? x : min(x, stack.peek().min) 
			push (x, min)
		pop():  
			pop
		top():  
			return top.value
		getMin():  
			return top.min


 */
public class F010MinStack {

	public static void main(String[] args) {
//		MinStack st = new MinStack();
		MinStack2 st = new MinStack2();
        st.push(5);
        st.push(2);
        st.push(8);
        st.push(1);

        System.out.println(st.getMin()); // 1
        st.pop();
        System.out.println(st.getMin()); // 2
        System.out.println(st.top());    // 8
	}
	
	/*
		⚙️ Approach 2: Two stacks (value stack + min stack)
		👉 Keep:
		stack → all values
		minStack → track minimums
		🔄 Logic
		push(x):
		  push to stack
		  if minStack empty or x <= minStack.peek():
		      push x to minStack
		pop():
		  if popped == minStack.peek():
		      pop from minStack
	 */
	
	static class MinStack2 {
		
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> min = new Stack<>();
		
		void push(int num) {
			stack.push(num);
			if(min.isEmpty() ||  num <= min.peek()) {
				min.push(num);
			}
		}
		
		int top() {
			return stack.peek();
		}
		
		int getMin() {
			return min.peek();
		}
		
		int pop() {
			if(stack.peek() == min.peek())
				min.pop();
			return stack.pop();
		}
	}

	static class MinStack {
		
		Stack<Pair> stack = new Stack<>();
		
		void push(int num) {
			int min = stack.isEmpty() ? num : Math.min(num, stack.peek().min);
			stack.push(new Pair(num, min));
		}
		
		int top() {
			return stack.peek().val;
		}
		
		int getMin() {
			return stack.peek().min;
		}
		
		int pop() {
			return stack.pop().val;
		}
	}
	
	static class Pair {
		int val;
		int min;
		
		Pair(int val, int min){
			this.val = val;
			this.min = min;
		}
	}

}
