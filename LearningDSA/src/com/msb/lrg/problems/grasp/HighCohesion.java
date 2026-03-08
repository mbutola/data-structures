package com.msb.lrg.problems.grasp;

import java.util.ArrayList;
import java.util.List;

/*

GRASP – High Cohesion
	Principle
		Assign responsibilities so that each class has a small, well-defined set of related responsibilities.
	High cohesion means:
		One reason to change
		Clear purpose
		Easy maintenance
	🧩 Problem Statement
		We are building an order system that must:
			Calculate total
			Save order
			Send confirmation email
	Question:
		👉 Should one class do everything?
	❌ Bad Design (Low Cohesion – God Class)
			class OrderManager {
			
			    void calculateTotal() { }
			    void saveOrder() { }
			    void sendEmail() { }
			    void connectToDatabase() { }
			}
		Problems
			Too many responsibilities
			Hard to change
			Hard to test
			Violates High Cohesion
	✅ Good Design (High Cohesion Applied)
		Each class has one clear job.
		Order (Business Logic Only)
			✔ Responsible only for order data & calculations
		OrderRepository (Persistence Only)
			✔ Responsible only for saving data
		EmailService (Communication Only)
			✔ Responsible only for email
		OrderService (Coordinator)
			✔ Coordinates
			✔ No heavy logic
			✔ High cohesion preserved

 */
public class HighCohesion {

	public static void main(String[] args) {
        Order order = new Order();
        order.addItem(1000);
        order.addItem(2000);

        OrderService service = new OrderService();
        service.placeOrder(order);
	}

	static class OrderService {

	    private final OrderRepository repository = new OrderRepository();
	    private final EmailService emailService = new EmailService();

	    void placeOrder(Order order) {
	        repository.save(order);
	        emailService.sendConfirmation(order);
	        System.out.println("Order total = " + order.getTotal());
	    }
	}
	
	static class EmailService {

	    void sendConfirmation(Order order) {
	        System.out.println("Order confirmation email sent");
	    }
	}
	
	static class OrderRepository {

	    void save(Order order) {
	        System.out.println("Order saved to database");
	    }
	}
	
	static class Order {

	    private final List<Double> prices = new ArrayList<>();

	    void addItem(double price) {
	        prices.add(price);
	    }

	    double getTotal() {
	        return prices.stream().mapToDouble(Double::doubleValue).sum();
	    }
	}
}
