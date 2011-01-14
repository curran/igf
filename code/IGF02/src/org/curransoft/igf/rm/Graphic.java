package org.curransoft.igf.rm;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.ImmediateModeGraphics;

/**
 * A persistent graphical object.
 * 
 * @author curran
 */
public interface Graphic {
	/**
	 * Draws the contents of this graphic.
	 */
	public void draw(ImmediateModeGraphics g);

	/**
	 * Returns true if the given (x,y) pixel space point is contained within
	 * this graphic. This is used to determine, for example, whether or not a
	 * given Graphic should initiate a drag operation for a given mousePressed
	 * event.
	 */
	public boolean containsPoint(double x, double y);

	/**
	 * Initiate a drag operation for this graphic in response to a mousePressed
	 * event where containsPoint(cursorX,cursorY) returned true.
	 */
	public void startDrag();

	/**
	 * Returns true if this graphic can be dragged with the mouse, false if not.
	 */
	public boolean isDraggable();

	/**
	 * Called when this graphic is dragged. This does not necessarily mean that
	 * the graphic will be moved on screen; it means that this graphic should
	 * respond in some way to a series of mouse drag events. This is only ever
	 * called after isDraggable() returns true, after startDrag() was called,
	 * and before endDrag() is called.
	 * 
	 * @param x
	 *            the current x coordinate of the mouse cursor during the drag
	 *            event
	 * @param y
	 *            the current y coordinate of the mouse cursor during the drag
	 *            event
	 * @param px
	 *            the previous x coordinate of the mouse cursor during the drag
	 *            event
	 * @param py
	 *            the previous y coordinate of the mouse cursor during the drag
	 *            event
	 */
	public void drag(double x, double y, double px, double py);

	/**
	 * Called when a drag operation ends. This is only ever called after
	 * isDraggable() returns true, startDrag() is called, and drag() may have
	 * been called many many times.
	 */
	public void endDrag();

	/**
	 * Gets the (mutable) fill and stroke parameters for this Graphic.
	 */
	public FillAndStroke style();

	// TODO implement focus
}
