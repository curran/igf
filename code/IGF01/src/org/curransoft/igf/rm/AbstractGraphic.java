package org.curransoft.igf.rm;

import org.curransoft.igf.FillAndStrokeParameters;
import org.curransoft.igf.IGF;

/**
 * A persistent graphic which has values for its stroke and stroke parameters.
 * 
 * Subclasses should make sure to invoke super.draw() before doing any drawing,
 * because that is where the fill and stroke parameters are set.
 * 
 * @author curran
 * 
 */
public class AbstractGraphic extends GraphicAdapter {
	/**
	 * The fill and stroke parameters for this Graphic.
	 */
	FillAndStrokeParameters fillAndStrokeParameters = new FillAndStrokeParameters();

	/**
	 * Sets the stroke and stroke parameters of IGF but does not draw anything.
	 * Subclasses can override AND MUST call super.draw() to set the stroke and
	 * stroke variables prior to drawing.
	 */
	@Override
	public void draw(IGF g) {

		FillAndStrokeParameters p = fillAndStrokeParameters;
		if (p.isStrokeOn())
			g.stroke(p.getStrokeRed(), p.getStrokeGreen(), p.getStrokeBlue(),
					p.getStrokeAlpha());
		else
			g.noStroke();
		if (p.isFillOn())
			g.fill(p.getFillRed(), p.getFillGreen(), p.getFillBlue(),
					p.getFillAlpha());
		else
			g.noFill();

	}

	/**
	 * This function is implemented, subclasses should not override.
	 * AbstractGraphic manages fill and stroke parameters so subclasses don't
	 * need to.
	 */
	public FillAndStrokeParameters getFillAndStrokeParameters() {
		return fillAndStrokeParameters;
	}

}
