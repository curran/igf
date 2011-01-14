package org.curransoft.igf.test;

import java.awt.Font;
import java.util.List;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.IGFApplicationAdapter;
import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.im.OpenGLImmediateModeGraphics;

class Node {
	String name;
	List<Node> children;
}

public class Test08CirclesDataFractal extends IGFApplicationAdapter {
	boolean timeIsPassing = true;

	int fontID;

	public static void main(String[] args) {
		// TestUtils.testApplication(new Test06CirclesFractal(),
		// new Test06CirclesFractal(), 500, 500);

		OpenGLImmediateModeGraphics gOGL = new OpenGLImmediateModeGraphics();
		int w = 500, h = 500;
		gOGL.showInFrame("Hello OpenGL", w, 0, w, h, new Test08CirclesDataFractal());
	}

	public void setup(ImmediateModeGraphics g) {
		fontID = g.loadFont(new Font(Font.SANS_SERIF, Font.PLAIN, 90));
	}

	public void draw(ImmediateModeGraphics g) {
		if (timeIsPassing) {

		}

		FillAndStroke style = g.style();

		// draw the background
		style.setFillOn(true);
		style.fill().setToWhite();
		g.drawBackground();

		style.setStrokeOn(true);
		style.stroke().setToBlack();
		style.setStrokeWeight(5);

		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		double radius = 200;

		g.drawCircle(x, y, radius);
		style.fill().setToBlack();
		g.drawText("Hello", fontID, x, y);
	}

	@Override
	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y) {
		timeIsPassing = !timeIsPassing;
	}
}
