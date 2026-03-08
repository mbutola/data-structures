package com.msb.lrg.problems.grasp;

/*

GRASP – Low Coupling
	Principle
		Assign responsibilities so that classes have few dependencies and minimal knowledge of other classes.
	Goal:
		Easier change
		Better testability
		Less ripple effect
	🧩 Problem Statement
		We need to process a payment for an order.
		Question:
			👉 Should Order directly depend on a concrete payment implementation?
	❌ Bad Design (Tightly Coupled)
			class Order {
			
			    void pay(double amount) {
			        PaytmPayment payment = new PaytmPayment(); // ❌ concrete dependency
			        payment.makePayment(amount);
			    }
			}
			
			class PaytmPayment {
			    void makePayment(double amount) {
			        System.out.println("Paid via Paytm: " + amount);
			    }
			}
		Problems
			Order depends on Paytm
			Cannot switch to UPI/Card
			Hard to test
			Violates Low Coupling
	✅ Good Design (Low Coupling Applied)
		Why this is low coupling
			✔ Depends on interface, not implementation
			✔ No new of concrete classes
			✔ Easy to extend

 */
public class LowCouplingDemo {

	public static void main(String[] args) {
        Order order = new Order();

        Payment paytm = new PaytmPayment();
        Payment card  = new CardPayment();

        order.pay(5000, paytm);  // switch payment method
        order.pay(3000, card);
	}
	
	static class Order {
	    void pay(double amount, Payment payment) {
	        payment.pay(amount); // depends only on abstraction
	    }
	}
	
	static interface Payment {
	    void pay(double amount);
	}

	static class PaytmPayment implements Payment {
	    public void pay(double amount) {
	        System.out.println("Paid via Paytm: " + amount);
	    }
	}

	static class CardPayment implements Payment {
	    public void pay(double amount) {
	        System.out.println("Paid via Card: " + amount);
	    }
	}
}
