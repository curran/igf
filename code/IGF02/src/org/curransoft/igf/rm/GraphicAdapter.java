package org.curransoft.igf.rm;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.ImmediateModeGraphics;

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
	public void draw(ImmediateModeGraphics g) {

	}

	/**
	 * Returns null, subclasses can override.
	 */
	@Override
	public FillAndStroke style() {
		return null;
	}

}
