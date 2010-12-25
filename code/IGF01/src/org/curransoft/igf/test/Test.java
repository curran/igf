package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;
import org.curransoft.igf.IGFJava2D;
import org.curransoft.igf.IGFOpenGL;

public class Test implements IGFApplication {

	public static void main(String[] args) {
		Test application = new Test();
		
		IGF gJava = new IGFJava2D(application);
		gJava.showFrame("Hello Java2D", 0, 0, 500, 500);

		IGF gOGL = new IGFOpenGL(application);
		gOGL.showFrame("Hello OpenGL", 500, 0, 500, 500);
	}

	public void setup(IGF g) {
		System.out.println("in setup");
	}

	public void draw(IGF g) {
		g.background(255);
		double w = g.getWidth();
		double h = g.getHeight();
		double x1 = 0,y1 = 0,x2 = w,y2 = h;
		double lineWidth = 2;
		g.fill(0);
		g.line(x1, y1, x2, y2, lineWidth);
	}

	public void pointPressed(IGF g, int id, double x, double y) {
		System.out.println("in pointPressed");
	}

	public void pointDragged(IGF g, int id, double x, double y, double px,
			double py) {
		System.out.println("in pointDragged");
	}

	public void pointReleased(IGF g, int id, double x, double y) {
		System.out.println("in pointReleased");
	}
	
	public void keyPressed(IGF g) {
		System.out.println("in keyPressed");
	}

	public void keyReleased(IGF g) {
		System.out.println("in keyReleased");
	}

	public void keyTyped(IGF g) {
		System.out.println("in keyTyped");
	}

	public boolean exit() {
		return true;
	}
}
