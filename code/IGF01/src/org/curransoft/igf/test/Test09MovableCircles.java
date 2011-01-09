package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.rm.DraggableCircle;
import org.curransoft.igf.rm.GraphicAdapter;
import org.curransoft.igf.rm.IGFRetainedModeApplicationAdapter;

/**
 * A demonstration of the IGFRetainedModeApplication API for persistent
 * graphics. A
 * 
 * @author curran
 * 
 */
public class Test09MovableCircles extends IGFRetainedModeApplicationAdapter {
	public static void main(String[] args) {
		TestUtils.testApplication(new Test09MovableCircles(),
				new Test09MovableCircles());
	}

	public void setup(IGF g) {
		final double halfWidth = g.getWidth() / 2;
		final double halfHeight = g.getHeight() / 2;

		// this circle stays the same color when you drag it
		addGraphic(new DraggableCircle(halfWidth + 50, halfHeight, 20));

		// this circle changes color when you drag it
		addGraphic(new GraphicAdapter() {
			private double x = halfWidth, y = halfHeight, radius = 20;
			private boolean isBeingDragged = false;
			private int nonDraggingGray = 100;
			private int draggingGray = 0;

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
			public void draw(IGF g) {
				g.fill(isBeingDragged ? draggingGray : nonDraggingGray);
				g.circle(x, y, radius);
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
