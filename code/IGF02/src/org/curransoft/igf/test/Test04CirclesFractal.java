package org.curransoft.igf.test;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.IGFApplicationAdapter;
import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.im.OpenGLImmediateModeGraphics;

public class Test04CirclesFractal extends IGFApplicationAdapter {
	double t = 0, tInterval = 0.01;
	boolean timeIsPassing = true;

	/**
	 * The branching factor
	 */
	int n = 3;
	
	int count = 0;

	public static void main(String[] args) {
		// TestUtils.testApplication(new Test06CirclesFractal(),
		// new Test06CirclesFractal(), 500, 500);

		OpenGLImmediateModeGraphics gOGL = new OpenGLImmediateModeGraphics();
		int w = 500, h = 500;
		gOGL.showInFrame("Hello OpenGL", w, 0, w, h, new Test04CirclesFractal());
	}

	public void draw(ImmediateModeGraphics g) {
		if (timeIsPassing)
			t += tInterval;

		FillAndStroke style = g.style();

		// draw the background
		style.setFillOn(true);
		style.fill().setToWhite();
		g.drawBackground();

		style.setStrokeOn(false);
		style.stroke().setToBlack();

		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		double radius = 200;
		int depth = 1;
		int maxDepth = 7;

		drawCircles(g, x, y, radius, depth, maxDepth);

//		System.out.println(count);
		count = 0;
	}

	public void drawCircles(ImmediateModeGraphics g, double x, double y,
			double radius, int depth, int maxDepth) {
		FillAndStroke style = g.style();
		style.setStrokeWeight(radius * 0.05);
		style.fill().set(1-(double) depth / maxDepth);
		g.drawCircle(x, y, radius);
		count++;

		if (depth < maxDepth) {
			double radius1 = radius * 0.462;
			for (double i = 0; i < n; i++) {
				double p = i / n;
				double angle = 2 * Math.PI * p + (depth % 2 == 0 ? 1 : -1) * t;

				double positioningRadius = radius * .533;
				double x1 = x + Math.cos(angle) * positioningRadius;
				double y1 = y + Math.sin(angle) * positioningRadius;
				drawCircles(g, x1, y1, radius1, depth + 1, maxDepth);
			}
		}
	}

	@Override
	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y) {
		timeIsPassing = !timeIsPassing;
	}
}
