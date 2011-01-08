package org.curransoft.igf.test;

import org.curransoft.igf.IGF;
import org.curransoft.igf.rm.Graphic;

/**
 * A convenience class with which lets you implement a Graphic without having to
 * provide all methods. This class provides empty implementations of all methods
 * inherited from Graphic.
 * 
 * @author curran
 * 
 */
public class GraphicAdapter implements Graphic {
	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void startDrag() {
	}

	/**
	 * Returns false, subclasses can override.
	 */
	@Override
	public boolean isDraggable() {
		return false;
	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void endDrag() {
	}

	/**
	 * Returns false. Subclasses can override.
	 */
	@Override
	public boolean containsPoint(double x, double y) {
		return false;
	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void drag(double x, double y, double px, double py) {
	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void draw(IGF g) {

	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void setFill(int red, int green, int blue, int alpha) {

	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void setNoFill() {
	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void setStroke(int red, int green, int blue, int alpha) {
	}

	/**
	 * Does nothing, subclasses can override.
	 */
	@Override
	public void setNoStroke() {
	}

}
