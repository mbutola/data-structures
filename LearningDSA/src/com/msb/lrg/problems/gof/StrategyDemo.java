package com.msb.lrg.problems.gof;

/*
 	What is the Strategy Pattern?
		The Strategy Pattern allows you to define a family of algorithms, encapsulate each one, and make 
		them interchangeable at runtime.
		You can change the behavior of an object without changing the object’s class.
	Purpose
		To choose an algorithm/behavior at runtime.
		To avoid long if-else / switch-case statements.
		To make algorithm selection flexible, pluggable, and testable.
	Problem It Solves
		Without Strategy Pattern:
			You often see code like:
				if (paymentType.equals("UPI")) {
				    // UPI logic
				} else if (paymentType.equals("CARD")) {
				    // card logic
				} else if (paymentType.equals("NETBANKING")) {
				    // netbanking logic
				}
		Problems:
			Hard to maintain
			Adding new strategy => modify existing code → violates Open/Closed Principle
			Testing each algorithm is difficult
			Behavior selection scattered everywhere
	How Strategy Pattern Solves It
		Move each algorithm into its own class
		Create a common Strategy interface
		Context class uses a Strategy object
		You can change the strategy at runtime
	Advantages
		No if-else for algorithm selection
		Clean, modular code.
		Open/Closed Principle
		Add new strategies without modifying existing code.
		Strategies are interchangeable
		Set different behavior at runtime.
		Easy unit testing
		Each strategy is a separate class.
		Better code organization
		Each behavior is isolated.
	Disadvantages
		More classes
		Each strategy becomes a separate class.
		Context must know about strategies
		Someone must choose the right strategy.
		Simple problems may become over-engineered
 
 */
public class StrategyDemo {

	public static void main(String[] args) {
        Checkout checkout = new Checkout();

        checkout.setPaymentStrategy(new UpiPayment("user@upi"));
        checkout.processOrder(500);

        checkout.setPaymentStrategy(new CardPayment("1234-5678-9876-5432"));
        checkout.processOrder(1200);

        checkout.setPaymentStrategy(new NetBankingPayment("HDFC"));
        checkout.processOrder(700);
	}

}

interface Payment {
	void pay(int amount);
}

class UpiPayment implements Payment {
	
	private String upiId;
	
	UpiPayment(String upiId) {
		this.upiId = upiId;
	}

	public void pay(int amount) {
		System.out.println("Paid Rs" + amount + " using UPI ID :: " + upiId);
	}

}

class CardPayment implements Payment {
	
	private String number;
	
	CardPayment(String number) {
		this.number = number;
	}

	public void pay(int amount) {
		System.out.println("Paid Rs" + amount + " using Card :: " + number);
	}

}

class NetBankingPayment implements Payment {
	
	private String bank;
	
	NetBankingPayment(String bank) {
		this.bank = bank;
	}

	public void pay(int amount) {
		System.out.println("Paid Rs" + amount + " via Net banking :: " + bank);
	}

}

class Checkout {
	
	private Payment payment;
	
	public void setPaymentStrategy(Payment payment) {
		this.payment = payment;
	}
	
	public void processOrder(int amount) {
		payment.pay(amount);
	}
	
}