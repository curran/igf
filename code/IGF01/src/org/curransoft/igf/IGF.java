package org.curransoft.igf;
import java.awt.Frame;

/**
 * The API specification for the Interactive Graphics Framework.
 * 
 * @author curran
 * 
 */
public interface IGF {
	/**
	 * The id of mouse points as passed to pointPressed(), pointDragged(), and
	 * pointReleased(). A touch point will never have this id. All mouse points
	 * will have this id.
	 */
	public static final int MOUSE_POINT_ID = -1;

	/**
	 * Shows a frame with the given bounds containing the given application.
	 * This should only ever be called once.
	 */
	Frame showFrame(String title, int x, int y, int width, int height);

	/**
	 * Returns the width of the drawing area
	 */
	double getWidth();

	/**
	 * Returns the height of the drawing area
	 */
	double getHeight();

	/**
	 * Draws a line from (x1,y1) to (x2,y2) with the given width (in pixels).
	 */
	void line(double x1, double y1, double x2, double y2, double width);

	/**
	 * Fills a rectangle the size of the drawing area with the given shade of
	 * gray (0 to 255).
	 */
	void background(int gray);

	/**
	 * Sets the current fill color to be the given shade of gray (0 to 255).
	 */
	void fill(int gray);

}
