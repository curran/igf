package org.curransoft.igf;

import java.awt.Font;
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
	 * A number which, when used as a font ID, will cause an error to occur.
	 * Applications should initialize their font ID variables to this value as a
	 * precaution prior to changing them to values returned by loadFont().
	 */
	public static final int INVALID_FONT_ID = -1;

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
	 * Turns the fill color off. The fill color is turned on when fill() is
	 * called. It only makes sense to draw shapes with a stroke only after
	 * calling noFill().
	 */
	void noFill();

	/**
	 * Sets the current fill color to the given shade of gray (0 to 255).
	 */
	void fill(int gray);

	/**
	 * Sets the current fill color to the given shade of gray with the given
	 * alpha value (both from 0 to 255).
	 */
	void fill(int gray, int alpha);

	/**
	 * Sets the current fill color to the given red, green and blue values (each
	 * from 0 to 255).
	 */
	void fill(int red, int green, int blue);

	/**
	 * Sets the current fill color to the given red, green, blue and alpha
	 * values (each from 0 to 255).
	 */
	void fill(int red, int green, int blue, int alpha);

	/**
	 * Sets the stroke color to the given shade of gray (0 to 255).
	 */
	void stroke(int gray);

	/**
	 * Sets the stroke color to the given shade of gray with the given alpha
	 * value (both from 0 to 255).
	 */
	void stroke(int gray, int alpha);

	/**
	 * Sets the current stroke color to the given red, green and blue values
	 * (each from 0 to 255).
	 */
	void stroke(int red, int green, int blue);

	/**
	 * Sets the current stroke color to the given red, green and blue and alpha
	 * values (each from 0 to 255).
	 */
	void stroke(int red, int green, int blue, int alpha);

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

	/**
	 * Returns a font id which can be used later as the fontID argument to
	 * text() and other methods.
	 */
	int loadFont(Font font);

	/**
	 * Draws the given text string using the given font at the given (x,y)
	 * center coordinates with the given scale and rotation
	 * 
	 * @param textString
	 *            the string to draw
	 * @param fontID
	 *            the id of the font to use, as returned from loadFont(). If
	 *            IGF.INVALID_FONT_ID is used, an exception is thrown.
	 * @param x
	 *            the X center coordinate for the text
	 * @param y
	 *            the Y center coordinate for the text
	 * @param scale
	 *            the scale of the text (1 = no scaling, 0.5 = half the size, 2
	 *            = double the size etc.)
	 * @param rotation
	 *            the counter-clockwise rotation angle of the text in radians (0
	 *            = no rotation)
	 */
	void text(String textString, int fontID, double x, double y, double scale,
			double rotation);

	/**
	 * Draws the given text string using the given font at the given (x,y)
	 * center coordinates with a scale of 1 and rotation of 0.
	 * 
	 * @param textString
	 *            the string to draw
	 * @param fontID
	 *            the id of the font to use, as returned from loadFont(). If
	 *            IGF.INVALID_FONT_ID is used, an exception is thrown.
	 * @param x
	 *            the X center coordinate for the text
	 * @param y
	 *            the Y center coordinate for the text
	 */
	void text(String textString, int fontID, double x, double y);

	/**
	 * Gets the width of the bounding box that the given text string would have
	 * if it were drawn (i.e. if text() were called with a scale of 1 and a
	 * rotation of 0).
	 * 
	 * @param textString
	 *            the text string to use
	 * @param fontID
	 *            the id of the font to use, as returned from loadFont(). If
	 *            IGF.INVALID_FONT_ID is used, an exception is thrown.
	 */
	double getTextWidth(String textString, int fontID);

	/**
	 * Gets the height of the bounding box that the given text string would have
	 * if it were drawn (i.e. if text() were called with a scale of 1 and a
	 * rotation of 0).
	 * 
	 * @param textString
	 *            the text string to use
	 * @param fontID
	 *            the id of the font to use, as returned from loadFont(). If
	 *            IGF.INVALID_FONT_ID is used, an exception is thrown.
	 */
	double getTextHeight(String textString, int fontID);
}
