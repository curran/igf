package org.curransoft.igf.test;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.IGFApplicationAdapter;
import org.curransoft.igf.im.ImmediateModeGraphics;

/**
 * A test which draws many circles with click-toggled animation.
 * 
 * @author curran
 * 
 */
public class Test03Circles extends IGFApplicationAdapter {
	double t = 0, tInterval = 0.02;
	boolean timeIsPassing = true;

	public static void main(String[] args) {
		TestUtils.testApplication(new Test03Circles(), new Test03Circles(),
				500, 500);
	}

	public void draw(ImmediateModeGraphics g) {
		if (timeIsPassing)
			t += tInterval;
		FillAndStroke style = g.style();
		style.fill().set(0.5);
		g.drawBackground();

		style.fill().setToWhite();
		style.stroke().setToBlack();
		style.setStrokeWeight(5);

		double x = g.getWidth() / 2;
		double y = g.getHeight() / 2;
		double radius = 200;
		g.drawCircle(x, y, radius);

		int n = 50;
		style.setStrokeOn(false);
		style.setFillOn(true);

		for (double i = 0; i < n; i++) {
			double p = i / n;
			radius = 30;

			double angle = p * Math.PI * 2;
			double red = Math.sin(t + angle) / 2 + .5;
			double green = Math.sin(t * 2.1 + angle) / 2 + .5;
			double blue = Math.sin(t * 3.4 + angle) / 2 + .5;
			style.fill().set(red, green, blue);

			double r = 80 + Math.sin(t / 5 + angle * (3 + Math.sin(t))) * 40;
			double x1 = x + Math.cos(angle) * r;
			double y1 = y + Math.sin(angle) * r;
			radius = 40 + Math.sin(t + angle / 2) * 40;
			g.drawCircle(x1, y1, radius);

		}

	}

	@Override
	public void pointPressed(ImmediateModeGraphics g, int id, double x, double y) {
		timeIsPassing = !timeIsPassing;
	}
}
