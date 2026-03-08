package com.msb.lrg.problems.solid;

/*
 	What is Single Responsibility Principle (SRP)?
	SRP says:
		A class should have only ONE reason to change.
			(i.e., only one responsibility or job)
		It is the S in SOLID principles.
	Purpose
		To make classes:
			focused
			easy to maintain
			easy to test
			less coupled
	Problem SRP Solves
		Without SRP, a class often becomes:
			Big
			Hard to understand
			Hard to change
			Hard to test
			Doing many unrelated tasks
			For example, imagine a class Invoice that:
				calculates invoice amount
				prints invoice
				saves invoice to DB
				sends invoice email
			Now a change in printing should not affect calculations or DB logic.
			This leads to Fragile and God Classes.
	How SRP Solves It
		SRP says:
			Break each responsibility into its own class
			Each class handles only one concern
			If business logic changes, only that class changes
			Other parts of system remain untouched
			This reduces coupling and improves clarity.
	Advantages
		1. Easy to maintain
			Only one reason for change.
		2. Less coupling
			Classes remain independent.
		3. Better unit testing
			Small classes → easy to mock/test.
		4. Higher reusability
			Each class does a single focused task.
		5. Avoids “God Object” anti-pattern
			No class becomes huge and unmanageable.
	Disadvantages
		1. Many small classes
			Leads to increased number of files.
		2. Requires good judgment
			Sometimes responsibilities are not clearly identifiable.
		3. Over-engineering risk
			Beginners may split too much.
	Java Example — Invoice with SRP
		Bad Example — Violates SRP
			public class Invoice {
			    
			    public double calculateTotal() {
			        // Logic to calculate total
			        return 1000;
			    }
			
			    public void saveToDatabase() {
			        System.out.println("Saving invoice to DB...");
			    }
			
			    public void printInvoice() {
			        System.out.println("Printing invoice...");
			    }
			
			    public void sendEmail() {
			        System.out.println("Sending invoice email...");
			    }
			}
	This class has 4 responsibilities:
		business logic
		persistence
		printing
		emailing
 	
 */
public class SRPDemo {

	public static void main(String[] args) {
        Invoice invoice = new Invoice();

        InvoiceRepository repo = new InvoiceRepository();
        InvoicePrinter printer = new InvoicePrinter();
        InvoiceEmailer emailer = new InvoiceEmailer();

        repo.save(invoice);
        printer.print(invoice);
        emailer.send(invoice);
	}

}

// Invoice calculation (business logic only)
class Invoice {
    public double calculateTotal() {
        return 1000;
    }	
}

// Persistence responsibility
class InvoiceRepository {
	public void save(Invoice invoice) {
		System.out.println("Saving invoice to DB...");
	}
}

// Printing responsibility

class InvoicePrinter {
	public void print(Invoice invoice) {
		System.out.println("Printing invoice...");
	}
}

// Email responsibility
class InvoiceEmailer {
	public void send(Invoice invoice) {
		System.out.println("Sending invoice email...");
	}
}