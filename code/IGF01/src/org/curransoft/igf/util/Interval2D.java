package org.curransoft.igf.util;

/**
 * A 2D interval.
 * 
 * @see Interval
 */
public class Interval2D {
	private final Interval x, y;

	/**
	 * Create a new Interval2D with the default bounds of (-1,1,-1,1)
	 */
	public Interval2D() {
		this(new Interval(), new Interval());
	}

	/**
	 * Create a new Interval2D with the given horizontal (x) and vertical (y)
	 * bounds
	 */
	public Interval2D(Interval x, Interval y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Create a new Interval2D with the given horizontal (xMin,xMax) and
	 * vertical (yMin, yMax) bounds
	 */
	public Interval2D(double xMin, double xMax, double yMin, double yMax) {
		this(new Interval(xMin, xMax), new Interval(yMin, yMax));
	}

	/**
	 * Sets this interval to the given horizontal (xMin,xMax) and vertical
	 * (yMin, yMax) bounds
	 */
	public void set(double xMin, double xMax, double yMin, double yMax) {
		x.setMinMax(xMin, xMax);
		y.setMinMax(yMin, yMax);
	}

	/**
	 * Gets the horizontal interval part of this 2D interval.
	 */
	public Interval getX() {
		return x;
	}

	/**
	 * Gets the vertical interval part of this 2D interval.
	 */
	public Interval getY() {
		return y;
	}
}
