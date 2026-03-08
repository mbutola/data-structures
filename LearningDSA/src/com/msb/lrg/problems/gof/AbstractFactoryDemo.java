package com.msb.lrg.problems.gof;

/*
	What is Abstract Factory?
		The Abstract Factory Pattern provides an interface to create families of related or dependent objects without specifying their concrete classes.
		It’s basically a factory of factories.

	Purpose (Intent)
		To encapsulate the creation of related objects that belong to a common theme or family.
		Ensure that products from the same family are compatible with each other.
		Allow adding new families without changing client code.

	Problem It Solves
		Let’s say you’re building a UI Framework that supports multiple themes —
		ike Light Mode and Dark Mode.
		You have multiple related UI elements:
			Button
			Textbox
			Checkbox
		Each theme has its own versions of these elements (e.g., LightButton, DarkButton).
		Without a factory, you’d need code like:
			if(theme.equals("DARK")) 
			    button = new DarkButton();
			else 
			    button = new LightButton();
		This becomes messy and hard to maintain when:
			You add a new theme (new if blocks everywhere).
			You add new components (button, checkbox, textbox, etc.).
			You must ensure matching family compatibility (e.g., LightButton shouldn’t mix with DarkTextbox).

	How Abstract Factory Solves It
		Defines an abstract factory interface that declares creation methods for each related type (button, textbox, checkbox).
		Each concrete factory (e.g., DarkThemeFactory, LightThemeFactory) implements this interface to produce consistent families of products.
		Thus, the client just asks the factory for objects —
		it never directly uses new or knows the concrete classes.
 */
public class AbstractFactoryDemo {

	public static void main(String[] args) {
		GUIFactory factory = new MacFactory();
		factory.createButton().paint();
		factory.createCheckBox().check();;
	}

}

interface Button { void paint();}
interface CheckBox { void check();}

class WinButton implements Button { public void paint() {System.out.println("Windows Button ...");}}
class WinCheckBox implements CheckBox { public void check() {System.out.println("Windows CheckBox ...");}}
class MacButton implements Button { public void paint() {System.out.println("Mac Button ...");}}
class MacCheckBox implements CheckBox { public void check() {System.out.println("Mac CheckBox ...");}}

interface GUIFactory {
	Button createButton();
	CheckBox createCheckBox();
}

class WinFactory implements GUIFactory {
	public Button createButton() { return new WinButton(); }
	public CheckBox createCheckBox() { return new WinCheckBox(); }
}

class MacFactory implements GUIFactory {
	public Button createButton() { return new MacButton(); }
	public CheckBox createCheckBox() { return new MacCheckBox(); }
}
