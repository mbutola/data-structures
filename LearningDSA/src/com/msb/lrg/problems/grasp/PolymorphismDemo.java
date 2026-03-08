package com.msb.lrg.problems.grasp;

/*
 
GRASP – Polymorphism
	Principle
		When behavior varies by type, assign responsibility to the different types using polymorphism instead of if / switch.
	🧩 Problem Statement
		We must calculate shipping cost based on shipping type:
			Regular
			Express
			International
	❌ Bad Design (Conditionals Everywhere)
			class ShippingService {
			
			    double calculate(double weight, String type) {
			        if (type.equals("REGULAR")) {
			            return weight * 10;
			        } else if (type.equals("EXPRESS")) {
			            return weight * 20;
			        } else if (type.equals("INTERNATIONAL")) {
			            return weight * 50;
			        }
			        throw new IllegalArgumentException();
			    }
			}
		Problems
			Hard to extend
			Violates Open/Closed
			Violates Polymorphism
	✅ Good Design (GRASP Polymorphism Applied)
		Order (Uses Polymorphism)
			✔ No if
			✔ Depends only on abstraction
 
 */
public class PolymorphismDemo {

	public static void main(String[] args) {
        Order regularOrder =
                new Order(new RegularShipping());

        Order expressOrder =
                new Order(new ExpressShipping());

        Order internationalOrder =
                new Order(new InternationalShipping());

        System.out.println("Regular: " +
                regularOrder.getShippingCost(5));

        System.out.println("Express: " +
                expressOrder.getShippingCost(5));

        System.out.println("International: " +
                internationalOrder.getShippingCost(5));
	}
	
	static class Order {

	    private final Shipping shipping;

	    Order(Shipping shipping) {
	        this.shipping = shipping;
	    }

	    double getShippingCost(double weight) {
	        return shipping.calculateCost(weight);
	    }
	}
	
	static interface Shipping {
	    double calculateCost(double weight);
	}
	
	static class RegularShipping implements Shipping {
	    public double calculateCost(double weight) {
	        return weight * 10;
	    }
	}

	static class ExpressShipping implements Shipping {
	    public double calculateCost(double weight) {
	        return weight * 20;
	    }
	}

	static class InternationalShipping implements Shipping {
	    public double calculateCost(double weight) {
	        return weight * 50;
	    }
	}

}
