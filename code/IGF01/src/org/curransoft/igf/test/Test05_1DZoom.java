package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplicationAdapter;
import org.curransoft.igf.IGFKey;

/**
 * The most basic possible implementation of 1D zooming.
 * 
 * @author curran
 * 
 */
public class Test05_1DZoom extends IGFApplicationAdapter {
	int min = 0, max = 5;
	double viewMin = 0, viewMax = 5;
	boolean goingRight = false, goingLeft = false, goingIn = false,
			goingOut = false;
	double dx = 0, dz = 0, acceleration = 0.005, dampening = 0.9;

	public static void main(String[] args) {
		TestUtils.testApplication(new Test05_1DZoom(),new Test05_1DZoom());
	}

	@Override
	public void draw(IGF g) {
		// draw the lines
		g.background(255);
		g.stroke(0);
		g.strokeWeight(1);

		for (double i = min; i < max; i++) {
			double x = (i - viewMin) / (viewMax - viewMin) * g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}

		for (double i = min; i < max; i++) {
			double x = (i / max - viewMin) / (viewMax - viewMin) * g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}

		for (double i = min; i < max; i++) {
			double x = (i / max / max - viewMin) / (viewMax - viewMin)
					* g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}

		// increment the movement (pan+zoom)
		dx *= dampening;
		dz *= dampening;
		double middle = (viewMax + viewMin) / 2;
		double span = ((viewMax - viewMin) / 2);
		double newSpan = span * (1 + dz);
		double offset = span * dx;
		viewMin = middle - newSpan + offset;
		viewMax = middle + newSpan + offset;

		// operate on key interactions
		if (goingIn) {
			dz -= acceleration;
		}
		if (goingOut) {
			dz += acceleration;
		}
		if (goingLeft) {
			dx -= acceleration;
		}
		if (goingRight) {
			dx += acceleration;
		}
	}

	@Override
	public void keyPressed(IGF g, IGFKey k) {
		System.out.println("in keyPressed");
		System.out.println("k.keyCode() == IGFKey.UP = "
				+ (k.keyCode() == IGFKey.UP));
		if (k.keyCode() == IGFKey.UP) {
			goingIn = true;
		} else if (k.keyCode() == IGFKey.DOWN) {
			goingOut = true;
		} else if (k.keyCode() == IGFKey.LEFT) {
			goingLeft = true;
		} else if (k.keyCode() == IGFKey.RIGHT) {
			goingRight = true;
		}
	}

	@Override
	public void keyReleased(IGF g, IGFKey k) {
		if (k.keyCode() == IGFKey.UP) {
			goingIn = false;
		} else if (k.keyCode() == IGFKey.DOWN) {
			goingOut = false;
		} else if (k.keyCode() == IGFKey.LEFT) {
			goingLeft = false;
		} else if (k.keyCode() == IGFKey.RIGHT) {
			goingRight = false;
		}
	}
}
