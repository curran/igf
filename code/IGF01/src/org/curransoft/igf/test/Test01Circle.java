package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which draws a circle.
 * 
 * @author curran
 * 
 */
public class Test01Circle extends IGFApplicationAdapter {
	public static void main(String[] args) {
		TestUtils.testApplication(new Test01Circle());
	}

	public void setup(IGF g) {
	}

	public void draw(IGF g) {
		g.background(255);
		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		int radius = 20;
		g.fill(0);
		g.circle(x, y, radius);
	}
}
