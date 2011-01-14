package org.curransoft.igf.rm;

import org.curransoft.igf.im.ImmediateModeGraphics;

/**
 * A draggable circle.
 * 
 * @author curran
 * 
 */
public class MovableCircle extends Circle {

	private boolean isBeingDragged = false;

	public MovableCircle(double x, double y, double radius) {
		super(x, y, radius);
	}

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
		if (isBeingDragged) {
			// TODO implement different styles (fill,stroke) for dragging mode
		}
		super.draw(g);
	}

	@Override
	public void drag(double x, double y, double px, double py) {
		this.x += x - px;
		this.y += y - py;
	}

}
