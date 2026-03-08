package com.msb.lrg.problems.gof;

/*
	Adapter Pattern
		What is the Adapter Pattern?
		The Adapter Pattern is a structural design pattern that allows two incompatible interfaces to 
		work together by wrapping one object with another that exposes the expected interface.
		It acts like a translator between objects.
	Purpose
		To convert the interface of a class into another interface that the client expects without modifying 
		the existing classes.
	Problem It Solves
		Sometimes you want to use an existing class, but:
			It has an incompatible interface
			You cannot modify that class (3rd-party library, legacy code)
			Client code expects a different method name or structure
	How the Adapter Solves It
		The adapter:
			Implements the interface your client expects
			Holds a reference to the incompatible class
			Translates calls from client → adaptee
	Advantages
		Works with legacy or third-party code
		Promotes reuse
		Follows Single Responsibility
		Loose coupling
	Disadvantages
		Extra layer = extra complexity
		Too many adapters → messy design
		Not ideal if you can modify the original class
		Can hide performance costs
 */
public class AdapterDemo {
	public static void main(String[] args) {
		PaymentProcessor processor = new StripeAdaptor(new StripePayment());
		processor.pay(100);
	}
}

interface PaymentProcessor {
	void pay(double amount);
}

class StripePayment {
	void makePayment(double amount) {
		System.out.println("Paid with Stripe :: " + amount);
	}
}

class StripeAdaptor implements PaymentProcessor {
	
	StripePayment stripe;
	
	StripeAdaptor(StripePayment stripe) {
		this.stripe = stripe;
	}
	
	public void pay(double amount) {
		stripe.makePayment(amount);
	}
}
