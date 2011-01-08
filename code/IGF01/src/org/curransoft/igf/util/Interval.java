package org.curransoft.igf.util;

/**
 * An encapsulation of an interval, consisting of a minimum and maximum value.
 * Methods are provided for transformations between two intervals.
 * 
 */
public class Interval {
	private double min = -1, max = 1, span = 2;

	/**
	 * Creates an interval with the default range of -1 to 1.
	 */
	public Interval() {
	}

	/**
	 * Creates an Interval initialized with the given min and max values.
	 */
	public Interval(double min, double max) {
		setMinMax(min, max);
	}

	/**
	 * Sets the min and max values for this interval
	 */
	public void setMinMax(double min, double max) {
		this.min = min;
		this.max = max;
		this.span = max - min;
	}

	/**
	 * Sets the min value for this interval
	 */
	public void setMin(double min) {
		this.min = min;
		this.span = max - min;
	}

	/**
	 * Sets the max value for this interval
	 */

	public void setMax(double max) {
		this.max = max;
		this.span = max - min;
	}

	/**
	 * Gets the min value for this interval
	 */
	public double getMin() {
		return min;
	}

	/**
	 * Gets the max value for this interval
	 */

	public double getMax() {
		return max;
	}

	/**
	 * returns (min + max) / 2
	 */
	public double getMiddle() {
		return (min + max) / 2;
	}

	/**
	 * returns max-min
	 */
	public double getSpan() {
		return span;
	}

	/**
	 * Transforms a given X value in this interval to the given one.
	 */
	public double transformTo(Interval interval, double x) {
		if (max == min)
			return interval.getMiddle();
		else
			return (x - min) / this.span * (interval.getSpan())
					+ interval.getMin();
	}

	/**
	 * Reflects a number x in this interval off the center of this interval.
	 * Returns the reflected x value.
	 */
	public double flip(double x) {
		return max - (x - min);
	}
}
