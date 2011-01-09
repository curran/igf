package org.curransoft.igf.rm;

import org.curransoft.igf.FillAndStrokeParameters;
import org.curransoft.igf.IGF;

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
	 * Returns null, subclasses can override.
	 */
	@Override
	public FillAndStrokeParameters getFillAndStrokeParameters() {
		return null;
	}

}
