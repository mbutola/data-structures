package com.msb.lrg.problems.design;

import java.util.*;

/* ================= SHOPPING CART ================= */
public class N1ShoppingCartDesign {
	
	static class ShoppingCart {

	    private Map<Integer, CartItem> cart = new HashMap<>();
	    private PricingStrategy strategy;
	
	    public ShoppingCart(PricingStrategy strategy) {
	        this.strategy = strategy;
	    }
	
	    /* Add item */
	    public void addItem(Product product, int qty) {
	
	        if (cart.containsKey(product.id)) {
	            cart.get(product.id).quantity += qty;
	        } else {
	            cart.put(product.id, new CartItem(product, qty));
	        }
	    }
	
	    /* Remove item */
	    public void removeItem(Product product) {
	        cart.remove(product.id);
	    }
	
	    /* Update quantity */
	    public void updateQuantity(Product product, int qty) {
	        if (cart.containsKey(product.id)) {
	            cart.get(product.id).quantity = qty;
	        }
	    }
	
	    /* Calculate total */
	    public double getTotal() {
	        double total = 0;
	
	        for (CartItem item : cart.values()) {
	            total += item.getTotalPrice();
	        }
	
	        return strategy.apply(total);
	    }
	}

    /* ================= PRODUCT ================= */
    static class Product {
        int id;
        String name;
        double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    /* ================= PRICING STRATEGY ================= */
    static interface PricingStrategy {
        double apply(double total);
    }

    /* ================= CART ITEM ================= */
    static class CartItem {
        Product product;
        int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return product.price * quantity;
        }
    }

    /* Example: 10% discount */
    static class PercentageDiscount implements PricingStrategy {
        public double apply(double total) {
            return total * 0.9;
        }
    }

    /* ================= MAIN ================= */
    public static void main(String[] args) {

        Product p1 = new Product(1, "Laptop", 1000);
        Product p2 = new Product(2, "Mouse", 50);

        ShoppingCart cart = new ShoppingCart(new PercentageDiscount());

        cart.addItem(p1, 1);
        cart.addItem(p2, 2);

        System.out.println(cart.getTotal()); // discounted total
    }
}

