package org.curransoft.igf.test;

import java.awt.Font;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;

/**
 * A simple IGF test which draws a circle.
 * 
 * @author curran
 * 
 */
public class Test07Text extends IGFApplicationAdapter {

	int fontID = IGF.INVALID_FONT_ID;

	public static void main(String[] args) {
		TestUtils.testApplication(new Test07Text(), new Test07Text());
	}

	public void setup(IGF g) {
		fontID = g.loadFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
	}

	public void draw(IGF g) {
		g.background(255);
		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		g.fill(0);
		g.text("Hello World!", fontID, x, y);

		g.noFill();
		g.stroke(0);
		g.strokeWeight(1);
	}
}
