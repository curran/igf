package org.curransoft.igf.im;


/**
 * An abstract ImmediateModeGraphics implementation which manages fill and
 * stroke style and the current. Subclasses must do the rest.
 * 
 * @author curran
 * 
 */
public abstract class AbstractImmediateModeGraphics implements
		ImmediateModeGraphics {
	/**
	 * The mutable fill and stroke style.
	 */
	private FillAndStroke style = new FillAndStroke();

	/**
	 * This method is implemented by AbstractImmediateModeGraphics and should
	 * not be overridden.
	 */
	@Override
	public FillAndStroke style() {
		return style;
	}
}
