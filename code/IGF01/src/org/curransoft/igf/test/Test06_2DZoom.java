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
public class Test06_2DZoom extends IGFApplicationAdapter {
	int min = 0, max = 5;
	double viewMinX = 0, viewMaxX = 5, viewMinY = 0, viewMaxY = 5;
	boolean goingRight = false, goingLeft = false, goingIn = false,
			goingOut = false, goingUp = false, goingDown = false;
	double dx = 0, dy, dz = 0, acceleration = 0.005, dampening = 0.9;

	public static void main(String[] args) {
		TestUtils.testApplication(new Test06_2DZoom());
	}

	@Override
	public void draw(IGF g) {
		// draw the lines
		g.background(255);
		
		g.stroke(0);
		g.strokeWeight(1);

		
		for (double i = min; i < max; i++) {
			double x = (i - viewMinX) / (viewMaxX - viewMinX) * g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}
		for (double i = min; i < max; i++) {
			double x = (i / max - viewMinX) / (viewMaxX - viewMinX)
					* g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}
		for (double i = min; i < max; i++) {
			double x = (i / max / max - viewMinX) / (viewMaxX - viewMinX)
					* g.getWidth();
			g.line(x, 0, x, g.getHeight());
		}

		for (double i = min; i < max; i++) {
			double y = (i - viewMinY) / (viewMaxY - viewMinY) * g.getHeight();
			g.line(0, y, g.getWidth(), y);
		}
		for (double i = min; i < max; i++) {
			double y = (i / max - viewMinY) / (viewMaxY - viewMinY)
					* g.getHeight();
			g.line(0, y, g.getWidth(), y);
		}
		for (double i = min; i < max; i++) {
			double y = (i / max / max - viewMinY) / (viewMaxX - viewMinX)
					* g.getHeight();
			g.line(0, y, g.getWidth(), y);
		}

		// increment the movement (pan+zoom)
		dx *= dampening;
		dy *= dampening;
		dz *= dampening;
		double middleX = (viewMaxX + viewMinX) / 2;
		double middleY = (viewMaxY + viewMinY) / 2;

		double spanX = ((viewMaxX - viewMinX) / 2) * (1 + dz);
		double spanY = ((viewMaxY - viewMinY) / 2) * (1 + dz);

		double offsetX = spanX * dx;
		double offsetY = spanY * dy;
		viewMinX = middleX - spanX + offsetX;
		viewMaxX = middleX + spanX + offsetX;
		viewMinY = middleY - spanY + offsetY;
		viewMaxY = middleY + spanY + offsetY;

		// operate on key interactions
		if (goingIn)
			dz -= acceleration;
		if (goingOut)
			dz += acceleration;
		if (goingLeft)
			dx -= acceleration;
		if (goingRight)
			dx += acceleration;
		if (goingUp)
			dy -= acceleration;
		if (goingDown)
			dy += acceleration;

	}

	@Override
	public void keyPressed(IGF g, IGFKey k) {
		if (k.keyCode() == IGFKey.UP)
			goingUp = true;
		else if (k.keyCode() == IGFKey.DOWN)
			goingDown = true;
		else if (k.keyCode() == IGFKey.LEFT)
			goingLeft = true;
		else if (k.keyCode() == IGFKey.RIGHT)
			goingRight = true;
		else if (k.keyCode() == IGFKey.CHAR) {
			if (k.keyChar() == 'd')
				goingIn = true;
			else if (k.keyChar() == 'c')
				goingOut = true;
		}
	}

	@Override
	public void keyReleased(IGF g, IGFKey k) {
		if (k.keyCode() == IGFKey.UP)
			goingUp = false;
		else if (k.keyCode() == IGFKey.DOWN)
			goingDown = false;
		else if (k.keyCode() == IGFKey.LEFT)
			goingLeft = false;
		else if (k.keyCode() == IGFKey.RIGHT)
			goingRight = false;
		else if (k.keyCode() == IGFKey.CHAR) {
			if (k.keyChar() == 'd')
				goingIn = false;
			else if (k.keyChar() == 'c')
				goingOut = false;
		}
	}
}
