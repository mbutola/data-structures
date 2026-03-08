package com.msb.lrg.problems.solid;

/*
	L — Liskov Substitution Principle (LSP)
	Purpose
		LSP ensures that subclasses can stand in for their parent classes without breaking the program.
		In other words:
			“Objects of a superclass should be replaceable with objects of its subclasses without altering 
			the correctness of the program.”
	Problem It Solves
		Without LSP, inheritance can break your application due to:
			Unexpected behavior in subclasses
			Broken parent–child relationships
			Violations of expected contracts
		Typical issues include:
			Overridden methods that do less or more than expected
			Subclass removing behaviors
			Subclass throwing new unexpected exceptions
			Subclass changing invariants (e.g., rectangle → square problem)
		LSP prevents BAD inheritance.
	How It Solves It
		LSP sets rules for subclass design:
			A subclass must NOT:
				Break behavior guaranteed by the parent
				Weaken preconditions (input restrictions)
				Strengthen postconditions (results)
				Change expected return type behavior
				Throw additional unexpected exceptions
			A subclass MUST:
				Honor the contract defined by the parent
				Preserve all invariants
				Behave predictably for all parent class use-cases
				This ensures polymorphism works safely.
	Advantages
		| Advantage               | Explanation                                                |
		| ----------------------- | ---------------------------------------------------------- |
		| Predictable inheritance | Subclasses behave reliably when used via parent references |
		| Safer polymorphism      | Fewer strange runtime bugs                                 |
		| Easy testability        | Parent class test suite also works for subclass            |
		| Better API design       | Encourages strong class contracts (behaviors)              |
	Disadvantages
		| Disadvantage               | Explanation                                                 |
		| -------------------------- | ----------------------------------------------------------- |
		| Requires careful design    | You must think through contracts and invariants             |
		| Makes inheritance harder   | You may discover inheritance won’t work—forcing composition |
		| Can limit subclass freedom | Subclass cannot change major parent behaviors               |
	Example — Violating LSP (The Rectangle–Square Problem)
		Wrong Approach — Subclass breaks parent behavior
			class Rectangle {
			    protected int width;
			    protected int height;
			
			    public void setWidth(int w) { this.width = w; }
			    public void setHeight(int h) { this.height = h; }
			
			    public int getArea() {
			        return width * height;
			    }
			}
			
			class Square extends Rectangle {
			    @Override
			    public void setWidth(int w) {
			        this.width = w;
			        this.height = w;  // Square forces height = width
			    }
			
			    @Override
			    public void setHeight(int h) {
			        this.width = h;
			        this.height = h;
			    }
			}
		Why this violates LSP?
			A caller expects:
				Rectangle r = new Rectangle();
				r.setWidth(5);
				r.setHeight(10);
			Area should be 50.
			But using a Square:
				Rectangle r = new Square();
				r.setWidth(5);
				r.setHeight(10);  // Internally height=10 forces width=10
				r.getArea(); // Returns 100 — WRONG!
			Parent expectations are broken → violates LSP.
 
 */
public class LSPDemo {

	public static void main(String[] args) {
		ShapeLSP rectangle = new RectangleLSP(5,10);
		System.out.println("Rectangle Area :: " + rectangle.getArea());
		ShapeLSP square = new SquareLSP(5);
		System.out.println("Square Area :: " + square.getArea());
	}

}

interface ShapeLSP {
	int getArea();
}

class RectangleLSP implements ShapeLSP {
	private int height;
	private int width;
	
	RectangleLSP(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	public int getArea() {
		return height*width;
	}
}

class SquareLSP implements ShapeLSP {
	private int side;
	
	SquareLSP(int side) {
		this.side = side;
	}
	
	public int getArea() {
		return side*side;
	}
}
