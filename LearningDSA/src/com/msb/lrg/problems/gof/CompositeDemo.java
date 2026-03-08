package com.msb.lrg.problems.gof;

import java.util.ArrayList;
import java.util.List;

/*
 	Composite Pattern (Structural Pattern)
		The Composite Pattern lets you treat individual objects and groups of objects uniformly.
		It structures objects into tree-like hierarchies (part–whole relationships).
	Purpose
		To represent hierarchical structures (tree structures).
		To allow clients to treat leaf objects and composite objects the same way.
		To simplify client code by introducing a common interface for everything.
	Problem It Solves
		Imagine you have a file system:
			Files
			Folders (contain files or other folders)
		Without Composite:
			You need separate logic for single objects (File)
			And separate logic for collections (Folder)
			Client code becomes full of if object is folder...else...
			This becomes messy and unmaintainable.
	How Composite Solves It
		Composite Pattern introduces:
			A common interface for both “Leaf” and “Composite”
			Component
				- Leaf
				- Composite
			Composite contains a list of Components
				List<Component>
			This allows you to:
				Call the same method (show()) on File or Folder.
				Recursively build and operate on tree structures.
	Advantages
		| Benefit                           | Explanation                                            |
		| --------------------------------- | ------------------------------------------------------ |
		| **Uniformity**                    | Treat single objects & groups the same way             |
		| **Simplifies client code**        | No need for type-checking (`instanceof`)               |
		| **Supports recursive structures** | Directly models tree-like data                         |
		| **Easy to extend**                | Add new leaf/composite without modifying existing code |
	Disadvantages
		| Drawback                                         | Why                                                                        |
		| ------------------------------------------------ | -------------------------------------------------------------------------- |
		| **Harder to restrict invalid combinations**      | e.g., preventing certain leaves from being inside certain composites       |
		| **Can become too generic**                       | All objects share the same interface even if some methods don’t make sense |
		| **Debugging recursive structures may be harder** | Deep trees require careful traversal                                       |

 */
public class CompositeDemo {

	public static void main(String[] args) {
        FileLeaf file1 = new FileLeaf("resume.pdf");
        FileLeaf file2 = new FileLeaf("profile.jpg");
        FileLeaf file3 = new FileLeaf("report.docx");

        // Folder composite
        FolderComposite documents = new FolderComposite("Documents");
        FolderComposite photos = new FolderComposite("Photos");

        // Build tree structure
        documents.add(file1);
        documents.add(file3);
        photos.add(file2);

        FolderComposite root = new FolderComposite("Home");
        root.add(documents);
        root.add(photos);

        // Display everything
        root.show();
	}

}

interface FileSystemComponent{
	void show();
}

class FileLeaf implements FileSystemComponent {
	
	private String name;
	
	FileLeaf(String name){
		this.name = name;
	}
	
	public void show() {
		System.out.println("File :: " + name);
	}

}

class FolderComposite implements FileSystemComponent {

	private String name;
	private List<FileSystemComponent> childrens = new ArrayList<>();
	
	FolderComposite(String name){
		this.name = name;
	}
	
	public void add(FileSystemComponent component) {
		childrens.add(component);
	}
	
	public void remove(FileSystemComponent component) {
		childrens.remove(component);
	}
	
	public void show() {
		System.out.println("Folder :: " + name);
		for(FileSystemComponent component : childrens) {
			component.show();
		}
	}
	
}