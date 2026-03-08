package com.msb.lrg.problems.solid;

/*
	I — Interface Segregation Principle (ISP)
	Purpose
		Clients should not be forced to depend on interfaces they do not use.
		ISP encourages small, focused interfaces rather than large, "fat" interfaces.
	Problem It Solves
		When an interface is too big:
			Classes implementing it must write empty or dummy methods
			Classes get unnecessary responsibilities
			Frequent interface changes break many classes (ripple effect)
			Code becomes tightly coupled and harder to maintain
			Violates Single Responsibility Principle (SRP)
		This typically happens with "God interfaces".
	How It Solves The Problem
		ISP prescribes:
			Break large interfaces into smaller, role-specific interfaces
			Each client implements only what it actually needs.
		This results in:
			Better cohesion
			Better testability
			Independent evolution of each interface
	Advantages
		| Advantage                  | Explanation                                             |
		| -------------------------- | ------------------------------------------------------- |
		| High cohesion              | Each interface focuses on one role                      |
		| Reduced impact of changes  | Change in one interface doesn't break unrelated classes |
		| Cleaner, maintainable code | No dummy or unused methods                              |
		| Encourages good API design | Small interfaces are easier to understand               |
	Disadvantages
		| Disadvantage                | Explanation                                                    |
		| --------------------------- | -------------------------------------------------------------- |
		| More interfaces to maintain | Can increase number of files                                   |
		| Harder initial design       | Requires upfront thinking about separation of responsibilities |
		| Over-segmentation risk      | Too many tiny interfaces may cause confusion                   |
	Bad Example (Violates ISP)
		A single interface with too many responsibilities:
			interface Worker {
			    void work();
			    void eat();
			    void sleep();
			}
		Now implementing classes are forced to implement unwanted methods:
			class Robot implements Worker {    
			    public void work() { System.out.println("Robot is working"); }
			
			    public void eat() { // Robot does NOT eat → unnecessary }
			
			    public void sleep() { // Robot does NOT sleep → unnecessary }
			}
		This violates ISP because Robot is forced to depend on methods it does not need.

 */
public class ISPDemo {

	public static void main(String[] args) {
	}

}

interface Workable {
	void work();
}

interface Eatable {
	void eat();
}

interface Sleepable {
	void sleep();
}

class Humans implements Workable,Eatable,Sleepable {
    public void work() { System.out.println("Human working"); }
    public void eat()  { System.out.println("Human eating"); }
    public void sleep(){ System.out.println("Human sleeping"); }
}

class Robot implements Workable {
    public void work() { System.out.println("Robot working"); }
}