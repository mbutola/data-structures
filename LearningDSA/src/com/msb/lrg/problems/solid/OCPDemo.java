package com.msb.lrg.problems.solid;

/*
	What is Open/Closed Principle (OCP)?
	Software entities (classes, modules, functions) should be OPEN for extension but CLOSED for modification.
		Means:
			You should be able to add new behavior WITHOUT modifying existing code.
			Achieved through abstraction, interfaces, inheritance, composition, and polymorphism.
	Purpose
		To avoid changing stable, tested, working code as new requirements come in.
		OCP ensures:
			Stability
			Scalability
			Maintainability
			Less regression bugs
	Problem OCP Solves
		Without OCP
			Suppose you have code that checks multiple types using:
				long if-else chains
				switch statements
				repeated modifications to add new types
				Example:
					if(type == "SQUARE") { ... }
					else if(type == "CIRCLE") { ... }
					else if(type == "TRIANGLE") { ... }   <-- every new type forces change
			Problems:
				You modify existing code each time → risk of breaking it.
				Code grows and becomes messy.
				Violates Open/Closed.
	How OCP Solves It
		OCP says:
			Create an abstraction (interface)
			Implement new behavior by adding new classes (extensions)
			Never touch existing classes
		This is achieved by:
			Polymorphism
			Strategy pattern
			Factory pattern
			Dependency injection
		Result: new features = add new classes, not touch old ones.
	Advantages
		1. No regression bugs
			Stable code remains untouched.
		2. Easy to add new features
			Just extend with new classes.
		3. High flexibility & scalability
			One abstraction supports many implementations.
		4. Cleaner code
			Removes long if/else or switch blocks.
	Disadvantages
		1. More classes
			Every extension adds new classes.
		2. Requires good upfront design
			Bad abstraction leads to misuse.
		3. Can be overkill
			For small projects, too many layers may complicate things.
	Java Example — Shape Area Calculator (Before & After OCP)
		Bad Design — Violates OCP
		New requirement → must modify existing code.
			class AreaCalculator {
			    public double calculateArea(Object shape) {
			
			        if (shape instanceof Circle) {
			            Circle c = (Circle) shape;
			            return Math.PI * c.radius * c.radius;
			
			        } else if (shape instanceof Square) {
			            Square s = (Square) shape;
			            return s.side * s.side;
			
			        } else if (shape instanceof Rectangle) {
			            Rectangle r = (Rectangle) shape;
			            return r.width * r.height;
			        }
			
			        return 0;
			    }
			}
		Any new shape = modify this class.
		Breaks OCP.

 */
public class OCPDemo {

	public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();

        Shape circle = new Circle(5);
        Shape square = new Square(4);

        System.out.println(calc.calculate(circle));  // 78.5
        System.out.println(calc.calculate(square));  // 16
	}

}

class AreaCalculator {
	public double calculate(Shape shape) {
		return shape.area();
	}
}

interface Shape {
	double area();
}

class Circle implements Shape {
	
	private double radius;
	
	Circle(double radius) {
		this.radius = radius;
	}
	
	public double area() {
		return Math.PI * radius * radius;
	}
}

class Square implements Shape {
	
	private double side;
	
	Square(double side) {
		this.side = side;
	}
	
	public double area() {
		return side * side;
	}
}

class Triangle implements Shape {
	
	private double base;
	private double height;
	
	Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}
	
	public double area() {
		return 0.5 * base * height;
	}
}
