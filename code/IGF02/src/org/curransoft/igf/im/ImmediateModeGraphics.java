package org.curransoft.igf.im;

import java.awt.Font;
import java.awt.Image;

/**
 * The Interactive Graphics Framework API specification for the immediate mode
 * graphics and mouse/keyboard events. .
 * 
 * @author curran
 * 
 */
public interface ImmediateModeGraphics {
	/**
	 * The id of mouse points passed to pointPressed(), pointDragged(), and
	 * pointReleased(). A touch point will never have this id. All mouse points
	 * will have this id.
	 */
	public static final int MOUSE_POINT_ID = -1;
	/**
	 * A number which, when used as a font ID, will cause an error to occur.
	 * Applications should initialize their font ID variables to this value as a
	 * precaution prior to setting them to values returned by loadFont().
	 */
	public static final int INVALID_FONT_ID = -1;

	/**
	 * Gets the mutable fill and stroke style used when drawing. This method
	 * should be called to in order to set the fill (color,on/off) and stroke
	 * (color,on/off,weight) parameters.
	 */
	public FillAndStrokeParameters style();

	/**
	 * Draws a line from (x1,y1) to (x2,y2). The current stroke color and weight
	 * is used to draw the line (see style()).
	 */
	void drawLine(double x1, double y1, double x2, double y2);

	/**
	 * Draws a circle with the given (x, y) center point and the given
	 * radius.The current fill and stroke style is used (see style()).
	 */
	void drawCircle(double x, double y, double radius);

	/**
	 * Fills a rectangle the size of the drawing area with the current fill
	 * color (see style()).
	 */
	void drawBackground();

	/**
	 * Returns the width of the drawing area
	 */
	double getWidth();

	/**
	 * Returns the height of the drawing area
	 */
	double getHeight();

	/**
	 * Loads an image into the graphics system and returns its ID so it can be
	 * used later with drawImage(id,...).
	 */
	int loadImage(Image image);

	/**
	 * Draws the image with the given id (id assigned from loadImage()) at the
	 * given center (x,y) coordinates scaled to the given width.
	 */
	void drawImage(int imageID, double x, double y/* double scale,double rotation */);

	// TODO add scale and rotation

	/**
	 * Returns the width of the image with the given ID (id assigned from
	 * loadImage()).
	 */
	double getImageWidth(int imageID);

	/**
	 * Returns the height of the image with the given ID (id assigned from
	 * loadImage()).
	 */
	double getImageHeight(int imageID);

	/**
	 * Returns a font id which can be used later as the fontID argument to
	 * text() and other methods.
	 */
	int loadFont(Font font);

	/**
	 * Draws the given text string using the given font at the given (x,y)
	 * center coordinates.
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
	void text(String textString, int fontID, double x, double y/*
																 * , double
																 * scale, double
																 * rotation
																 */);

	// TODO add scale and rotation
	// * @param scale
	// * the scale of the text (1 = no scaling, 0.5 = half the size, 2
	// * = double the size etc.)
	// * @param rotation
	// * the counter-clockwise rotation angle of the text in radians (0
	// * = no rotation)

	/**
	 * Gets the width of the bounding box that the given text string would have
	 * if it were drawn (i.e. if text() were called with a scale of 1 and a
	 * rotation of 0).
	 * 
	 * @param textString
	 *            the text string to use
	 * @param fontID
	 *            the id of the font to use, as returned from loadFont(). If
	 *            INVALID_FONT_ID is used, an exception is thrown.
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
