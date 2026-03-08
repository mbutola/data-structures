package com.msb.lrg.problems.gof;

public class ProxyDemo {

	public static void main(String[] args) {
        Image img = new ImageProxy("high_res_pic.jpg");

        System.out.println("Image created. Not loaded yet.");

        img.display();  // first time → loads
        img.display();  // second time → reused
	}

}

interface Image {
	void display();
}

class RealImage implements Image {
	
	private String fileName;
	
	RealImage(String fileName){
		this.fileName = fileName;
		loadImage(fileName);
	}
	
	private void loadImage(String fileName) {
		System.out.println("Loading image :: " + fileName);
	}
	
	public void display() {
		System.out.println("Displaying image :: " + fileName);
	}
}

class ImageProxy implements Image {
	
	private RealImage image;
	private String fileName;
	
	ImageProxy(String fileName){
		this.fileName = fileName;
	}
	
	public void display() {
		if(image == null) {
			image = new RealImage(fileName);
		}
		image.display();
	}
}