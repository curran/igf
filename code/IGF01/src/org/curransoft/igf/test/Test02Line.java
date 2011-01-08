package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which draws several lines with varying thicknesses and
 * orientations.
 * 
 * @author curran
 * 
 */
public class Test02Line extends IGFApplicationAdapter {
	public static void main(String[] args) {
		TestUtils.testApplication(new Test02Line(),new Test02Line());
	}

	public void draw(IGF g) {
		g.background(255);
		g.stroke(0);

		g.fill(0);
		int x1 = 20, y1 = 20, x2 = 80, y2 = 20;
		g.strokeWeight(1);
		g.stroke(200);
		g.line(x1, y1, x2, y2);

		g.strokeWeight(4);
		g.stroke(100);
		y1 = y2 = 40;
		g.line(x1, y1, x2, y2);

		g.strokeWeight(10);
		g.stroke(0);
		y1 = y2 = 70;
		g.line(x1, y1, x2, y2);

		int offset = 100;
		y1 = 20;
		x1 = 20+ offset;
		y2 = 80;
		x2 = 20 + offset;
		g.strokeWeight(1);
		g.stroke(200);
		g.line(x1, y1, x2, y2);

		g.strokeWeight(4);
		g.stroke(100);
		x1 = x2 = 40 + offset;
		g.line(x1, y1, x2, y2);

		g.strokeWeight(10);
		g.stroke(0);
		
		x1 = x2 = 70 + offset;
		g.line(x1, y1, x2, y2);

		x1= 20;
		y1 = 120;
		x2 = 100;
		y2 = 200;
		g.strokeWeight(1);
		g.stroke(200,0,0);
		g.line(x1, y1, x2, y2);

		offset = 50;
		x1+= offset;
		x2+= offset;
		g.strokeWeight(4);
		g.stroke(0,200,0);
		g.line(x1, y1, x2, y2);
		x1+= offset;
		x2+= offset;
		g.strokeWeight(10);
		g.stroke(0,0,200);
		g.line(x1, y1, x2, y2);

	}
}
