package com.msb.lrg.problems.gof;

/*
 	Iterator Pattern (Behavioral Pattern)
	What is the Iterator Pattern?
		Iterator Pattern provides a standard way to traverse elements of a collection without exposing its 
		internal structure (array, list, tree, etc.).
		You use an iterator whenever you do:
		Iterator<String> itr = list.iterator();
	Purpose
		Provide sequential access to elements
		Hide underlying data structure
		Allow different traversal strategies
		Support multiple iterators at the same time
	Problem It Solves
		Without Iterator pattern:
		Client code must know how the collection is stored
		Example:
		If stored in an array → for (int i=0...)
		If stored in a linked list → move node by node
		If stored in a tree → DFS/BFS logic
		This means:
		Client becomes tightly coupled with collection internals
		Changing collection structure breaks client code
		Duplicate traversal logic everywhere
	How It Solves the Problem
		Iterator Pattern:
		Creates an Iterator interface:
		hasNext()
		next()
		Collection creates iterator objects
		So internal representation is hidden from clients.
		Client simply calls:
		while (iterator.hasNext()) {
		    iterator.next();
		}
		Client does not know (or care) if the collection is
		array / linked list / tree / hash bucket.
	Advantages
		Decouples collection structure from traversal
		Supports multiple simultaneous traversals
		Simplifies client code
		Easy to add new iteration strategies (reverse, skip N, filter etc.)
		Standard interface (Java, C#, C++ use it)
	Disadvantages
		More classes when building custom iterators
		Not efficient for some collections (e.g., linked list random access)
		Only sequential access (no skipping unless custom iterator)
	Real-World Uses
		Java Collections Framework
		Iterator, ListIterator, Spliterator
		File system traversal
		Tree traversal (iterative DFS)

 */

public class IteratorDemo {

	public static void main(String[] args) {
        NameRepository repository = new NameRepository();

        Iterator<String> itr = repository.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
	}

}

interface Iterator<T> {
	boolean hasNext();
	T next();
}

interface Container {
	Iterator<String> iterator();
}

class NameRepository implements Container {
	
	String[] names = {"John", "David", "Ravi", "Amit"};
	
	public Iterator<String> iterator(){
		return new NameIterator();
	}
	
	class NameIterator implements Iterator<String> {
		int index = 0;
		
		public boolean hasNext() {
			return index < names.length;
		}
		
		public String next() {
			return names[index++];
		}
	}
} 
