package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;
import org.curransoft.igf.IGFKey;

/**
 * A basic test demonstrating that an application can have a resizable window.
 * 
 * @author curran
 * 
 */
public class Test00Resize implements IGFApplication {

	public static void main(String[] args) {
		TestUtils.testApplication(new Test00Resize(),new Test00Resize());
	}

	public void setup(IGF g) {
		System.out.println("in setup");
	}

	public void draw(IGF g) {
		g.background(255);
		double w = g.getWidth();
		double h = g.getHeight();
		double x1 = 0, y1 = 0, x2 = w, y2 = h;
		double lineWidth = 2;
		g.stroke(0);
		g.strokeWeight(lineWidth);
		g.line(x1, y1, x2, y2);
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

	public void keyPressed(IGF g, IGFKey k) {
		System.out.println("in keyPressed");
	}

	public void keyReleased(IGF g, IGFKey k) {
		System.out.println("in keyReleased");
	}

	public void keyTyped(IGF g, IGFKey k) {
		System.out.println("in keyTyped");
	}

	public boolean exit() {
		return true;
	}
}
