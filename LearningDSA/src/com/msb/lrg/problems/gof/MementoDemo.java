package com.msb.lrg.problems.gof;

import java.util.Stack;

/*

	What is the Memento Pattern?
	The Memento Pattern lets you capture and store an object’s internal state so it can be restored later, without exposing the object’s internals.
		In simple words:
			It enables Undo / Rollback functionality.
		Purpose
			Save the state of an object (snapshot)
			Restore an object to a previous state
			Encapsulate state so it’s not exposed to others
	Problem It Solves
		Without memento:
			To implement undo, an object must expose internal fields (breaking encapsulation)
			Or you manually track changes everywhere in your code
	Problems:
			State becomes visible → violates encapsulation
			Undo logic becomes complex
			Hard to manage multiple versions of an object
		Example:
			A text editor wants to support Ctrl + Z
			How to restore exact previous content?
				Memento solves this perfectly.
	How It Solves the Problem
		Memento pattern divides responsibilities:
			Originator
				The object whose state you want to save (e.g., TextEditor)
				Creates a memento containing its state
				Can restore its state from a memento
			Memento
				Stores the internal state
				Immutable
				Does not expose fields to outside world
			Caretaker
				Manages the history of mementos
				Never modifies or inspects mementos
				So state saving and restoring becomes clean and encapsulated.
	Advantages
		Provides Undo / Redo / Rollback
		Maintains encapsulation (no exposure of internal fields)
		Clean separation of roles
		Easy to implement versioning of objects
	Disadvantages
		Can consume high memory if mementos store large state
		Too many mementos → performance issues
		Deep copy logic can be complex
	Real-World Uses
		Text editors (Undo/Redo)
		Graphics editors (history of changes)
		Version control systems
		Database transactions (savepoints, rollback)
		Game checkpoints

 */
public class MementoDemo {

	public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.type("Hello ");
        history.save(editor.save());

        editor.type("World!");
        history.save(editor.save());

        editor.type(" Extra text.");

        System.out.println("Current Content: " + editor.getContent());

        // Undo last change
        editor.restore(history.undo());
        System.out.println("After undo 1: " + editor.getContent());

        // Undo again
        editor.restore(history.undo());
        System.out.println("After undo 2: " + editor.getContent());
	}

}

class EditorMemento {
	private final String content;
	
	EditorMemento(String content) {
		this.content = content;
	}
	
	String getSavedContent() {
		return content;
	}
}

class TextEditor {
	private String content = "";
	
	public void type(String words) {
		this.content += words;
	}
	
	String getContent() {
		return content;
	}

	public EditorMemento save() {
		return new EditorMemento(content);
	}
	
	public void restore(EditorMemento memento) {
		content = memento.getSavedContent();		
	}
}

class History {
	
	private Stack<EditorMemento> history = new Stack<>();
	
	void save(EditorMemento memento) {
		history.push(memento);
	}

	EditorMemento undo() {
		return history.pop();
	}
}
