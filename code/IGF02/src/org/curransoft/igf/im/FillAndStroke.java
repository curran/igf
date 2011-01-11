package org.curransoft.igf.im;

import java.awt.Color;

/**
 * Mutable fill (color,on/off) and stroke (color,on/off,weight) parameters.
 * 
 * @author curran
 * 
 */
public class FillAndStroke {
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
	 * The red component of the fill color (between 0 and 1). The default value
	 * is 0.
	 */
	public double getFillRed() {
		return fillRed;
	}

	/**
	 * The red component of the fill color (between 0 and 1). The default value
	 * is 0. Calling this also turns the fill on (afterwards, isFillOn() will
	 * return true).
	 */
	public void setFillRed(double fillRed) {
		this.fillRed = fillRed;
		fillOn = true;
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
	 * value is 0. Calling this also turns the fill on (afterwards, isFillOn
	 * will return true).
	 */
	public void setFillGreen(double fillGreen) {
		this.fillGreen = fillGreen;
		fillOn = true;
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
	 * is 0. Calling this also turns the fill on (afterwards, isFillOn() will
	 * return true).
	 */
	public void setFillBlue(double fillBlue) {
		this.fillBlue = fillBlue;
		fillOn = true;
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
	 * 1).The default value is 1 (opaque). Calling this also turns the fill on
	 * (afterwards, isFillOn() will return true).
	 */
	public void setFillAlpha(double fillAlpha) {
		this.fillAlpha = fillAlpha;
		fillOn = true;
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
	 * Sets the current fill color to the given shade of gray (0 to 1).Calling
	 * this also turns the fill on (afterwards, isFillOn() will return true).
	 */
	public void setFill(double gray) {
		setFill(gray, 1);
	}

	/**
	 * Sets the current fill color to the given shade of gray with the given
	 * alpha value (each from 0 to 1).Calling this also turns the fill on
	 * (afterwards, isFillOn() will return true).
	 */
	public void setFill(double gray, double alpha) {
		setFill(gray, gray, gray, alpha);
	}

	/**
	 * Sets the current fill color to the given red, green and blue values (each
	 * from 0 to 1).Calling this also turns the fill on (afterwards, isFillOn
	 * will return true).
	 */
	public void setFill(double red, double green, double blue) {
		setFill(red, green, blue, 1);
	}

	/**
	 * Sets the current fill color to the given red, green, blue and alpha
	 * values (each from 0 to 1).Calling this also turns the fill on
	 * (afterwards, isFillOn() will return true).
	 */
	public void setFill(double red, double green, double blue, double alpha) {
		fillRed = red;
		fillGreen = green;
		fillBlue = blue;
		fillAlpha = alpha;
		fillOn = true;
	}

	/**
	 * Sets the fill color to black.Calling this also turns the fill on
	 * (afterwards, isFillOn() will return true).
	 */
	public void setFillToBlack() {
		setFill(0);
	}

	/**
	 * Sets the fill color to white.Calling this also turns the fill on
	 * (afterwards, isFillOn() will return true).
	 */
	public void setFillToWhite() {
		setFill(1);
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
	 * Calling this also turns the fill on (afterwards, isFillOn() will return
	 * true). Calling this also turns the stroke on (afterwards, isStrokeOn()
	 * will return true).
	 */
	public void setStrokeWeight(double strokeWeight) {
		this.strokeWeight = strokeWeight;
		strokeOn = true;
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
	 * value is 0.Calling this also turns the stroke on (afterwards,
	 * isStrokeOn() will return true).
	 */
	public void setStrokeRed(double strokeRed) {
		this.strokeRed = strokeRed;
		strokeOn = true;
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
	 * value is 0.Calling this also turns the stroke on (afterwards,
	 * isStrokeOn() will return true).
	 */
	public void setStrokeGreen(double strokeGreen) {
		this.strokeGreen = strokeGreen;
		strokeOn = true;
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
	 * value is 0.Calling this also turns the stroke on (afterwards,
	 * isStrokeOn() will return true).
	 */
	public void setStrokeBlue(double strokeBlue) {
		this.strokeBlue = strokeBlue;
		strokeOn = true;
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
	 * The default value is 1 (opaque).Calling this also turns the stroke on
	 * (afterwards, isStrokeOn() will return true).
	 */
	public void setStrokeAlpha(double strokeAlpha) {
		this.strokeAlpha = strokeAlpha;
		strokeOn = true;
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
	 * Sets the current stroke color to the given shade of gray (0 to 1).Calling
	 * this also turns the stroke on (afterwards, isStrokeOn() will return
	 * true).
	 */
	public void setStroke(double gray) {
		setStroke(gray, 1);
	}

	/**
	 * Sets the current stroke color to the given shade of gray with the given
	 * alpha value (each from 0 to 1).Calling this also turns the stroke on
	 * (afterwards, isStrokeOn() will return true).
	 */
	public void setStroke(double gray, double alpha) {
		setStroke(gray, gray, gray, alpha);
	}

	/**
	 * Sets the current stroke color to the given red, green and blue values
	 * (each from 0 to 1).Calling this also turns the stroke on (afterwards,
	 * isStrokeOn() will return true).
	 */
	public void setStroke(double red, double green, double blue) {
		setStroke(red, green, blue, 1);
	}

	/**
	 * Sets the current stroke color to the given red, green, blue and alpha
	 * values (each from 0 to 1). Calling this also turns the stroke on
	 * (afterwards, isStrokeOn() will return true).
	 */
	public void setStroke(double red, double green, double blue, double alpha) {
		strokeRed = red;
		strokeGreen = green;
		strokeBlue = blue;
		strokeAlpha = alpha;
		strokeOn = true;
	}

	/**
	 * Sets the stroke color to black.Calling this also turns the stroke on
	 * (afterwards, isStrokeOn() will return true).
	 */
	public void setStrokeToBlack() {
		setStroke(0);
	}

	/**
	 * Sets the stroke color to white.Calling this also turns the stroke on
	 * (afterwards, isStrokeOn() will return true).
	 */
	public void setStrokeToWhite() {
		setStroke(1);
	}
}
