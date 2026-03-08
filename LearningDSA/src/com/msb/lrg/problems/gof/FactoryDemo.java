package com.msb.lrg.problems.gof;

/*
 	What is the Factory Pattern?
 		The Factory Pattern is a creational design pattern that provides an interface or method to create 
 		objects without exposing the creation logic (i.e., new) to the client.
 		In simple words:
 			The Factory decides which class to instantiate, so your code doesn’t need to.

	Purpose (Intent)
		To encapsulate object creation logic.
		To decouple the client code from concrete class names.
		To allow flexible addition of new object types without modifying client code.

	Problem / Issue It Solves
		Without a factory, your code directly uses new, like this:
			if(type.equals("CIRCLE")) obj = new Circle();
			else if(type.equals("SQUARE")) obj = new Square();

	Problems:
		Code depends on concrete classes (Circle, Square).
		Adding a new shape means modifying client code (violates Open/Closed Principle).
		The logic of creation is scattered across multiple places.

	How It Solves It
		Factory pattern moves all “creation logic” into one place — the Factory class.
		Clients only call a common interface like Shape shape = ShapeFactory.createShape("CIRCLE");
		The factory decides which object to instantiate.
			Hides new logic.
			Reduces code changes.
			Makes system extensible and loosely coupled.
 */
public class FactoryDemo {

	public static void main(String[] args) {
		ShapeFactory factory = new ShapeFactory();
		Shape s = factory.getShape("Circle");
		s.Draw();
	}

}

interface Shape{
	public void Draw();
}

class Circle implements Shape{
	public void Draw() {
		System.out.println("Drawing circle ...");
	}
}

class Rectangle implements Shape{
	public void Draw() {
		System.out.println("Drawing Rectangle ...");
	}
}

class ShapeFactory {
	
	public Shape getShape(String type) {
		return switch(type.toLowerCase()) {
			case "circle" -> new Circle();
			case "rectangle"  -> new Rectangle();
			default -> throw new IllegalStateException("Unknown shape...");
		};
	}
}