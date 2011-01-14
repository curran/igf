package org.curransoft.igf.im;

/**
 * A mutable RGBA (red, green, blue, alpha) color.
 * 
 * @author curran
 * 
 */
public class MutableColor {
	private double red = 1, green = 1, blue = 1, alpha = 1;

	/**
	 * Returns the red component of this color (between 0 and 1).
	 */
	public double getRed() {
		return red;
	}

	/**
	 * Sets the red component of this color (between 0 and 1).
	 */
	public void setRed(double red) {
		this.red = red;
	}

	/**
	 * Returns the green component of this color (between 0 and 1).
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * Sets the green component of this color (between 0 and 1).
	 */
	public void setGreen(double green) {
		this.green = green;
	}

	/**
	 * Returns the blue component of this color (between 0 and 1).
	 */
	public double getBlue() {
		return blue;
	}

	/**
	 * Sets the blue component of this color (between 0 and 1).
	 */
	public void setBlue(double blue) {
		this.blue = blue;
	}

	/**
	 * Returns the alpha component of this color (between 0 and 1).
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * Sets the alpha component of this color (between 0 and 1).
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	/**
	 * Sets this color to the given shade of gray (from 0 to 1) with an alpha
	 * value of 1 (totally opaque).
	 */
	public void set(double gray) {
		set(gray, 1);
	}

	/**
	 * Sets this color to the given shade of gray with the given alpha value
	 * (each from 0 to 1).
	 */
	public void set(double gray, double alpha) {
		set(gray, gray, gray, alpha);
	}

	/**
	 * Sets this color to the given red, green and blue values (each from 0 to
	 * 1) with an alpha value of 1 (totally opaque).
	 */
	public void set(double red, double green, double blue) {
		set(red, green, blue, 1);
	}

	/**
	 * Sets this color to the given red, green, blue and alpha values (each from
	 * 0 to 1).
	 */
	public void set(double red, double green, double blue, double alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}

	/**
	 * Sets this color to black.
	 */
	public void setToBlack() {
		set(0);
	}

	/**
	 * Sets this color to white.
	 */
	public void setToWhite() {
		set(1);
	}

	/**
	 * Sets this color to the given color. No new objects are created.
	 */
	public void set(MutableColor color) {
		this.red = color.red;
		this.green = color.green;
		this.blue = color.blue;
		this.alpha = color.alpha;
	}
}
