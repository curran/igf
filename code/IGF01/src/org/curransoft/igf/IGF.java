package org.curransoft.igf;

import java.awt.Frame;
import java.awt.Image;

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
	 * Draws a line from (x1,y1) to (x2,y2) using the width set by
	 * strokeWeight() and the color set by stroke().
	 */
	void line(double x1, double y1, double x2, double y2);

	/**
	 * Fills a rectangle the size of the drawing area with the given shade of
	 * gray (0 to 255).
	 */
	void background(int gray);

	/**
	 * Sets the current fill color to the given shade of gray (0 to 255).
	 */
	void fill(int gray);

	/**
	 * Sets the current fill color to the given red, green and blue values (each
	 * from 0 to 255).
	 */
	void fill(int red, int green, int blue);

	/**
	 * Sets the stroke color to the given shade of gray (0 to 255).
	 */
	void stroke(int gray);

	/**
	 * Sets the current stroke color to the given red, green and blue values
	 * (each from 0 to 255).
	 */
	void stroke(int red, int green, int blue);

	/**
	 * Draws a circle with the given (x, y) center point and the given radius.
	 */
	void circle(double x, double y, double radius);

	/**
	 * Sets the stroke weight (a.k.a. thickness, width, size, etc.) in pixels
	 */
	void strokeWeight(double weight);

	/**
	 * Loads an image into the graphics system and returns its ID so it can be
	 * used later with drawImage(id,...).
	 */
	int loadImage(Image image);

	/**
	 * Draws the image with the given id (from a call to loadImage()) at the
	 * given center (x,y) coordinates scaled to the given width.
	 */
	void image(int imageID, double x, double y, double width, double height);

	/**
	 * Returns the width of the image with the given ID (as returned from a call
	 * to loadImage()).
	 */
	double getImageWidth(int imageID);

	/**
	 * Returns the height of the image with the given ID (as returned from a
	 * call to loadImage()).
	 */
	double getImageHeight(int imageID);
}
// TODO add text rendering support:
/*
 * //returns a font id int loadFont(Font font); void text(int fontID, String
 * text, double x, double y)
 * 
 * renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 36));
 * 
 * In the display method of your GLEventListener, add:
 * 
 * renderer.beginRendering(drawable.getWidth(), drawable.getHeight()); //
 * optionally set the color renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
 * renderer.draw("Text to draw", xPosition, yPosition); // ... more draw
 * commands, color changes, etc. renderer.endRendering(); } from
 * http://www.cse.unsw
 * .edu.au/~cs3421/jogl/javadoc_public/com/sun/opengl/util/j2d/TextRenderer.html
 */
