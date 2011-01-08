package org.curransoft.igf.rm;

import org.curransoft.igf.IGF;

/**
 * A draggable circle.
 * 
 * @author curran
 * 
 */
public class DraggableCircle extends Circle {

	private boolean isBeingDragged = false;

	public DraggableCircle(double x, double y, double radius) {
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
	public void draw(IGF g) {
		if (isBeingDragged) {
			// TODO implement different styles (fill,stroke) for dragging mode
		}
		super.draw(g);
	}

	@Override
	public void drag(double x, double y, double px, double py) {
		this.x = x;
		this.y = y;
	}

}
