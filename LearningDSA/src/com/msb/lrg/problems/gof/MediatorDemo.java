package com.msb.lrg.problems.gof;

import java.time.LocalDateTime;

/*
 	What is the Mediator Pattern?
		The Mediator Pattern defines an object that encapsulates how a set of objects interact.
		Instead of objects talking directly to each other, they talk through a mediator.
		It reduces dependencies between communicating classes (colleagues).
	Purpose
		Centralize complex communication
		Reduce coupling between classes
		Make interaction logic reusable and easier to maintain
	Problem It Solves
		Without a mediator:
			Many objects talk directly to each other
		This creates a mesh network of connections:
			A <-> B
			A <-> C
			B <-> C
			C <-> D
			...
		This leads to:
			Tight coupling
			Hard to modify communication
			Difficult to add new objects
			Code becomes unmanageable
	How Mediator Solves the Problem
		Instead of objects communicating directly:
			Each object (called Colleague) communicates only with the Mediator
			Mediator forwards the message appropriately
			A  →  
			      Mediator → B  
			                → C  
			C  →  
		This:
			Removes direct dependencies
			Centralizes interaction logic
			Makes colleagues simple, reusable components
	Advantages
		Decoupled classes
		Objects no longer depend on each other’s interfaces.
		Centralized communication logic
		Easy to change or extend interaction.
		Easy to add new components
		Just register with the mediator—no rewiring.
		Promotes single responsibility
		Colleagues focus on their own work; mediator handles interactions.
	Disadvantages
		Mediator can become God object
		If too many rules inside mediator → becomes complex.
		Hard to maintain if used incorrectly
		All communication in one place → can grow big.
	Real-World Examples
		Aviation control tower (ATC)
			Planes don’t talk to each other—every plane talks to the tower.
		GUI frameworks
			Buttons, text fields, and dropdowns interact through a mediator.
		Chat applications
			Servers act as mediators for clients.
 	
 */
public class MediatorDemo {

	public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();

        ChatUser user1 = new BasicUser(chat, "Alice");
        ChatUser user2 = new BasicUser(chat, "Bob");

        user1.send("Hi Bob!");
        user2.send("Hello Alice!");
	}

}

interface ChatMediator {
	void sendMessage(String message, ChatUser user);
}

class ChatRoom implements ChatMediator {
	
	public void sendMessage(String message, ChatUser user) {
		System.out .println(LocalDateTime.now() + " [" + user.getName() + "] : " + message);
	}
}

abstract class ChatUser {
	
	protected ChatMediator mediator;
	protected String name;
	
	ChatUser(ChatMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public abstract void send(String message);

	String getName() {
		return name;
	}
	
}

class BasicUser extends ChatUser {
	
	BasicUser(ChatMediator mediator, String name) {
		super(mediator, name);
	}
	
	public void send(String message) {
		mediator.sendMessage(message, this);
	}
	
}
