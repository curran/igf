package org.curransoft.igf.rm;

import org.curransoft.igf.IGF;
import org.curransoft.igf.test.GraphicAdapter;

/**
 * A persistent graphic which has values for its stroke and stroke parameters.
 * 
 * Subclasses should make sure to invoke super.draw() before doing any drawing,
 * because that is where the stroke and stroke parameters are set.
 * 
 * @author curran
 * 
 */
public class AbstractGraphic extends GraphicAdapter {
	/**
	 * Whether or not fill is enabled.
	 */
	boolean fillOn = true;

	/**
	 * The red component of the fill color (between 0 and 1) for this Graphic.
	 */
	private double fillR;
	/**
	 * The green component of the fill color (between 0 and 1) for this Graphic.
	 */
	private double fillG;
	/**
	 * The blue component of the fill color (between 0 and 1) for this Graphic.
	 */
	private double fillB;
	/**
	 * The alpha (transparency) component of the fill color (between 0 and 1)
	 * for this Graphic.
	 */
	private double fillA;

	/**
	 * Whether or not stroke is enabled for this Graphic.
	 */
	boolean strokeOn = false;
	/**
	 * The red component of the stroke color (between 0 and 1) for this Graphic.
	 */
	private double strokeR;
	/**
	 * The green component of the stroke color (between 0 and 1) for this
	 * Graphic.
	 */
	private double strokeG;
	/**
	 * The blue component of the stroke color (between 0 and 1) for this
	 * Graphic.
	 */
	private double strokeB;

	/**
	 * The alpha (transparency) component of the stroke color (between 0 and 1)
	 * for this Graphic.
	 */
	private double strokeA;

	/**
	 * Sets the stroke and stroke parameters of IGF but does not draw anything.
	 * Subclasses can override AND MUST call super.draw() to set the stroke and
	 * stroke variables prior to drawing.
	 */
	@Override
	public void draw(IGF g) {
		// TODO change IGF to use [0,1] like OpenGL
		// if (strokeOn)
		// g.stroke(strokeR, strokeB, strokeG, strokeA);
		// else
		// g.noFill();
		// if (strokeOn)
		// g.stroke(strokeR, strokeB, strokeG, strokeA);
		// else
		// g.noStroke();
		if (strokeOn)
			g.stroke((int) (255 * strokeR), (int) (255 * strokeB),
					(int) (255 * strokeG), (int) (255 * strokeA));
		else
			g.noStroke();
		if (fillOn)
			g.fill((int) (255 * fillR), (int) (255 * fillB),
					(int) (255 * fillG), (int) (255 * fillA));
		else
			g.noFill();

	}

	/**
	 * This method is implemented, do not override.
	 */
	@Override
	public void setFill(int red, int green, int blue, int alpha) {
		fillR = red / 255.0;
		fillG = green / 255.0;
		fillB = blue / 255.0;
		fillA = alpha / 255.0;
		fillOn = true;
	}

	/**
	 * This method is implemented, do not override.
	 */
	@Override
	public void setNoFill() {
		fillOn = false;
	}

	/**
	 * This method is implemented, do not override.
	 */
	@Override
	public void setStroke(int red, int green, int blue, int alpha) {
		strokeR = red / 255.0;
		strokeG = green / 255.0;
		strokeB = blue / 255.0;
		strokeA = alpha / 255.0;
		strokeOn = true;
	}

	/**
	 * This method is implemented, do not override.
	 */
	@Override
	public void setNoStroke() {
		strokeOn = false;
	}

}
