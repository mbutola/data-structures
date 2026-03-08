package com.msb.lrg.problems.grasp;

import java.util.ArrayList;
import java.util.List;

import com.msb.lrg.problems.grasp.InformationExpert.Order;
import com.msb.lrg.problems.grasp.InformationExpert.OrderItem;
import com.msb.lrg.problems.grasp.InformationExpert.Product;

/*

GRASP – Creator
	Principle
		Assign the responsibility of creating an object to the class that:
			contains it, or
			aggregates it, or
			closely uses it, or
			has the data needed to create it
	🧩 Problem Statement
		We want to create OrderItem objects.
		Classes:
			Order
			OrderItem
			Product
			Main class
	Question:
		👉 Who should create OrderItem?
	❌ Bad Design (Violates Creator)
			public class OrderApp {
			
			    public static void main(String[] args) {
			
			        Product phone = new Product("Phone", 20_000);
			
			        // ❌ Main creates OrderItem directly
			        OrderItem item = new OrderItem(phone, 2);
			
			        Order order = new Order();
			        order.addItem(item);
			    }
			}
		Why this is bad
			main now knows:
				how OrderItem is constructed
				what data it needs
				High coupling
				Harder to change construction logic
	✅ Good Design (Follows GRASP Creator)
		Why Order is the Creator
			✔ Contains OrderItem
			✔ Closely uses OrderItem
			✔ Has data required to create it

 */
public class CreatorDemo {

	public static void main(String[] args) {
        
		Product laptop = new Product("Laptop", 50_000);
        Product mouse  = new Product("Mouse", 500);

        Order order = new Order();
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        double total = order.getTotal();

        System.out.println("Order total = " + total);

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
