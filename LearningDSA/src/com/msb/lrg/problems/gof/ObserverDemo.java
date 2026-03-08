package com.msb.lrg.problems.gof;

import java.util.ArrayList;
import java.util.List;

/*

	What is the Observer Pattern?
	The Observer Pattern defines a one-to-many relationship between objects so that when one object changes state, all its dependents (observers) are notified automatically.
		In simple words:
			It is a Publish–Subscribe pattern.
			One object (Subject) broadcasts updates, and many Observers listen.
	Purpose
		Notify multiple objects about a state change
		Implement event-driven behavior
		Decouple publisher and subscribers
	Problem It Solves
		Without Observer:
			A subject must manually call update() on every dependent class
			This leads to tight coupling
			Hard to add/remove listeners
			Hard to maintain multiple dependent objects
		Example:
			A stock price changes → all dashboards, alerts, graphs must update.
			Without a pattern, this becomes messy.
	How It Solves the Problem
		Observer pattern uses:
			Subject (Publisher)
			Maintains list of observers
			Notifies observers when state changes
		Observer (Subscriber)
			Has an update() method
			Reacts to changes
			Loose Coupling
			Subject does not know observer details
			Observers subscribe/unsubscribe dynamically
	Advantages
		Decouples publisher and subscribers
		Easy to add/remove observers at runtime
		Automatic notification of state change
		Ideal for event-driven systems
	Disadvantages
		Can cause performance issues with many observers
		Notification order is not guaranteed
		Hard to debug when there are too many chained updates
		Risk of memory leaks if observers are not removed
	Real-World Examples
		GUI frameworks
			Buttons → onClick listeners
			Dropdown → onChange listeners
		Stock market alerts
			Investors subscribed to price changes
		Pub/Sub systems
			Kafka, Redis PubSub, RabbitMQ
		Event handlers
			File watchers, keyboard events, mouse events
		Reactive programming
			RxJava, Reactor
 
 */
public class ObserverDemo {

	public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer investor1 = new EmailInvestor("Alice");
        Observer investor2 = new MobileInvestor("Bob");

        market.attach(investor1);
        market.attach(investor2);

        System.out.println("Setting price to 101.5");
        market.setPrice(101.5f);

        System.out.println("Setting price to 103.0");
        market.setPrice(103.0f);

        // Bob unsubscribes
        market.detach(investor2);

        System.out.println("Setting price to 99.0");
        market.setPrice(99.0f);
	}

}

interface Observer {
	void update(float price);
}

class EmailInvestor implements Observer {
	
	private String name;
	
	EmailInvestor(String name) {
		this.name = name;
	}
	
	public void update(float price) {
		System.out.println(name + "(Email) notified. New Price :: " + price);
	}
}

class MobileInvestor implements Observer {
	private String name;
	
	MobileInvestor(String name) {
		this.name = name;
	}
	
	public void update(float price) {
		System.out.println(name + "(Mobile) notified. New Price :: " + price);
	}
}

interface Subject {
	void attach(Observer o);
	void detach(Observer o);
	void notifyObservers();
}

class StockMarket implements Subject {
	
	private List<Observer> observers = new ArrayList<>(); 
	private float stockPrice;
	
	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void detach(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		for(Observer o: observers)
			o.update(stockPrice);
	}
	
	public void setPrice(float price) {
		this.stockPrice = price;
		notifyObservers();
	}

}
