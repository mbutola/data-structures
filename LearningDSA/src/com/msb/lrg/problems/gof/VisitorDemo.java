package com.msb.lrg.problems.gof;

/*
	What is the Visitor Pattern?
		The Visitor Pattern lets you add NEW operations to existing object structures without modifying their classes.
		It separates:
			Data Structure (Elements)
			Operations on Data (Visitors)
	Purpose
		To perform different operations on a set of objects without changing the classes of those objects.
		To follow Open/Closed Principle → open for extension, closed for modification.
	Problem It Solves
		Imagine you have different AST nodes (Expression Tree), File System nodes, or Employee hierarchy:
			Manager
			Developer
			Intern
		If you want to apply MANY new operations:
			calculate salary
			generate report
			print role
			send appraisal email
		Without Visitor, you'd keep changing each class → violates Open/Closed.
	How Visitor Pattern Solves It
		Every object (Element) has an accept(visitor) method
			→ It calls the correct method on the visitor.
		A Visitor interface contains visit methods for each element
			→ visitManager(), visitDeveloper(), etc.
		To add a NEW operation
			→ Just create a new visitor class.
		No need to touch element classes.
		This is Double Dispatch:
			The element decides which visitor method to call
			The visitor decides what operation to perform
	Advantages
		Open for extension (add new operations easily)
		Just add a new visitor class.
		Keeps data classes simple
		They just hold data, no mixed logic.
		Great for complex data structures
		ASTs, file systems, compilers, EMF models.
		Avoids repeated instanceof checks
		Visitor eliminates long if (obj instanceof X) chains.
	Disadvantages
		Hard to add new Element types
		If you add a new class like Tester,
			→ you must update ALL visitor implementations.
		Overkill for small object structures
		Better use polymorphism directly for simple cases.
		Tightly coupled with Element hierarchy
		Each element must implement accept() and know the visitor interface.

 */
public class VisitorDemo {

	public static void main(String[] args) {
		VisitorEmployee[] employees = {
                new Manager(),
                new Developer(),
                new Intern()
            };

            EmployeeVisitor bonusVisitor = new BonusCalculatorVisitor();
            EmployeeVisitor reportVisitor = new ReportVisitor();

            for (VisitorEmployee emp : employees) {
                emp.accept(bonusVisitor);
            }

            System.out.println("---");

            for (VisitorEmployee emp : employees) {
                emp.accept(reportVisitor);
            }
	}

}

interface VisitorEmployee {
	void accept(EmployeeVisitor visitor);
}

class Manager implements VisitorEmployee {
	public void accept(EmployeeVisitor visitor) {
		visitor.visit(this);
	}
}

class Developer implements VisitorEmployee {
	public void accept(EmployeeVisitor visitor) {
		visitor.visit(this);
	}
}

class Intern implements VisitorEmployee {
	public void accept(EmployeeVisitor visitor) {
		visitor.visit(this);
	}
}

interface EmployeeVisitor {
	void visit(Manager manager);
	void visit(Developer developer);
	void visit(Intern intern);
}

class BonusCalculatorVisitor implements EmployeeVisitor {
	public void visit(Manager manager) {
		System.out.println("Manager gets 15% bonus");
	}
	
	public void visit(Developer developer) {
		System.out.println("Developer gets 10% bonus");
	}
	
	public void visit(Intern intern) {
		System.out.println("Intern gets 5% bonus");
	}
}

class ReportVisitor implements EmployeeVisitor {
	public void visit(Manager manager) {
		System.out.println("Generating Manager Report ...");
	}

	public void visit(Developer developer) {
		System.out.println("Generating Developer Report ...");
	}
	
	public void visit(Intern intern) {
		System.out.println("Generating Intern Report ...");
	}
}

