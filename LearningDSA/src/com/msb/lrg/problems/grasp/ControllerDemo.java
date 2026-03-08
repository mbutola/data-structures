package com.msb.lrg.problems.grasp;

import java.util.ArrayList;
import java.util.List;

import com.msb.lrg.problems.grasp.CreatorDemo.Order;
import com.msb.lrg.problems.grasp.CreatorDemo.OrderItem;
import com.msb.lrg.problems.grasp.CreatorDemo.Product;

/*

GRASP – Controller
	Principle
		Assign responsibility for handling system events (UI / external requests) to a controller object.
		A Controller:
			Represents the system or a use-case
			Coordinates work
			Does NOT contain business logic
	🧩 Problem Statement
		User wants to place an order.
		Actors involved:
			UI / Main class
			Controller
			Domain objects (Order, OrderItem, Product)
	❌ Bad Design (No Controller)
			public class OrderApp {
			
			    public static void main(String[] args) {
			
			        Product laptop = new Product("Laptop", 50_000);
			
			        Order order = new Order();
			        order.addItem(laptop, 1);
			        order.placeOrder(); // ❌ UI directly talks to domain
			    }
			}
		Problems
			UI tightly coupled to domain
			No single entry point for use case
			Hard to change UI/API later
	✅ Good Design (GRASP Controller Applied)
		Why this is a Controller
			✔ Handles system events
			✔ Coordinates domain objects
			✔ No business calculations
			✔ Single entry point

 */
public class ControllerDemo {

	public static void main(String[] args) {
        
		Product laptop = new Product("Laptop", 50_000);
        Product mouse  = new Product("Mouse", 500);

        OrderController controller = new OrderController();

        // UI events
        controller.addItem(laptop, 1);
        controller.addItem(mouse, 2);
        controller.placeOrder();

	}

	static class OrderController {

	    private final Order order = new Order();

	    // System event: "Add Item"
	    void addItem(Product product, int quantity) {
	        order.addItem(product, quantity);
	    }

	    // System event: "Place Order"
	    void placeOrder() {
	        order.place();
	    }
	}
	
	static class Order {
	    private final List<OrderItem> items = new ArrayList<>();

	    void addItem(Product product, int quantity) {
	        items.add(new OrderItem(product, quantity));
	    }

	    double getTotal() {
	        return items.stream()
	                    .mapToDouble(OrderItem::getSubtotal)
	                    .sum();
	    }
	    
	    void place() {
	        System.out.println("Order placed. Total = " + getTotal());
	    }
	}
	
	static class OrderItem {
	    private final Product product;
	    private final int quantity;

	    OrderItem(Product product, int quantity) {
	        this.product = product;
	        this.quantity = quantity;
	    }

	    double getSubtotal() {
	        return product.getPrice() * quantity;
	    }
	}
	
	static class Product {
	    private final String name;
	    private final double price;

	    Product(String name, double price) {
	        this.name = name;
	        this.price = price;
	    }

	    double getPrice() {
	        return price;
	    }
	}

}
