package com.msb.lrg.problems.gof;

/*

	What is the Template Method Pattern?
		Template Method Pattern defines the skeleton of an algorithm in a base class but lets 
		subclasses override specific steps without changing the algorithm structure.
	Purpose
		To enforce a fixed sequence of operations.
		To allow subclasses to override some steps, not the whole logic.
		To avoid code duplication when many classes follow the same workflow.
	Problem It Solves
		Consider different types of data parsers:
				JSON parser
				XML parser
				CSV parser
			All have SAME workflow:
				Read file
				Parse data
				Validate parsed data
				Save results
			If you implement this in each parser separately → duplicate code + violates DRY.
	How the Template Pattern Solves It
			You define:
				An abstract "template method"
					→ contains the full algorithm steps in order.
				Abstract or hook methods
					→ subclasses override only the parts they need.
			Thus:
				Algorithm structure stays fixed
				Custom steps vary per subclass
	Advantages
		Avoids duplicate code
		Common workflow lives in base class.
		Enforces a standard algorithm
		Every subclass must follow same sequence.
		Subclasses override specific steps only
		Easy extension.
		Enhances maintainability
		Any workflow change happens in one place.
	Disadvantages
		Inheritance-based → not flexible as composition
		Can’t swap behavior at runtime like Strategy Pattern.
		Too many abstract methods may complicate subclasses
		Subclasses may be forced to implement unnecessary steps.
		Hard to understand if hierarchy becomes deep

 */
public class TemplateDemo {

	public static void main(String[] args) {
        DataParser json = new JsonParser();
        json.process();

        System.out.println();

        DataParser xml = new XmlParser();
        xml.process();
	}

}

abstract class DataParser {
	
	public void process() {
		loadFile();
		parseData();
		validateData();
		saveResults();
	}

	void loadFile() {
		System.out.println("Loading file ...");
	}
	
	abstract void parseData();
	abstract void validateData();
	
	void saveResults() {
		System.out.println("Saving parsed data ...");
	}

}

class JsonParser extends DataParser {
	void parseData() {
		System.out.println("Parsing JSON data ...");
	}
	
	void validateData() {
		System.out.println("Validating JSON schema ...");
	}
}

class XmlParser extends DataParser {
	void parseData() {
		System.out.println("Parsing XML data ...");
	}
	
	void validateData() {
		System.out.println("Validating XML schema ...");
	}
}
