package org.curransoft.igf.rm;

import org.curransoft.igf.im.FillAndStroke;
import org.curransoft.igf.im.ImmediateModeGraphics;

/**
 * A persistent graphic which has values for its stroke and stroke parameters.
 * 
 * Subclasses should make sure to invoke super.draw() before doing any drawing,
 * because that is where the fill and stroke parameters are set (or rather, the
 * fill and stroke style associated with this graphic is transferred to the
 * immediate mode graphics state).
 * 
 * @author curran
 * 
 */
public class AbstractGraphic extends GraphicAdapter {
	/**
	 * The fill and stroke parameters for this Graphic.
	 */
	FillAndStroke style = new FillAndStroke();

	/**
	 * Sets the stroke and stroke parameters of ImmediateModeGraphics but does
	 * not draw anything. Subclasses can override AND MUST call super.draw() to
	 * set the fill and stroke variables prior to drawing.
	 */
	@Override
	public void draw(ImmediateModeGraphics g) {
		g.style().set(style);
	}

	/**
	 * This function is implemented, subclasses should not override.
	 * AbstractGraphic manages fill and stroke parameters so subclasses don't
	 * need to.
	 */
	public FillAndStroke style() {
		return style;
	}

}
