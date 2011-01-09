package org.curransoft.igf.im;

import java.awt.Color;

/**
 * Fill (color,on/off) and stroke (color,on/off,weight) parameters.
 * 
 * @author curran
 * 
 */
public class FillAndStrokeParameters {
	/**
	 * Whether or not the fill (the color inside shapes) is enabled.
	 */
	private boolean fillOn = false;

	/**
	 * The red component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	private double fillRed = 0;
	/**
	 * The green component of the fill color (between 0 and 1). The default
	 * value is 0.
	 */
	private double fillGreen = 0;
	/**
	 * The blue component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	private double fillBlue = 0;
	/**
	 * The alpha (transparency) component of the fill color (between 0 and
	 * 1).The default value is 1 (opaque).
	 */
	private double fillAlpha = 0;

	/**
	 * Whether or not the stroke (weighted outline) is enabled.
	 */
	boolean strokeOn = false;

	/**
	 * The weight (thickness, width) of the stroke. The default value is 1.
	 */
	private double strokeWeight = 1;

	/**
	 * The red component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	private double strokeRed = 0;
	/**
	 * The green component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	private double strokeGreen = 0;
	/**
	 * The blue component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	private double strokeBlue = 0;

	/**
	 * The alpha (transparency) component of the stroke color (between 0 and 1).
	 * The default value is 1 (opaque).
	 */
	private double strokeAlpha = 0;

	/**
	 * Whether or not the fill (the color inside shapes) is enabled.
	 */
	public boolean isFillOn() {
		return fillOn;
	}

	/**
	 * Whether or not the fill (the color inside shapes) is enabled.
	 */
	public void setFillOn(boolean fillOn) {
		this.fillOn = fillOn;
	}

	/**
	 * The red component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	public double getFillRed() {
		return fillRed;
	}

	/**
	 * The red component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	public void setFillRed(double fillRed) {
		this.fillRed = fillRed;
	}

	/**
	 * The green component of the fill color (between 0 and 1). The default
	 * value is 0.
	 */
	public double getFillGreen() {
		return fillGreen;
	}

	/**
	 * The green component of the fill color (between 0 and 1). The default
	 * value is 0.
	 */
	public void setFillGreen(double fillGreen) {
		this.fillGreen = fillGreen;
	}

	/**
	 * The blue component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	public double getFillBlue() {
		return fillBlue;
	}

	/**
	 * The blue component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	public void setFillBlue(double fillBlue) {
		this.fillBlue = fillBlue;
	}

	/**
	 * The alpha (transparency) component of the fill color (between 0 and
	 * 1).The default value is 1 (opaque).
	 */
	public double getFillAlpha() {
		return fillAlpha;
	}

	/**
	 * The alpha (transparency) component of the fill color (between 0 and
	 * 1).The default value is 1 (opaque).
	 */
	public void setFillAlpha(double fillAlpha) {
		this.fillAlpha = fillAlpha;
	}

	/**
	 * Whether or not the stroke (weighted outline) is enabled.
	 */
	public boolean isStrokeOn() {
		return strokeOn;
	}

	/**
	 * Whether or not the stroke (weighted outline) is enabled.
	 */
	public void setStrokeOn(boolean strokeOn) {
		this.strokeOn = strokeOn;
	}

	/**
	 * The weight (thickness, width) of the stroke. The default value is 1.
	 */
	public double getStrokeWeight() {
		return strokeWeight;
	}

	/**
	 * The weight (thickness, width) of the stroke. The default value is 1.
	 */
	public void setStrokeWeight(double strokeWeight) {
		this.strokeWeight = strokeWeight;
	}

	/**
	 * The red component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public double getStrokeRed() {
		return strokeRed;
	}

	/**
	 * The red component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public void setStrokeRed(double strokeRed) {
		this.strokeRed = strokeRed;
	}

	/**
	 * The green component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public double getStrokeGreen() {
		return strokeGreen;
	}

	/**
	 * The green component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public void setStrokeGreen(double strokeGreen) {
		this.strokeGreen = strokeGreen;
	}

	/**
	 * The blue component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public double getStrokeBlue() {
		return strokeBlue;
	}

	/**
	 * The blue component of the stroke color (between 0 and 1). The default
	 * value is 0.
	 */
	public void setStrokeBlue(double strokeBlue) {
		this.strokeBlue = strokeBlue;
	}

	/**
	 * The alpha (transparency) component of the stroke color (between 0 and 1).
	 * The default value is 1 (opaque).
	 */
	public double getStrokeAlpha() {
		return strokeAlpha;
	}

	/**
	 * The alpha (transparency) component of the stroke color (between 0 and 1).
	 * The default value is 1 (opaque).
	 */
	public void setStrokeAlpha(double strokeAlpha) {
		this.strokeAlpha = strokeAlpha;
	}

	/**
	 * Returns a Java Color view of the fill color. This causes new object
	 * creation, avoid using this method often.
	 */
	public Color getFillColor() {
		return new Color((float) fillRed, (float) fillGreen, (float) fillBlue,
				(float) fillAlpha);
	}

	/**
	 * Sets the current fill color to the given shade of gray (0 to 1).
	 */
	void setFill(double gray) {
		setFill(gray, 1);
	}

	/**
	 * Sets the current fill color to the given shade of gray with the given
	 * alpha value (each from 0 to 1).
	 */
	void setFill(double gray, double alpha) {
		setFill(gray, gray, gray, alpha);
	}

	/**
	 * Sets the current fill color to the given red, green and blue values (each
	 * from 0 to 1).
	 */
	void setFill(double red, double green, double blue) {
		setFill(red, green, blue, 1);
	}

	/**
	 * Sets the current fill color to the given red, green, blue and alpha
	 * values (each from 0 to 1).
	 */
	void setFill(double red, double green, double blue, double alpha) {
		setFillRed(red);
		setFillGreen(green);
		setFillBlue(blue);
		setFillAlpha(alpha);
	}

	/**
	 * Sets the fill color to black.
	 */
	public void setFillToBlack() {
		setFill(0);
	}

	/**
	 * Sets the fill color to white.
	 */
	public void setFillToWhite() {
		setFill(1);
	}

	/**
	 * Returns a Java Color view of the stroke color. This causes new object
	 * creation, avoid using this method often.
	 */
	public Color getStrokeColor() {
		return new Color((float) strokeRed, (float) strokeGreen,
				(float) strokeBlue, (float) strokeAlpha);
	}

	/**
	 * Sets the current stroke color to the given shade of gray (0 to 1).
	 */
	void setStroke(double gray) {
		setStroke(gray, 1);
	}

	/**
	 * Sets the current stroke color to the given shade of gray with the given
	 * alpha value (each from 0 to 1).
	 */
	void setStroke(double gray, double alpha) {
		setStroke(gray, gray, gray, alpha);
	}

	/**
	 * Sets the current stroke color to the given red, green and blue values
	 * (each from 0 to 1).
	 */
	void setStroke(double red, double green, double blue) {
		setStroke(red, green, blue, 1);
	}

	/**
	 * Sets the current stroke color to the given red, green, blue and alpha
	 * values (each from 0 to 1).
	 */
	void setStroke(double red, double green, double blue, double alpha) {
		setStrokeRed(red);
		setStrokeGreen(green);
		setStrokeBlue(blue);
		setStrokeAlpha(alpha);
	}

	/**
	 * Sets the stroke color to black.
	 */
	public void setStrokeToBlack() {
		setStroke(0);
	}

	/**
	 * Sets the stroke color to white.
	 */
	public void setStrokeToWhite() {
		setStroke(1);
	}

}
