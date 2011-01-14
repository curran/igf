package org.curransoft.igf.test;

import java.awt.Font;

import org.curransoft.igf.im.IGFApplicationAdapter;
import org.curransoft.igf.im.ImmediateModeGraphics;

/**
 * A simple ImmediateModeGraphics test which draws a circle.
 * 
 * @author curran
 * 
 */
public class Test07Text extends IGFApplicationAdapter {

	int fontID = ImmediateModeGraphics.INVALID_FONT_ID;

	public static void main(String[] args) {
		TestUtils.testApplication(new Test07Text(), new Test07Text(), 100, 100);
	}

	public void setup(ImmediateModeGraphics g) {
		fontID = g.loadFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
	}

	public void draw(ImmediateModeGraphics g) {
		g.style().fill().setToWhite();
		g.drawBackground();
		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		g.style().fill().setToBlack();
		
		//TODO Make sure the text is centered
		g.drawText("Hello World!", fontID, x, y);
	}
}
