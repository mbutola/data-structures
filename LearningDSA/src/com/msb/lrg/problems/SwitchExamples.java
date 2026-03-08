package com.msb.lrg.problems;

public class SwitchExamples {

	public static void main(String[] args) {

		version1_0();
		version5_0();
		version7_0();
		version12_0();
		version13_0();
		version17_0();
		version21_0();
		version21_1();
		version21_2();	
		version21();
	}
	
	/*
	 	pattern-matching model:
			switch expressions
			record patterns
			sealed hierarchies
			guards (when clauses)

		Let’s build a real-world example that demonstrates all of these together.
		Example: Payment Processing System
		We’ll model a sealed hierarchy of payment events, 
			use records for immutable data, 
			and handle logic using a pattern-matching switch with guards.
	 */
	static void version21() {
		
        PaymentEvent event = new PaymentSuccess("TXN001", 12000);
//        PaymentEvent event = new PaymentFailure("TXN002", "timeout error");
//        PaymentEvent event = new PaymentPending("TXN003", "UPI");
        
        String result = 
	        	switch (event) {
			        // ✅ Record pattern + guard condition
			        case PaymentSuccess(String txnId, double amt) when amt > 10000 ->
			            "High-value payment approved: " + txnId + ", amount $" + amt;
			
			        case PaymentSuccess(String txnId, double amt) ->
			            "Payment successful for txn " + txnId + ", amount $" + amt;
			
			        // ✅ Record pattern with guard
			        case PaymentFailure(String txnId, String reason) when reason.contains("timeout") ->
			            "Payment " + txnId + " failed due to network timeout. Retry scheduled.";
			
			        case PaymentFailure(String txnId, String reason) ->
			            "Payment " + txnId + " failed. Reason: " + reason;
			
			        // ✅ Another pattern
			        case PaymentPending(String txnId, String channel) when channel.equalsIgnoreCase("UPI") ->
			            "Payment " + txnId + " pending via UPI, waiting confirmation.";
			
			        case PaymentPending(String txnId, String channel) ->
			            "Payment " + txnId + " pending on channel: " + channel;
			
			        // ✅ Null safety
			        case null -> "No payment event received.";
			    };
		
	    System.out.println("version21 :: " + result);    
	}
	
	/*
	 	The Real Purpose of Sealed Types
			The goal of sealed in Java is not to make all subclassing impossible.
			It’s to control who can extend the class at the first level.
			In other words:
				You (the author of the sealed class) decide who is allowed to continue the inheritance chain — and who can’t.
			So, once a class is permitted (listed in permits), that class gets to decide:
				whether it wants to stay closed (final),
				continue controlled sealing (sealed),
				or open things back up (non-sealed).
			🧩 Example — Controlled Opening
				sealed class Vehicle permits Car, Truck, Bike {}
				
				final class Car extends Vehicle {}        // fully closed
				sealed class Truck extends Vehicle permits MiniTruck, HeavyTruck {} // semi-open
				non-sealed class Bike extends Vehicle {}  // open hierarchy
				
		Switch + Sealed Class (Pattern Matching)
		From Java 21, you can use a switch with pattern matching on a sealed class hierarchy.
		
	 */
	static void version21_2(){
		
		Shape shape = new Triangle(3,4);
		
		double area = switch (shape) {
						case Circle c -> Math.PI * c.radius * c.radius;
						case Rectangle r -> r.height * r.width ;
						case Triangle t -> 0.5 * t.base * t.height;
		
		};
		
		System.out.println("version21_2 :: " + area);	
	}
	
	/*
	 	Record Patterns in switch (finalized in Java 21).
		It allows you to deconstruct record types directly inside a switch or an instanceof — no more manual p.x() / p.y() calls.
		Concept						Example											Added In
		------------------			---------------------------------				------------
		Record declaration			record Point(int x, int y)						Java 16
		Basic record pattern		case Point(int x, int y)						Java 19
		Nested record patterns		case Rectangle(Point(int x1, int y1), ...)		Java 20
		Record pattern in switch	switch(obj) { case Point(int x, int y) -> ... }	Java 21
	 */
	static void version21_1(){
		/*
		  	A record pattern lets you destructure a record into its components in-place.
			For example, instead of:
				if (obj instanceof Point p) {
			    	int x = p.x();
			    	int y = p.y();
				}

			You can write:
				if (obj instanceof Point(int x, int y)) {
			    	// x and y are directly bound
				}

			This works in switch expressions, too.
		*/
		
		record Point(int x, int y) {};
		
		Point point = new Point(3,5);
		
		String result = switch (point) {
							case Point(int x, int y) -> "(" + x + "," + y + ")";
							case null -> "null object ...";
							default -> "unknown shape ...";
						};
		
		System.out.println("version21_1() :: " + result);
	}
	
	/*
	 	Pattern Matching for switch with Guards (when clauses), added in Java 21 (final)
		Java’s Pattern Matching for switch allows you to:
			Match type patterns directly inside a switch,
			Avoid repetitive casting,
			Add guard conditions (via when clauses),
			Handle null safely.
			It’s the next evolution of instanceof pattern matching (added in Java 16).
	 */
	static void version21_0(){
		
		Object obj = -5;
		
		String result = switch(obj) {
			case String s when s.isEmpty() -> "empty string ...";
			case String s when s.length() > 10 -> "long string ...";
			case String s -> "nornal string ...";
			case Integer i when i<0 -> "negative integer ...";
			case Integer i -> "positive integer ...";
			case null -> "null value";
			default -> "unknown type ";
		};
		
		System.out.println("version21_0() :: " + result);
	}
	
