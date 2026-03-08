package com.msb.lrg.problems.grasp;

import java.util.ArrayList;
import java.util.List;

/*

GRASP – Pure Fabrication
	Principle
		Assign responsibility to an artificial class (not part of the domain model)
		to achieve low coupling and high cohesion.
	🧩 Problem Statement
		We want to save an Order to the database.
		Question:
			👉 Should Order know how to talk to the database?
	❌ Bad Design (No Pure Fabrication)
			class Order {
			
			    void saveToDatabase() {
			        // ❌ DB logic inside domain
			        System.out.println("INSERT INTO orders ...");
			    }
			}
		Problems
			Domain polluted with DB logic
			Low cohesion
			High coupling
			Hard to change DB
	✅ Good Design (Pure Fabrication Applied)
		We create an artificial class:
			👉 OrderRepository
		This class:
			Does not represent a real-world concept
			Exists only to improve design

 */
public class PureFabrication {

	public static void main(String[] args) {
        Order order = new Order();
        order.addItem(1000);
        order.addItem(2500);

        OrderService service = new OrderService();
        service.placeOrder(order);
	}
	
	static class OrderService {

	    private final OrderRepository repository = new OrderRepository();

	    void placeOrder(Order order) {
	        repository.save(order);
	    }
	}
	
	static class OrderRepository {

	    void save(Order order) {
	        // Artificial class — DB logic here
	        System.out.println("Order saved to database. Total = " + order.getTotal());
	    }
	}
	
	static class Order {

	    private final List<Double> prices = new ArrayList<>();

	    void addItem(double price) {
	        prices.add(price);
	    }

	    double getTotal() {
	        return prices.stream()
	                     .mapToDouble(Double::doubleValue)
	                     .sum();
	    }
	}

}
