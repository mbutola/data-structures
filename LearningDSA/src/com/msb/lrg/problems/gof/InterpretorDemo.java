package com.msb.lrg.problems.gof;
/*
 	Interpreter Pattern (Behavioral Pattern)
	What is the Interpreter Pattern?
		Interpreter Pattern defines a grammar (rules) for a language and uses classes to interpret sentences in that language.
		Used when you need to evaluate expressions based on a simple grammar
		Each grammar rule becomes a class
		It helps build mini-languages, expression evaluators, query processors, etc.
	Purpose
		Define a class-based way to represent grammar rules
		Interpret expressions repeatedly
		Build a reusable expression engine
	Problem It Solves
		Without the Interpreter pattern:
			Evaluating expressions becomes hard-coded
			Complex if-else or long parsing logic
			Adding new operations breaks existing code
			Grammar is not clearly represented
	How It Solves the Problem
		Interpreter pattern:
			Defines an Expression interface with an interpret(context) method
			Creates terminal expressions (values)
			Creates non-terminal expressions (rules like Add, Subtract, AND, OR, etc.)
			Uses a tree of expressions to evaluate a grammar
			Each class = one rule in the grammar.
	Advantages
			Easy to change grammar
			Each rule is a separate class.
			Good for simple languages / expressions
			Math, boolean logic, filtering, etc.
			Extensible
			Add new grammar rules without touching old ones.
	Disadvantages
			Class explosion
			For a real grammar, many classes are required.
			Slow for complex languages
			Better to use parsing libraries or compiler tools.
			Not suitable for full-scale languages
			Only good for small DSLs (Domain Specific Languages).
	Real-World Uses
			Logical Rule Engines
			Mathematical Expression Evaluators
			Simple SQL parsing
			Filter Language (e.g., age > 18 AND country = "India")
			Search query processors

 */
public class InterpretorDemo {

	public static void main(String[] args) {
        Expression ten = new NumberExpression(10);
        Expression five = new NumberExpression(5);
        Expression three = new NumberExpression(3);

        Expression add = new AddExpression(ten, five);  // (10 + 5)
        Expression subtract = new SubtractExpression(add, three); // (10+5)-3

        System.out.println("Result = " + subtract.interpret());
	}

}

interface Expression {
	int interpret();
}

class NumberExpression implements Expression {
	
	private int number;
	
	NumberExpression(int number){
		this.number = number;
	}
	
	public int interpret() {
		return number;
	}
}

class AddExpression implements Expression {
	
	Expression left;
	Expression right;
	
	AddExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public int interpret() {
		return left.interpret() + right.interpret();
	}
}

class SubtractExpression implements Expression {
	
	Expression left;
	Expression right;
	
	SubtractExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public int interpret() {
		return left.interpret() - right.interpret();
	}
}