	/*
	 	Java 17 (2021) — Pattern Matching for switch (Preview)
			Allows switch to match types and destructure objects.
			Smart type casting
			Cleaner than instanceof chains
			Handles null safely (from Java 17+)
		Java 21 (2023) — Pattern Matching for Switch: Finalized
			Becomes a standard, non-preview feature.
			Supports sealed classes and record patterns.
			Compiler ensures all sealed subtypes are handled — no default needed.
			Very powerful for domain modeling.
	 */
	static void version17_0(){
		// Pattern Matching lets you test a variable’s type and extract its value in one step — directly inside a switch or if.
		
		// Old way (before pattern matching):
		Object obj = "Hello";
		if (obj instanceof String) {
		    String s = (String) obj;   // manual casting
		    System.out.println(s.toUpperCase());
		}

		// New way (with pattern matching):
		if (obj instanceof String s) {
		    System.out.println(s.toUpperCase()); // auto-cast, cleaner
		}

		// No explicit cast
		// The variable (s) is automatically cast and scoped inside the block	
		
		// Pattern Matching in switch (Java 17+)
		// Let’s start with a simple example.

		String result = switch (obj) {
		    case String s -> "String of length " + s.length();
		    case Integer i -> "Integer value " + i;
		    case null -> "Null value";
		    default -> "Unknown type";
		};

		System.out.println(result);
		
		// Automatically performs instanceof and casting
		// No break needed
		// Handles null safely
	}
		
	/*
	 	Java 12 (2019, Preview) — Switch Expressions (Preview)
	 		Introduced new arrow (->) syntax and ability to return values directly.
	 		Features introduced:
				Arrow syntax case ... ->
				Multiple case labels (comma-separated)
				No fall-through
				Can return a value
	 	Java 13 (2019, Preview 2) — yield keyword introduced
	 		Added the yield keyword to return a value from block-style cases.
	 		yield lets you do complex logic inside a case block.
	 	Java 14 (2020) — Switch Expressions become STANDARD
			Officially became a stable feature.
			So from Java 14 onward, you can freely use:
				Arrow (->) syntax
				yield
				Expression-style switch
			No preview flags needed anymore
	 */
	static void version13_0(){
		String day = "WEDNESDAY";
		
		int numLetters = switch (day) {
							case "MONDAY", "FRIDAY", "SUNDAY" -> 5;
							case "TUESDAY" -> 7;
							default -> {
								System.out.println("Unknown day ...");
								yield 0;
							}
						};
		System.out.println(numLetters);
		
	}
	
	static void version12_0(){
		String day = "WEDNESDAY";
		
		int numLetters = switch (day) {
							case "MONDAY", "FRIDAY", "SUNDAY" -> 5;
							case "TUESDAY" -> 7;
							case "THRUSDAY", "SATURDAY" -> 8;
							case "WEDNESDAY" -> 9;
							default -> 0;
						};
		System.out.println(numLetters);
	}
	
	/*
	 	Java 7 (2011) — String support added
	 	You can now use String directly in switch statements.
	 	The compiler uses String.hashCode() and compares with equals().
	 */
	static void version7_0(){
		
		String fruit = "Apple";
		
		switch(fruit) {
			case "Apple" :
				System.out.println("Sweet ...");
				break;
			case "Mango" :
				System.out.println("Tropical ...");
				break;
			default :
				System.out.println("Unknown fruit ...");
		}
	}
	
	/*
		Java 5 (2004) — enum support added
		You could now use enum constants in switch.
		Enums make switches more readable and type-safe for fixed sets of values.
		
	*/
	static void version5_0(){
		enum Day {MONDAY, TUESDAY, WEDNESDAY};
		
		Day day = Day.WEDNESDAY;
		
		switch (day) {
			case MONDAY :
				System.out.println("Start of the week ...");
				break;
			case TUESDAY :
				System.out.println("Other day ...");
				break;
			case WEDNESDAY :
				System.out.println("Mid week ...");
		}
	}	
	
	/*
	 	Java 1.0 → Classic switch
	 	Only byte, short, char, int and their wrappers (Byte, Short, Character, Integer)
	 	Limitations:
	 		Must manually use break
	 		No expression form (can’t return a value)
	 		Only primitive int-like types (no String)
	 */
	static void version1_0(){
		int day = 3;
		switch (day) {
		    case 1:
		        System.out.println("Monday");
		        break;
		    case 2:
		        System.out.println("Tuesday");
		        break;
		    case 3:
		        System.out.println("Wednesday");
		        break;
		    default:
		        System.out.println("Invalid day");
		}
	}

}

sealed interface Shape permits Circle, Rectangle, Triangle {}

final class Circle implements Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }
}

final class Rectangle implements Shape {
    double width, height;
    Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }
}

final class Triangle implements Shape {
    double base, height;
    Triangle(double base, double height) {
        this.base = base; this.height = height;
    }
}

sealed interface PaymentEvent permits PaymentSuccess, PaymentFailure, PaymentPending {}

record PaymentSuccess(String txnId, double amount) implements PaymentEvent {}
record PaymentFailure(String txnId, String reason) implements PaymentEvent {}
record PaymentPending(String txnId, String channel) implements PaymentEvent {}
