package org.curransoft.igf.im;

/**
 * Mutable fill (color,on/off) and stroke (color,on/off,weight) parameters.
 * 
 * @author curran
 * 
 */
public class FillAndStroke {
	/**
	 * Whether or not the fill (the color inside shapes) is enabled. The default
	 * is true;
	 */
	private boolean fillOn = true;
	/**
	 * The fill color. The default is black.
	 */
	private MutableColor fill;
	/**
	 * The stroke color. The default is black.
	 */
	private MutableColor stroke;
	/**
	 * Whether or not the stroke (weighted outline) is enabled. The default is
	 * false;
	 */
	boolean strokeOn = false;
	/**
	 * The weight (thickness, width) of the stroke. The default value is 1.
	 */
	private double strokeWeight = 1;

	public FillAndStroke() {
		fill = new MutableColor();
		fill.setToBlack();
		stroke = new MutableColor();
		stroke.setToBlack();
	}

	/**
	 * Whether or not the fill (the color inside shapes) is enabled
	 * ("turned on").
	 */
	public boolean isFillOn() {
		return fillOn;
	}

	/**
	 * Turns fill on (fillOn=true) or off (fillOn=false).
	 */
	public void setFillOn(boolean fillOn) {
		this.fillOn = fillOn;
	}

	/**
	 * Whether or not the stroke (the color inside shapes) is enabled
	 * ("turned on").
	 */
	public boolean isStrokeOn() {
		return strokeOn;
	}

	/**
	 * Turns stroke on (strokeOn=true) or off (strokeOn=false).
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
	 * Sets all parameters in this FillAndStroke to those in the given
	 * FillAndStroke. No new objects are created.
	 */
	public void set(FillAndStroke style) {
		setStrokeOn(style.isStrokeOn());
		stroke().set(style.stroke());
		setFillOn(style.isFillOn());
		fill().set(style.fill());
	}

	/**
	 * Returns the mutable fill color of this style.
	 */
	public MutableColor fill() {
		return fill;
	}

	/**
	 * Returns the mutable stroke color of this style.
	 */
	public MutableColor stroke() {
		return stroke;
	}

}
