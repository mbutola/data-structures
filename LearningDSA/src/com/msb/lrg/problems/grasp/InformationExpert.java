package com.msb.lrg.problems.grasp;

import java.util.ArrayList;
import java.util.List;

/*

	GRASP – Information Expert (Clear, Practical Example)
		Principle
			Assign responsibility to the class that has the information needed to fulfill it.
		🧩 Problem Statement
			We need to calculate the total price of an order.
		Classes involved:
			Order
			OrderItem
			Product
		❌ Bad Design (Violates Information Expert)
			class OrderService {
			
			    double calculateTotal(Order order) {
			        double total = 0;
			        for (OrderItem item : order.getItems()) {
			            total += item.getProduct().getPrice() * item.getQuantity();
			        }
			        return total;
			    }
			}
		Why this is bad
			OrderService does not own the data
			High coupling to:
				Order
				OrderItem
				Product
			Logic breaks if data structure changes
		✅ Good Design (Follows Information Expert)
			Step 1: Let OrderItem calculate its subtotal
					class OrderItem {
					    private Product product;
					    private int quantity;
					
					    double getSubtotal() {
					        return product.getPrice() * quantity;
					    }
					}
				👉 OrderItem has:
					price
					quantity
				So it’s the information expert for subtotal.
			Step 2: Let Order calculate total
					class Order {
					    private List<OrderItem> items;
					
					    double getTotal() {
					        return items.stream()
					                    .mapToDouble(OrderItem::getSubtotal)
					                    .sum();
					    }
					}
				👉 Order:
				Knows all OrderItems
				So it’s the information expert for total.
	🧠 Responsibility Flow
		Product      → knows price
		OrderItem    → knows quantity + product → subtotal
		Order        → knows items → total
	Each class:
		Uses its own data
		Has minimal knowledge of others	

 */
public class InformationExpert {

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
