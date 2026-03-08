package com.msb.lrg.problems.grasp;

/*

GRASP – Indirection
	Principle
		Assign responsibility to an intermediate object to decouple two other objects that would otherwise be directly dependent.
	Used when:
		One class should not depend on another directly
		External systems / volatile APIs are involved
	🧩 Problem Statement
		Order needs to send a notification.
	Question:
		👉 Should Order directly depend on Email / SMS / WhatsApp SDKs?
	❌ Bad Design (No Indirection)
			class Order {
			
			    void notifyCustomer(String message) {
			        EmailSender sender = new EmailSender(); // ❌ direct dependency
			        sender.sendEmail(message);
			    }
			}
			
			class EmailSender {
			    void sendEmail(String msg) {
			        System.out.println("Email sent: " + msg);
			    }
			}
		Problems
			Tight coupling
			Hard to switch notification channel
			Hard to test
	✅ Good Design (GRASP Indirection Applied)
		We introduce an intermediate object:
			👉 NotificationService
				✔ Acts as a buffer / middleman
				✔ Decouples Order from concrete channels

 */
public class IndirectionDemo {

    public static void main(String[] args) {

        NotificationChannel email = new EmailNotification();
        NotificationService service = new NotificationService(email);

        Order order = new Order(service);
        order.place();
    }
    
    static interface NotificationChannel {
        void send(String message);
    }
    
    static class EmailNotification implements NotificationChannel {
        public void send(String message) {
            System.out.println("Email sent: " + message);
        }
    }

    static class SMSNotification implements NotificationChannel {
        public void send(String message) {
            System.out.println("SMS sent: " + message);
        }
    }
	
    static class NotificationService {

        private final NotificationChannel channel;

        NotificationService(NotificationChannel channel) {
            this.channel = channel;
        }

        void notify(String message) {
            channel.send(message);
        }
    }
    
    static class Order {

        private final NotificationService notificationService;

        Order(NotificationService notificationService) {
            this.notificationService = notificationService;
        }

        void place() {
            System.out.println("Order placed");
            notificationService.notify("Your order is placed!");
        }
    }
}
