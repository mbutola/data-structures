package com.msb.lrg.problems.solid;

/*
	D — Dependency Inversion Principle (DIP)
	Definition
		High-level modules should not depend on low-level modules.
		Both should depend on abstractions.
		Abstractions should not depend on details.
		Details should depend on abstractions.
		DIP is the most powerful principle in SOLID because it eliminates tight coupling.
	Purpose
		To decouple your high-level business logic from low-level implementation details, so:
			Code becomes flexible
			You can replace database, logger, notification system, etc., without changing core logic
			Unit testing becomes easy (mock dependencies)
	Problem It Solves
		Without DIP (tight coupling):
			High-level modules directly create and use low-level classes:
				class NotificationService {
				    EmailSender email = new EmailSender(); // tightly coupled
				    void send(String msg) {
				        email.sendEmail(msg);
				    }
				}
	Problems:
		Hard to replace Email with SMS or WhatsApp
		Hard to test (Email sender actually runs)
		Change in low-level class breaks high-level class
		Violates Open/Closed Principle (OCP)
	How DIP Solves It
		Introduce an abstraction (interface)
		High-level module depends on the interface, not the implementation.
		Low-level classes implement the interface.
		Use Constructor Injection / Setter Injection
		So concrete implementations are passed in at runtime.
		This means:
			High-level code is stable
			Low-level code is pluggable
	Advantages
		| Advantage                          | Explanation                                           |
		| ---------------------------------- | ----------------------------------------------------- |
		| Low coupling                       | High-level logic doesn’t care about implementation    |
		| Easy to extend                     | Switch Email → SMS → Push easily                      |
		| Testability                        | You can inject mocks                                  |
		| Fewer ripple effects               | Changing low-level code doesn't touch high-level code |
		| Works perfectly with DI frameworks | Spring, Guice, Micronaut                              |
	Disadvantages
		| Disadvantage          | Explanation                                 |
		| --------------------- | ------------------------------------------- |
		| More boilerplate      | Need interfaces + implementation            |
		| More design upfront   | Requires understanding of abstractions      |
		| Over-abstraction risk | Too many unnecessary interfaces if overused |
	Java Example (Simple and Clear)
		Without DIP (Bad Design)
			class EmailSender {
			    public void sendEmail(String msg) {
			        System.out.println("Sending Email: " + msg);
			    }
			}
			
			class NotificationService {
			    private EmailSender emailSender = new EmailSender(); // direct dependency
			
			    public void notifyUser(String msg) {
			        emailSender.sendEmail(msg);
			    }
			}
		Issues:
			NotificationService is tightly coupled to EmailSender — cannot switch to SMS easily.
	With DIP (Good Design)
		What we Achieved
			NotificationService does NOT know:
			whether it uses email, SMS, WhatsApp, push notification
			how the message is transported
			any low-level implementation details
			Changing implementation does NOT change business logic.
			Perfect DIP.

 */
public class DIPDemo {

	public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();

        NotificationService service1 = new NotificationService(email);
        service1.notifyUser("Welcome via Email!");

        NotificationService service2 = new NotificationService(sms);
        service2.notifyUser("Welcome via SMS!");
	}

}

interface MessageSender {
	void send(String message);
}

class EmailSender implements MessageSender {
	public void send(String message) {
		System.out.println("Email :: " + message);
	}
}

class SmsSender implements MessageSender {
	public void send(String message) {
		System.out.println("SMS :: " + message);
	}
}

class NotificationService {
	
	private MessageSender sender;
	
	NotificationService(MessageSender sender) {
		this.sender = sender;
	}
	
	public void notifyUser(String message) {
		sender.send(message);
	}
}
