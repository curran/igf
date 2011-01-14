package org.curransoft.igf.test;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.ImmediateModeGraphics;
import org.curransoft.igf.rm.MovableCircle;
import org.curransoft.igf.rm.GraphicAdapter;
import org.curransoft.igf.rm.IGFRetainedModeApplicationAdapter;

/**
 * A test of movable circles, and an example of how to create custom persistent
 * Graphic objects.
 * 
 * @author curran
 * 
 */
public class Test05MovableCircles extends IGFRetainedModeApplicationAdapter {
	public static void main(String[] args) {
		TestUtils.testApplication(new Test05MovableCircles(),
				new Test05MovableCircles(), 500, 500);
	}

	public void setup(ImmediateModeGraphics g) {
		final double halfWidth = g.getWidth() / 2;
		final double halfHeight = g.getHeight() / 2;

		backgroundColor().setToWhite();

		// this circle remains black, and is moved by the mouse (not necessarily
		// centered on it)
		double x = halfWidth;
		double y = halfHeight;
		double radius = 20;
		addGraphic(new MovableCircle(x, y, radius));

		// this circle is gray,changes to black when you drag it
		// and is always centered on the mouse
		addGraphic(new GraphicAdapter() {
			private double x = halfWidth + 50, y = halfHeight, radius = 20;
			private boolean isBeingDragged = false;
			private double nonDraggingGray = .7;
			private double draggingGray = 0;

			@Override
			public void startDrag() {
				this.isBeingDragged = true;
			}

			@Override
			public boolean isDraggable() {
				return true;
			}

			@Override
			public void endDrag() {
				this.isBeingDragged = false;
			}

			@Override
			public void draw(ImmediateModeGraphics g) {
				FillAndStroke style = g.style();
				style.fill().set(
						isBeingDragged ? draggingGray : nonDraggingGray);
				style.setFillOn(true);
				style.setStrokeOn(false);

				g.drawCircle(x, y, radius);
			}

			@Override
			public void drag(double x, double y, double px, double py) {
				this.x = x;
				this.y = y;
			}

			@Override
			public boolean containsPoint(double x, double y) {
				// perform a circle inside test
				double dx = x - this.x;
				double dy = y - this.y;
				return dx * dx + dy * dy < radius * radius;
			}
		});
	}

}
