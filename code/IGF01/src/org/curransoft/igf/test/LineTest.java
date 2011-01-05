package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which draws several lines with varying thichnesses and
 * orientations.
 * 
 * @author curran
 * 
 */
public class LineTest extends IGFApplicationAdapter {
	public static void main(String[] args) {
		TestUtils.testApplication(new LineTest());
	}

	public void draw(IGF g) {
		g.background(255);
		g.stroke(0);

		int x1 = 20, y1 = 20, x2 = 80, y2 = 20;
		g.strokeWeight(1);
		g.line(x1, y1, x2, y2);

		g.strokeWeight(4);
		y1 = y2 = 40;
		g.line(x1, y1, x2, y2);

		g.strokeWeight(10);
		y1 = y2 = 70;
		g.line(x1, y1, x2, y2);
	}
}
