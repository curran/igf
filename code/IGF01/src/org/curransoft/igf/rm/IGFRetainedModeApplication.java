package org.curransoft.igf.rm;

import java.util.ArrayList;
import java.util.List;

import org.curransoft.igf.IGF;
import org.curransoft.igf.IGFApplication;

/**
 * An IGF application with additional methods for manipulating persistent
 * graphical objects. This class encapsulates the notion of retained mode
 * graphics, in which graphics (or their abstractions) are thought of as
 * persistent objects rather than redrawn every frame.
 * 
 * If you override draw() or any of the point...() methods, you MUST call the
 * superclass implementation too, otherwise the application will break.
 * 
 * @author curran
 * 
 */
public abstract class IGFRetainedModeApplication implements IGFApplication {
	/**
	 * The list of persistent graphical objects. Drawing and mouse events are
	 * delegated to graphics in this list.
	 */
	private List<Graphic> graphics = new ArrayList<Graphic>();

	/**
	 * The graphic currently being dragged, or null when no graphic is being
	 * dragged.
	 */
	private Graphic graphicBeingDragged = null;

	/**
	 * Adds the given graphic to the persistent collection of graphics which
	 * will handle draw and mouse events.
	 */
	public void addGraphic(Graphic graphic) {
		graphics.add(graphic);
	}

	@Override
	public void draw(IGF g) {
		for (Graphic graphic : graphics)
			graphic.draw(g);
	}

	@Override
	public void pointPressed(IGF g, int id, double x, double y) {

		graphicBeingDragged = null;
		for (Graphic graphic : graphics)
			if (graphic.isDraggable() && graphic.containsPoint(x, y)) {
				graphicBeingDragged = graphic;
				graphicBeingDragged.startDrag();
				break;
			}
	}

	@Override
	public void pointDragged(IGF g, int id, double x, double y, double px,
			double py) {
		if (graphicBeingDragged != null)
			graphicBeingDragged.drag(x, y, px, py);
	}

	@Override
	public void pointReleased(IGF g, int id, double x, double y) {
		if (graphicBeingDragged != null) {
			graphicBeingDragged.endDrag();
			graphicBeingDragged = null;
		}
	}
}
