package com.msb.lrg.problems.gof;

/*
 	Decorator Pattern (Structural Design Pattern)
		The Decorator Pattern lets you add new behavior to an object at runtime, without modifying its class.
		Think of it like wrapping an object with layers—each layer adds functionality.
	Purpose
		To add functionality dynamically, without subclassing.
		To follow the Open/Closed Principle
			(open for extension, closed for modification).
		To avoid having many subclasses for every variation of a class.
	Problem It Solves
		Traditional inheritance causes subclass explosion
		Suppose you have a Coffee class and you want variations:
			Coffee with Milk
			Coffee with Sugar
			Coffee with Sugar + Milk
			Coffee with Caramel
			Coffee with Caramel + Milk
				… infinite combinations
		You’ll end up with:
			CoffeeWithMilk
			CoffeeWithSugar
			CoffeeWithSugarAndMilk
			CoffeeWithCaramel
			...
		This is unmaintainable.
	How Decorator Solves It
		Decorator wraps an object inside another object:
		Coffee (interface)
			- SimpleCoffee (Concrete Component)
			- CoffeeDecorator (Base Decorator)
				- MilkDecorator
				- SugarDecorator
				- CaramelDecorator
		Each decorator adds behavior:
			cost() = base cost + decorator cost
		You can stack multiple decorators:
			Coffee coffee = new MilkDecorator(new SugarDecorator(new SimpleCoffee()));
Advantages
	| Benefit                           | Explanation                         |
	| --------------------------------- | ----------------------------------- |
	| **Add behavior dynamically**      | No need to change existing classes  |
	| **Avoids subclass explosion**     | Combinations handled at runtime     |
	| **Follows Open/Closed principle** | New decorators = new behavior       |
	| **Flexible**                      | Mix & match decorators in any order |
Disadvantages
	| Drawback               | Why                                             |
	| ---------------------- | ----------------------------------------------- |
	| **Many small classes** | Each behavior is a separate class               |
	| **Debugging harder**   | Call stack can be deep due to nested decorators |
	| **Order matters**      | Wrapping sequence may change final behavior     |
Example:
	Java’s new BufferedInputStream(new FileInputStream("file.txt")) → classic decorator.

 */
public class DecoratorDemo {

	public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " => Rs." + coffee.cost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " => Rs." + coffee.cost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " => Rs." + coffee.cost());
	}

}

interface Coffee {
	String getDescription();
	double cost();
}

class SimpleCoffee implements Coffee {
	public String getDescription() {
		return "Simple Coffee";
	}
	public double cost() {
		return 50;
	}
}

class DecoratorCoffee implements Coffee {
	
	protected Coffee coffee;
	
	DecoratorCoffee(Coffee coffee){
		this.coffee = coffee;
	}
	
	public String getDescription() {
		return coffee.getDescription();
	}
	public double cost() {
		return coffee.cost();
	}
}

class MilkDecorator extends DecoratorCoffee {
	
	MilkDecorator(Coffee coffee){
		super(coffee);
	}
	
	public String getDescription() {
		return coffee.getDescription() + ", Milk";
	}
	public double cost() {
		return coffee.cost() + 10;
	}
}

class SugarDecorator extends DecoratorCoffee {
	
	SugarDecorator(Coffee coffee){
		super(coffee);
	}
	
	public String getDescription() {
		return coffee.getDescription() + ", Sugar";
	}
	public double cost() {
		return coffee.cost() + 5;
	}
}
