package com.msb.lrg.problems.grasp;

/*
 
GRASP – Protected Variations
	Principle
		Identify points of variation (things likely to change) and
		encapsulate them behind stable interfaces.
	Goal:
		Minimize impact of change
		Avoid ripple effects
		Make the system resilient to future changes
	🧩 Problem Statement
		We need to calculate tax for an order.
		Tax rules may change by:
			Country
			State
			Year
			Business policy
	Question:
		👉 Should Order hard-code tax logic?
	❌ Bad Design (Not Protected)
			class Order {
			
			    double calculateTotal(double amount, String country) {
			
			        if (country.equals("INDIA")) {
			            return amount + amount * 0.18;
			        } else if (country.equals("USA")) {
			            return amount + amount * 0.10;
			        }
			        return amount;
			    }
			}
		Problems
			Every tax change → modify Order
			Violates Open/Closed Principle
			Not protected from variation
	✅ Good Design (GRASP Protected Variations Applied)
		We identify the variation point:
			👉 Tax calculation
				We protect it using an interface.
 
 */
public class ProtectedVariations {

	public static void main(String[] args) {
        double amount = 10_000;

        Order indiaOrder = new Order(new IndiaTaxCalculator());
        Order usaOrder = new Order(new USATaxCalculator());

        System.out.println("India total = " + indiaOrder.getTotal(amount));
        System.out.println("USA total = " + usaOrder.getTotal(amount));
	}
	
	static interface TaxCalculator {
	    double calculateTax(double amount);
	}
	
	static class IndiaTaxCalculator implements TaxCalculator {
	    public double calculateTax(double amount) {
	        return amount * 0.18;
	    }
	}

	static class USATaxCalculator implements TaxCalculator {
	    public double calculateTax(double amount) {
	        return amount * 0.10;
	    }
	}
	
	static class Order {

	    private final TaxCalculator taxCalculator;

	    Order(TaxCalculator taxCalculator) {
	        this.taxCalculator = taxCalculator;
	    }

	    double getTotal(double amount) {
	        return amount + taxCalculator.calculateTax(amount);
	    }
	}

}
